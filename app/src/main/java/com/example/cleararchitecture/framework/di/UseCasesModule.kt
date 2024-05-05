package com.example.cleararchitecture.framework.di

import com.example.cleararchitecture.framework.UseCases
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNote
import com.example.core.usecase.GetNote
import com.example.core.usecase.GetWorldCount
import com.example.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNote(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWorldCount(),
    )
}