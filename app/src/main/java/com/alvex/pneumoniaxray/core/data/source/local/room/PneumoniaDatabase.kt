package com.alvex.pneumoniaxray.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alvex.pneumoniaxray.core.data.source.local.entity.HistoryEntity
import com.alvex.pneumoniaxray.core.data.source.local.entity.LoginEntity
import com.alvex.pneumoniaxray.core.data.source.local.entity.PictureEntity

@Database(
    entities = [
        HistoryEntity::class,
        PictureEntity::class,
        LoginEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PneumoniaDatabase : RoomDatabase() {
    abstract fun pneumoniaDao(): PneumoniaDao
}