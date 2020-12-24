package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface InstituteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstitute(institute: List<Entities.Institute>)

    @Update
    suspend fun updateInstitute(institute: Entities.Institute)

    @Query("SELECT * FROM ${InstituteContract.TABLE_NAME}")
    suspend fun getAlInstituties(): List<Entities.Institute>
}