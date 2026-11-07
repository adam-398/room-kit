package dev.auroralaboratories.room_kit.example

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A note stored in the database. The @Entity annotation marks this class as a table.
 * @param id Unique ID, assigned by SQLite when the note is inserted.
 * @param title The title of the note.
 * @param body The body of the note.
 * @param createdAt When the note was created, in milliseconds since 1970.
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val body: String = "",
    val createdAt: Long = System.currentTimeMillis(),
)