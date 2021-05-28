package com.sempatpanick.pneumoniaxray.core.data.source.remote

import android.util.Log
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiService
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPatientResponseItem
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPicture(): Flow<ApiResponse<List<ListPictureResponseItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListPicture()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllPatient(query: String): Flow<ApiResponse<List<ListPatientResponseItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListPatient(query)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}