package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface GradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grade: List<Entities.Grades>)

    @Update
    suspend fun updateGrade(grade: Entities.Grades)

    @Query("SELECT * FROM ${GradeContract.TABLE_NAME}")
    suspend fun getAllGrade(): List<Entities.Grades>

    @Query("DELETE FROM ${GradeContract.TABLE_NAME} WHERE ${GradeContract.Columns.STUDENT_ID} = :studentId")
    suspend fun removeGradeById(studentId: Long)
}