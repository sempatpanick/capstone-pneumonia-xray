package com.alvex.pneumoniaxray.core.di

import android.content.Context
import androidx.room.Room
import com.alvex.pneumoniaxray.core.data.source.local.room.PneumoniaDao
import com.alvex.pneumoniaxray.core.data.source.local.room.PneumoniaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PneumoniaDatabase = Room.databaseBuilder(
        context,
        PneumoniaDatabase::class.java, "Pneumonia.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePneumoniaDao(database: PneumoniaDatabase): PneumoniaDao = database.pneumoniaDao()
}