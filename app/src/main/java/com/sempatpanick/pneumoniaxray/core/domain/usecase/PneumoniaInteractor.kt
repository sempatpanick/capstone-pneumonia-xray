package com.sempatpanick.pneumoniaxray.core.domain.usecase

import com.sempatpanick.pneumoniaxray.core.data.PneumoniaRepository
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PneumoniaInteractor @Inject constructor(private val pneumoniaRepository: PneumoniaRepository): PneumoniaUseCase {
    override fun getAllPicture() = pneumoniaRepository.getAllPicture()
    override fun getAllPatient(query: String) = pneumoniaRepository.getAllPatient(query)
}