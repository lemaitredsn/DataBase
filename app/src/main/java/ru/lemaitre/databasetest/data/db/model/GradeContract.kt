package ru.lemaitre.databasetest.data.db.model

object GradeContract {
    const val TABLE_NAME = "grades"

    object Columns{
        const val ID = "id"
        const val NAME = "name"
        const val STUDENT_ID = "student_id"
        const val DISCIPLINE_ID = "discipline_id"
    }
}