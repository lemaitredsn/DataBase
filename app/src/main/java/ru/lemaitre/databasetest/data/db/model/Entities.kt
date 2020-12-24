package ru.lemaitre.databasetest.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

sealed class Entities : Parcelable {
    @Entity(
        tableName = InstituteContract.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                entity = University::class,
                parentColumns = [UniversitiesContract.Columns.ID],
                childColumns = [InstituteContract.Columns.UNIVERSITY_ID]
            )
        ]
    )
    @Parcelize
    data class Institute(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = InstituteContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = InstituteContract.Columns.UNIVERSITY_ID)
        val universityId: Long,
        @ColumnInfo(name = InstituteContract.Columns.NAME)
        val name: String
    ) : Entities()

    @Entity(
        tableName = KaphedraContract.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                entity = University::class,
                parentColumns = [UniversitiesContract.Columns.ID],
                childColumns = [KaphedraContract.Columns.UNIVERSITY_ID]
            )
        ]
    )
    @Parcelize
    data class Kaphedra(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = KaphedraContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = KaphedraContract.Columns.UNIVERSITY_ID)
        val universityId: Long,
        @ColumnInfo(name = KaphedraContract.Columns.NAME)
        val name:String
    ): Entities()


}