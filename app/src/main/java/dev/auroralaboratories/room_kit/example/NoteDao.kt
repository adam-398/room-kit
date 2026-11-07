package dev.auroralaboratories.room_kit.example

import androidx.room.Dao
import androidx.room.Query
import dev.auroralaboratories.room_kit.core.database.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * Data access object (DAO) for the [Note] entity. Extends the [BaseDao] interface.
 * @see BaseDao
 */
@Dao
interface NoteDao : BaseDao<Note> {

    /**
     * Observes all notes in the database.
     * @return A [Flow] of a list of [Note]s.
     */
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<Note>>

    /**
     * Retrieves a note by its ID.
     * @param id The ID of the note to retrieve.
     * @return The [Note] with the specified ID, or null if not found.
     */
    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun get(id: Long): Note?
}