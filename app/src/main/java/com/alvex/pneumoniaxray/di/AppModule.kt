package com.alvex.pneumoniaxray.di

import com.alvex.pneumoniaxray.core.domain.usecase.PneumoniaInteractor
import com.alvex.pneumoniaxray.core.domain.usecase.PneumoniaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun providePneumoniaUseCase(pneumoniaInteractor: PneumoniaInteractor): PneumoniaUseCase

}