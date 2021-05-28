package com.sempatpanick.pneumoniaxray.core.data.source.local.room

import androidx.room.*
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PatientEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow

@Dao
interface PneumoniaDao {
    @Query("SELECT * FROM picture")
    fun getAllPicture(): Flow<List<PictureEntity>>

    @Query("SELECT * FROM patient")
    fun getAllPatient(): Flow<List<PatientEntity>>

    @Query("DELETE FROM picture")
    fun deletePicture()

    @Query("DELETE FROM patient")
    fun deletePatient()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: List<PictureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: List<PatientEntity>)

}