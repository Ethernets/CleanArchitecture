package com.example.cleararchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cleararchitecture.R
import com.example.cleararchitecture.databinding.FragmentNoteBinding
import com.example.cleararchitecture.framework.NoteViewModel
import com.example.cleararchitecture.framework.NoteViewModelFactory
import com.example.core.data.Note

class NoteFragment : Fragment() {
    private lateinit var viewBinding: FragmentNoteBinding
    private lateinit var viewModel: NoteViewModel
    private var noteId = 0L
    private var currentNote = Note("", "", creationTime = 0L, updateTime = 0L)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNoteBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.note_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.delete_note -> {
                        if (currentNote.id != 0L) {
                            viewModel.deleteNote(currentNote)
                        }
                        findNavController().popBackStack()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = NoteViewModelFactory(this.requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        arguments.let {
            noteId = NoteFragmentArgs.fromBundle(it!!).noteId
        }
        if (noteId != 0L){
            viewModel.getNoteById(noteId)
        }
        viewBinding.checkButton.setOnClickListener {
            if (viewBinding.titleEditText.toString() != "" || viewBinding.contentEditText.toString() != ""){
                val time: Long = System.currentTimeMillis()
                currentNote.title = viewBinding.titleEditText.text.toString()
                currentNote.content = viewBinding.contentEditText.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L){
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                findNavController().popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            it?.let {
                currentNote = it
                viewBinding.titleEditText.setText(it.title)
                viewBinding.contentEditText.setText(it.content)
            }
        })
    }

//    @SuppressLint("ServiceCast")
//    private fun hideKeyboard() {
//        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(view?.windowToken, 0)
//    }
}