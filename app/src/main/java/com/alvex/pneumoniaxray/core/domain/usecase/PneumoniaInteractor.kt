package com.alvex.pneumoniaxray.core.domain.usecase

import com.alvex.pneumoniaxray.core.data.PneumoniaRepository
import javax.inject.Inject

class PneumoniaInteractor @Inject constructor(private val pneumoniaRepository: PneumoniaRepository) : PneumoniaUseCase {
    override fun getAllPicture() = pneumoniaRepository.getAllPicture()
    override fun getLogin(username: String, password: String) = pneumoniaRepository.getLogin(username, password)
    override fun getAllHistory() = pneumoniaRepository.getAllHistory()
}