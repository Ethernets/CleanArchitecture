package com.example.core.usecase

import com.example.core.data.Note

class GetWorldCount {
    operator fun invoke(note: Note) = getCount(note.title) + getCount(note.content)
    private fun getCount(string: String) = string.split(Regex("[\\s\\n]+")).count {
        it.contains(Regex("[\\p{L}]+"))
    }
}