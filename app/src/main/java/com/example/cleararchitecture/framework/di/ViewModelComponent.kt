package com.example.cleararchitecture.framework.di

import com.example.cleararchitecture.framework.ListViewModel
import com.example.cleararchitecture.framework.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}