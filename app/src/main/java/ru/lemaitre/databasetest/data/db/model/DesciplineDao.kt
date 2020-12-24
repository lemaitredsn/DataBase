package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface DesciplineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDescipline(descipline: List<Entities.Descipline>)

    @Update
    suspend fun updateDescipline(descipline: Entities.Descipline)

    @Query("SELECT * FROM ${DesciplineContract.TABLE_NAME}")
    suspend fun getAllDescipline(): List<Entities.Descipline>

}