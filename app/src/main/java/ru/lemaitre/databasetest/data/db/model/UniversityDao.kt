package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversity(universities: List<University>)

    @Query("SELECT * FROM ${UniversitiesContract.TABLE_NAME}")
    suspend fun getAllUniversities(): List<University>

    @Delete
    suspend fun removeUniversity(university: University)

    @Query("DELETE FROM ${UniversitiesContract.TABLE_NAME} WHERE ${UniversitiesContract.Columns.ID} = :universityId")
    suspend fun removeUniversityById(universityId: Long)

    @Query("SELECT * FROM ${UniversitiesContract.TABLE_NAME} WHERE ${UniversitiesContract.Columns.ID} = :universityId")
    suspend fun getUniversityById(universityId: Long): University?

    @Update
    suspend fun updateUniversity(university: University)

    @Query("SELECT * FROM ${UniversitiesContract.TABLE_NAME}")
    suspend fun getAllUniversityWithRelations(): List<UniversityWithInstituties>

    @Query("SELECT * FROM ${UniversitiesContract.TABLE_NAME} WHERE ${UniversitiesContract.Columns.ID} = :universityId")
    suspend fun getUniversityRelationsById(universityId: Long):UniversityWithInstituties
}