package com.sempatpanick.pneumoniaxray.core.data

import com.sempatpanick.pneumoniaxray.core.data.source.local.LocalDataSource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.RemoteDataSource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture
import com.sempatpanick.pneumoniaxray.core.domain.repository.IPneumoniaRepository
import com.sempatpanick.pneumoniaxray.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PneumoniaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPneumoniaRepository {

    override fun getAllPicture(): Flow<Resource<List<Picture>>> =
        object : NetworkBoundResource<List<Picture>, List<ListPictureResponseItem>>() {
            override fun loadFromDB(): Flow<List<Picture>> {
                return localDataSource.getAllPicture().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Picture>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ListPictureResponseItem>>> =
                remoteDataSource.getAllPicture()

            override suspend fun saveCallResult(data: List<ListPictureResponseItem>) {
                val pictureList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPicture(pictureList)
            }

        }.asFlow()

}