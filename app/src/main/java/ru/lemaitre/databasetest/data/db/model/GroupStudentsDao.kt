package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface GroupStudentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupStudents(groupStudents: List<Entities.GroupStudents>)

    @Update
    suspend fun updateGroupStudents(groupStudents: Entities.GroupStudents)

    @Query("SELECT * FROM ${GroupStudentsContract.TABLE_NAME}")
    suspend fun getAllGroupStudents(): List<Entities.GroupStudents>

}
