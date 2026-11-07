package dev.auroralaboratories.room_kit.core.database

import androidx.room.TypeConverter

/**
 * Type converter class for Room database.
 */
class Converters {

    /**
     * Converts a list of strings to a single string.
     * @param value The list of strings to convert.
     * @return The string representation of the list.
     */
    @TypeConverter
    fun fromStringList(value: List<String>?): String? =
        value?.joinToString(separator = "/u001F")

    /**
     * Converts a string to a list of strings.
     * @param value The string to convert.
     * @return The list of strings.
     */
    @TypeConverter
    fun toStringList(value: String?): List<String>? =
        value?.let { if (it.isEmpty()) emptyList() else it.split("/u001F") }

}