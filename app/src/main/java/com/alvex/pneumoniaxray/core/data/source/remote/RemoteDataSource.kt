package com.alvex.pneumoniaxray.core.data.source.remote

import android.util.Log
import com.alvex.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.alvex.pneumoniaxray.core.data.source.remote.network.ApiService
import com.alvex.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.alvex.pneumoniaxray.core.data.source.remote.response.DataHistory
import com.alvex.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
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

    fun getLogin(username: String, password: String): Flow<ApiResponse<List<DataDoctor>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getLogin(username, password)
                if (response.status) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllHistory(): Flow<ApiResponse<List<DataHistory>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListHistory()
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