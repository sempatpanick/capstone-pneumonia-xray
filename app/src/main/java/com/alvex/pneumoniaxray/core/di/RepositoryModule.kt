package com.alvex.pneumoniaxray.core.di

import com.alvex.pneumoniaxray.core.data.PneumoniaRepository
import com.alvex.pneumoniaxray.core.domain.repository.IPneumoniaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(pneumoniaRepository: PneumoniaRepository): IPneumoniaRepository
}