package com.alvex.pneumoniaxray.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alvex.pneumoniaxray.core.domain.usecase.PneumoniaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@HiltViewModel
@FlowPreview
@ExperimentalCoroutinesApi
class HistoryViewModel @Inject constructor(pneumoniaUseCase: PneumoniaUseCase) : ViewModel() {
    val history = pneumoniaUseCase.getAllHistory().asLiveData()
}