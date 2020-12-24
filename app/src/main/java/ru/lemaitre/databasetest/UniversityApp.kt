package ru.lemaitre.databasetest

import android.app.Application
import ru.lemaitre.databasetest.data.db.Database

class UniversityApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
    }
}