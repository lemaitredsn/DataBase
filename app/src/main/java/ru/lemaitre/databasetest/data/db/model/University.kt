package ru.lemaitre.databasetest.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UniversitiesContract.TABLE_NAME)
data class University(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UniversitiesContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = UniversitiesContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = UniversitiesContract.Columns.ADDRESS)
    val address: String,
    @ColumnInfo(name = UniversitiesContract.Columns.AVATAR)
    val avatar: String?
)
