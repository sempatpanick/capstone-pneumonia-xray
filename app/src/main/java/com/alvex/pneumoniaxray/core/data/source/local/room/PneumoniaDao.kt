package com.alvex.pneumoniaxray.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alvex.pneumoniaxray.core.data.source.local.entity.HistoryEntity
import com.alvex.pneumoniaxray.core.data.source.local.entity.LoginEntity
import com.alvex.pneumoniaxray.core.data.source.local.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PneumoniaDao {
    @Query("SELECT * FROM picture")
    fun getAllPicture(): Flow<List<PictureEntity>>

    @Query("SELECT * FROM login")
    fun getLogin(): Flow<List<LoginEntity>>

    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<HistoryEntity>>

    @Query("DELETE FROM picture")
    fun deletePicture()

    @Query("DELETE FROM login")
    fun deleteLogin()

    @Query("DELETE FROM history")
    fun deleteHistory()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: List<PictureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogin(login: List<LoginEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: List<HistoryEntity>)

}