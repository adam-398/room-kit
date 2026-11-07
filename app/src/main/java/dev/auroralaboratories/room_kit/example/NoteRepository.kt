package dev.auroralaboratories.room_kit.example

import kotlinx.coroutines.flow.Flow

/**
 * Repository for the [Note] entity.
 * @param dao The DAO for the [Note] entity.
 */
class NoteRepository(private val dao: NoteDao) {

    /**
     * Live list of all notes, newest first. Emits a fresh list whenever the notes table changes.
     */
    val notes: Flow<List<Note>> = dao.observeAll()

    /**
     * Retrieves a note by its ID.
     * @param id The ID of the note to retrieve.
     * @return The [Note] with the specified ID, or null if not found.
     */
    suspend fun get(id: Long): Note? = dao.get(id)

    /**
     * Adds a new note to the database.
     * @param title The title of the note.
     * @param body The body of the note.
     * @return The ID of the inserted note.
     */
    suspend fun add(title: String, body: String = ""): Long =
        dao.insert(Note(title = title, body = body))

    /**
     * Updates an existing note in the database.
     * @param note The note to update.
     */
    suspend fun update(note: Note) {
        dao.update(note)
    }

    /**
     * Deletes a note from the database.
     * @param note The note to delete.
     */
    suspend fun delete(note: Note) {
        dao.delete(note)
    }
}