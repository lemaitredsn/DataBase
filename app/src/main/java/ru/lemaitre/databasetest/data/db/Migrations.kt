package ru.lemaitre.databasetest.data.db

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object: Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        Log.d("TAG", "Miration 1-2 start")
        database.execSQL("ALTER TABLE universities ADD COLUMN email TEXT DEFAULT 'mail'")
        Log.d("TAG", "Miration 1-2 success")
    }

}