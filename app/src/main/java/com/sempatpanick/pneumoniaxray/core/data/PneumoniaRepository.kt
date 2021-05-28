package com.sempatpanick.pneumoniaxray.core.data

import com.sempatpanick.pneumoniaxray.core.data.source.local.LocalDataSource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.RemoteDataSource
import com.sempatpanick.pneumoniaxray.core.data.source.remote.network.ApiResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPatientResponseItem
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Patient
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

    override fun getAllPatient(query: String): Flow<Resource<List<Patient>>> =
        object : NetworkBoundResource<List<Patient>, List<ListPatientResponseItem>>() {
            override fun loadFromDB(): Flow<List<Patient>> {
                return localDataSource.getAllPatient().map {
                    DataMapper.patientMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Patient>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ListPatientResponseItem>>> =
                remoteDataSource.getAllPatient(query)

            override suspend fun saveCallResult(data: List<ListPatientResponseItem>) {
                val patientList = DataMapper.patientMapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.deletePatient() }
                localDataSource.insertPatient(patientList)
            }

        }.asFlow()

}