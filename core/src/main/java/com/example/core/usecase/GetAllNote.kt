package com.example.core.usecase

import com.example.core.repository.NoteRepository

class GetAllNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAll()
}