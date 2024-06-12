package com.ankit.noteapp.repository

import androidx.lifecycle.LiveData
import com.ankit.noteapp.database.NoteDAO
import com.ankit.noteapp.model.Note

class NotesRepository(private val noteDAO: NoteDAO) {

    val allNotes : LiveData<List<Note>> = noteDAO.getAllNote()

    suspend fun insert(note: Note){
        noteDAO.insert(note)
    }

    suspend fun delete(note:Note){
        noteDAO.delete(note)
    }

    suspend fun update(note: Note){
        noteDAO.update(note.id,note.title,note.note)
    }
}