package com.alvex.pneumoniaxray.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alvex.pneumoniaxray.core.data.source.remote.network.ApiConfig
import com.alvex.pneumoniaxray.core.domain.usecase.PneumoniaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(pneumoniaUseCase: PneumoniaUseCase) : ViewModel() {
    val picture = pneumoniaUseCase.getAllPicture().asLiveData()

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val searchPatient = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .map {
            ApiConfig.provideApiService().getListPatient(it).data
        }
        .asLiveData()
}