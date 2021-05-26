package com.sempatpanick.pneumoniaxray.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sempatpanick.pneumoniaxray.core.domain.usecase.PneumoniaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(pneumoniaUseCase: PneumoniaUseCase) : ViewModel() {
    val picture = pneumoniaUseCase.getAllPicture().asLiveData()
}