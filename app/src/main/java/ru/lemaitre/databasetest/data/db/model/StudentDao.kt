package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: List<Entities.Student>)

    @Update
    suspend fun updateStudent(student: Entities.Student)

    @Query("SELECT * FROM ${StudentContract.TABLE_NAME}")
    suspend fun getAllStudents(): List<Entities.Student>

    @Query("DELETE FROM ${StudentContract.TABLE_NAME} WHERE ${StudentContract.Columns.ID} = :studentId")
    suspend fun removeStudentById(studentId: Long)
}