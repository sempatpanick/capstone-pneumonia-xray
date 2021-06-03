package com.alvex.pneumoniaxray.core.data

import com.alvex.pneumoniaxray.core.data.source.local.LocalDataSource
import com.alvex.pneumoniaxray.core.data.source.remote.RemoteDataSource
import com.alvex.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.alvex.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.alvex.pneumoniaxray.core.data.source.remote.response.DataHistory
import com.alvex.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.alvex.pneumoniaxray.core.domain.model.History
import com.alvex.pneumoniaxray.core.domain.model.Login
import com.alvex.pneumoniaxray.core.domain.model.Picture
import com.alvex.pneumoniaxray.core.domain.repository.IPneumoniaRepository
import com.alvex.pneumoniaxray.core.utils.AppExecutors
import com.alvex.pneumoniaxray.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PneumoniaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPneumoniaRepository {

    override fun getAllPicture(): Flow<Resource<List<Picture>>> =
        object : NetworkBoundResource<List<Picture>, List<ListPictureResponseItem>>() {
            override fun loadFromDB(): Flow<List<Picture>> {
                return localDataSource.getAllPicture().map {
                    DataMapper.pictureMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Picture>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ListPictureResponseItem>>> =
                remoteDataSource.getAllPicture()

            override suspend fun saveCallResult(data: List<ListPictureResponseItem>) {
                val pictureList = DataMapper.pictureMapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.deletePicture() }
                localDataSource.insertPicture(pictureList)
            }

        }.asFlow()

    override fun getLogin(username: String, password: String): Flow<Resource<List<Login>>> =
        object : NetworkBoundResource<List<Login>, List<DataDoctor>>() {
            override fun loadFromDB(): Flow<List<Login>> {
                return localDataSource.getLogin().map {
                    DataMapper.loginMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Login>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<DataDoctor>>> =
                remoteDataSource.getLogin(username, password)

            override suspend fun saveCallResult(data: List<DataDoctor>) {
                val login = DataMapper.loginMapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.deleteLogin() }
                localDataSource.insertLogin(login)
            }

        }.asFlow()

    override fun getAllHistory(): Flow<Resource<List<History>>> =
        object : NetworkBoundResource<List<History>, List<DataHistory>>() {
            override fun loadFromDB(): Flow<List<History>> {
                return localDataSource.getAllHistory().map {
                    DataMapper.historyMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<History>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<DataHistory>>> =
                remoteDataSource.getAllHistory()

            override suspend fun saveCallResult(data: List<DataHistory>) {
                val historyList = DataMapper.historyMapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.deleteHistory() }
                localDataSource.insertHistory(historyList)
            }

        }.asFlow()
}