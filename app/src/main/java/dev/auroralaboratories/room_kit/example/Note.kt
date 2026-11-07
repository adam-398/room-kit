package dev.auroralaboratories.room_kit.example

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a note entity in the database, @Entity instantiates the data class as a table
 * @param id The ID of the note.
 * @param title The title of the note.
 * @param body The body of the note.
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val body: String = "",
    val createdAt: Long = System.currentTimeMillis(),
)
