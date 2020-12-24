package ru.lemaitre.databasetest.data.db.model

import androidx.room.*

@Dao
interface KaphedraDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKaphedra(kaphedra: List<Entities.Kaphedra>)

    @Update
    suspend fun updateKaphedra(kaphedra: Entities.Kaphedra)

    @Query("SELECT * FROM ${KaphedraContract.TABLE_NAME}")
    suspend fun getAllKaphedra(): List<Entities.Kaphedra>


}