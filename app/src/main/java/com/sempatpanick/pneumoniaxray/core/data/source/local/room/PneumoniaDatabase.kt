package com.sempatpanick.pneumoniaxray.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.DoctorEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity

@Database(
    entities = [PictureEntity::class, DoctorEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PneumoniaDatabase : RoomDatabase() {
    abstract fun pneumoniaDao(): PneumoniaDao
}