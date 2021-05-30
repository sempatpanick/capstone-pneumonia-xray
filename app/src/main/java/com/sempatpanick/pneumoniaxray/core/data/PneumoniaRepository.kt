package com.sempatpanick.pneumoniaxray.core.data

import com.sempatpanick.pneumoniaxray.core.data.source.local.LocalDataSource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.RemoteDataSource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Login
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import com.sempatpanick.pneumoniaxray.core.domain.repository.IPneumoniaRepository
import com.sempatpanick.pneumoniaxray.core.utils.AppExecutors
import com.sempatpanick.pneumoniaxray.core.utils.DataMapper
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
}