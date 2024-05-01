package com.example.cleararchitecture.framework

import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNote
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getNotes: GetAllNote,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    )