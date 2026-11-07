package dev.auroralaboratories.room_kit.example

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.auroralaboratories.room_kit.core.database.Converters

/**
 * The Room database for the app.
 * @see Database
 * @see TypeConverters
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
/**
 * Abstract class representing the Room database for the app.
 */
abstract class NoteDatabase : RoomDatabase() {

    /**
     * Retrieves the DAO for the [Note] entity.
     */
    abstract fun noteDao(): NoteDao

    /**
     * Companion object for the [NoteDatabase] class.
     */
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Retrieves the singleton instance of the [NoteDatabase].
         * @param context The application context.
         * @return The singleton instance of the [NoteDatabase].
         */
        fun getInstance(context: Context): NoteDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build().also { INSTANCE = it }
            }
    }
}
