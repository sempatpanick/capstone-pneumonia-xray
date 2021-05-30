package com.alvex.pneumoniaxray.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alvex.pneumoniaxray.core.domain.usecase.PneumoniaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val pneumoniaUseCase: PneumoniaUseCase) :
    ViewModel() {

    fun getLogin(username: String, password: String) =
        pneumoniaUseCase.getLogin(username, password).asLiveData()

}