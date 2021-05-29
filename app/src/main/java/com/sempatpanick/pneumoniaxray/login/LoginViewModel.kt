package com.sempatpanick.pneumoniaxray.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.domain.model.Doctor
import com.sempatpanick.pneumoniaxray.core.domain.usecase.PneumoniaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val pneumoniaUseCase: PneumoniaUseCase) : ViewModel() {

    fun getDoctor(username: String, password: String) = pneumoniaUseCase.getDoctor(username, password).asLiveData()

}