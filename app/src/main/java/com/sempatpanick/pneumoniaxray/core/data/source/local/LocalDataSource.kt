package com.sempatpanick.pneumoniaxray.core.data.source.local

import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.LoginEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.room.PneumoniaDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pneumoniaDao: PneumoniaDao) {
    fun getAllPicture(): Flow<List<PictureEntity>> = pneumoniaDao.getAllPicture()
    fun deletePicture() = pneumoniaDao.deletePicture()
    suspend fun insertPicture(pictureList: List<PictureEntity>) =
        pneumoniaDao.insertPicture(pictureList)

    fun getLogin(): Flow<List<LoginEntity>> = pneumoniaDao.getLogin()
    fun deleteLogin() = pneumoniaDao.deleteLogin()
    suspend fun insertLogin(login: List<LoginEntity>) = pneumoniaDao.insertLogin(login)

}