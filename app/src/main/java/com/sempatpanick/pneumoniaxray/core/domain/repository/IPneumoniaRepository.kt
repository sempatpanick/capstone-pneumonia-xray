package com.sempatpanick.pneumoniaxray.core.domain.repository

import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.domain.model.Patient
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow

interface IPneumoniaRepository {
    fun getAllPicture(): Flow<Resource<List<Picture>>>
    fun getAllPatient(query: String): Flow<Resource<List<Patient>>>
}