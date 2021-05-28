package com.sempatpanick.pneumoniaxray.core.domain.usecase

import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.domain.model.Patient
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow

interface PneumoniaUseCase {
    fun getAllPicture(): Flow<Resource<List<Picture>>>
    fun getAllPatient(query: String): Flow<Resource<List<Patient>>>
}