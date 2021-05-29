package com.sempatpanick.pneumoniaxray.core.domain.usecase

import com.sempatpanick.pneumoniaxray.core.data.PneumoniaRepository
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PneumoniaInteractor @Inject constructor(private val pneumoniaRepository: PneumoniaRepository): PneumoniaUseCase {
    override fun getAllPicture() = pneumoniaRepository.getAllPicture()
    override fun getDoctor(username: String, password: String) = pneumoniaRepository.getDoctor(username, password)
}