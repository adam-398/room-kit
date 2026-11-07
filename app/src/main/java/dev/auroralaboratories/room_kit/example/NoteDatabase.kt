package dev.auroralaboratories.room_kit.example

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.auroralaboratories.room_kit.core.database.Converters

/**
 * The Room database for the app. Lists the entities, registers the converters,
 * exposes the DAOs, and holds the single shared instance.
 * @see Database
 * @see TypeConverters
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {

    /**
     * Retrieves the DAO for the [Note] entity.
     */
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Retrieves the singleton instance of the [NoteDatabase], creating it if needed.
         * @param context Any context. The application context is used internally.
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