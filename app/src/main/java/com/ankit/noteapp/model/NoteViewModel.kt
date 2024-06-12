package com.ankit.noteapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ankit.noteapp.database.NoteDatabase
import com.ankit.noteapp.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }


    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.delete(note) }
    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.insert(note) }
    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.update(note) }

}