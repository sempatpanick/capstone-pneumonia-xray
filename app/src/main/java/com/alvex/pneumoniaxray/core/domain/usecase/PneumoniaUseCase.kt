package com.alvex.pneumoniaxray.core.domain.usecase

import com.alvex.pneumoniaxray.core.data.Resource
import com.alvex.pneumoniaxray.core.domain.model.History
import com.alvex.pneumoniaxray.core.domain.model.Login
import com.alvex.pneumoniaxray.core.domain.model.Picture
import kotlinx.coroutines.flow.Flow

interface PneumoniaUseCase {
    fun getAllPicture(): Flow<Resource<List<Picture>>>
    fun getLogin(username: String, password: String): Flow<Resource<List<Login>>>
    fun getAllHistory(): Flow<Resource<List<History>>>
}