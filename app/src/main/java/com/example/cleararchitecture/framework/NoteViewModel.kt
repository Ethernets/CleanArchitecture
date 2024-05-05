package com.example.cleararchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleararchitecture.framework.di.ApplicationModule
import com.example.cleararchitecture.framework.di.DaggerViewModelComponent
import com.example.core.data.Note
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNote
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application): ViewModel()  {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases
    init {
        DaggerViewModelComponent.builder().applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
    }

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note){
       coroutineScope.launch {
           useCases.addNote(note)
           saved.postValue(true)
       }
    }

    fun getNoteById(id: Long){
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(currentNote: Note) {
        coroutineScope.launch {
            useCases.removeNote(currentNote)
            saved.postValue(true)
        }
    }
}

class NoteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}