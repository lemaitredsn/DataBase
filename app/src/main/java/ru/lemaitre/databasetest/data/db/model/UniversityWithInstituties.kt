package ru.lemaitre.databasetest.data.db.model

import androidx.room.Embedded
import androidx.room.Relation

data class UniversityWithInstituties(
    @Embedded
    val university: University,
    @Relation(
        parentColumn = UniversitiesContract.Columns.ID,
        entityColumn = InstituteContract.Columns.UNIVERSITY_ID
    )
    val instituties: List<Entities.Institute>
)
