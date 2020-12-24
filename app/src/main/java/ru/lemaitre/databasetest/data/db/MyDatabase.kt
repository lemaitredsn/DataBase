package ru.lemaitre.databasetest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lemaitre.databasetest.data.db.model.*

@Database(
    entities = [
        University::class,
        Entities.Institute::class,
        Entities.Kaphedra::class
    ],
    version = MyDatabase.DB_VERSION
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun universityDao(): UniversityDao
    abstract fun instituteDao(): InstituteDao
    abstract fun kaphedraDao(): KaphedraDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "university-database"
    }
}