package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface TeachersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: List<Entities.Teachers>)

    @Update
    suspend fun updateTeacher(teacher: Entities.Teachers)

    @Query("SELECT * FROM ${TeacherContract.TABLE_NAME}")
    suspend fun getAllTeachers(): List<Entities.Teachers>

}