package com.sempatpanick.pneumoniaxray.core.domain.usecase

import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.sempatpanick.pneumoniaxray.core.domain.model.Doctor
import com.sempatpanick.pneumoniaxray.core.domain.model.Patient
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow

interface PneumoniaUseCase {
    fun getAllPicture(): Flow<Resource<List<Picture>>>
    fun getDoctor(username: String, password: String): Flow<Resource<List<Doctor>>>
}