package com.example.cleararchitecture.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)
    @Query("SELECT * FROM note")
    suspend fun getAllNoteEntities(): List<NoteEntity>
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteEntity(id: Long): NoteEntity?
    @Delete
    suspend fun removeNoteEntity(noteEntity: NoteEntity)
}