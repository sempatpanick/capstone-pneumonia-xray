package com.sempatpanick.pneumoniaxray.core.data.source.local.room

import androidx.room.*
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PneumoniaDao {
    @Query("SELECT * FROM picture")
    fun getAllPicture(): Flow<List<PictureEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: List<PictureEntity>)

}