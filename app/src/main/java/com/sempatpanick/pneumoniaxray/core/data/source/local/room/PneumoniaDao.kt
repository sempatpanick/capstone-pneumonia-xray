package com.sempatpanick.pneumoniaxray.core.data.source.local.room

import androidx.room.*
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.DoctorEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PneumoniaDao {
    @Query("SELECT * FROM picture")
    fun getAllPicture(): Flow<List<PictureEntity>>

    @Query("SELECT * FROM doctor")
    fun getDoctor(): Flow<List<DoctorEntity>>

    @Query("DELETE FROM picture")
    fun deletePicture()

    @Query("DELETE FROM doctor")
    fun deleteDoctor()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: List<PictureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctor(doctor: List<DoctorEntity>)

}