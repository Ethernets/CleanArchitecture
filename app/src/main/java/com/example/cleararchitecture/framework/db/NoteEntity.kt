package com.example.cleararchitecture.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val content: String,
    @ColumnInfo(name = "creation_time")
    val creationTime: Long,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
){
    companion object{
        fun fromNote(note: Note) = NoteEntity(
            note.title,
            note.content,
            note.creationTime,
            note.updateTime
        )
    }

    fun toNote() = Note(
        title = title,
        content = content,
        creationTime = creationTime,
        updateTime = updateTime,
        id = id
    )
}