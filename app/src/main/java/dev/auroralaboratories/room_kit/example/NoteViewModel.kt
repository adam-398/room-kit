package dev.auroralaboratories.room_kit.example

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * View model for the notes screen.
 * @param application The application context.
 * @see AndroidViewModel
 */
class NoteViewModel(application: Application): AndroidViewModel(application)  {

    private val repository = NoteRepository(NoteDatabase.getInstance(application).noteDao())

    /**
     * Live list of all notes, newest first. Emits a fresh list whenever the notes table changes.
     * @see StateFlow
     */
    val notes: StateFlow<List<Note>> = repository.notes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /**
     * Adds a new note to the database.
     * @param title The title of the note.
     * @param body The body of the note.
     */
    fun addNote(title: String, body: String = "") {
        viewModelScope.launch {
            repository.add(title, body)
        }
    }

    /**
     * Deletes a note from the database.
     * @param note The note to delete.
     */
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}