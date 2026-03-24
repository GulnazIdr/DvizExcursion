package org.example.project.core.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun arrayListToString(list: List<String>?): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun stringToArrayList(json: String): List<String> {
        return Json.Default.decodeFromString(json)
    }
}