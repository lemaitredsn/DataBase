package ru.lemaitre.databasetest.data.db.model

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class GradeConverter {

    @TypeConverter
    fun fromString(string: String): List<String>{
        val s = string.split(" ")
        return s
    }

    @TypeConverter
    fun toString(list: List<String>): String = list.toString()

}