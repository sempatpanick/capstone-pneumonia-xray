package com.sempatpanick.pneumoniaxray.core.utils

import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.LoginEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Login
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture

object DataMapper {

    fun pictureMapResponsesToEntities(input: List<ListPictureResponseItem>): List<PictureEntity> {
        val pictureList = ArrayList<PictureEntity>()
        input.map {
            val picture = PictureEntity(
                idGambar = it.idGambar,
                namaGambar = it.namaGambar,
                lokasiGambar = it.lokasiGambar
            )
            pictureList.add(picture)
        }
        return pictureList
    }

    fun pictureMapEntitiesToDomain(input: List<PictureEntity>): List<Picture> =
        input.map {
            Picture(
                idGambar = it.idGambar,
                namaGambar = it.namaGambar,
                lokasiGambar = it.lokasiGambar
            )
        }

    fun pictureMapDomainToEntity(input: Picture) = PictureEntity(
        idGambar = input.idGambar,
        namaGambar = input.namaGambar,
        lokasiGambar = input.lokasiGambar
    )

    fun loginMapResponsesToEntities(input: List<DataDoctor>): List<LoginEntity> {
        val loginList = ArrayList<LoginEntity>()

        input.map {
            val login = LoginEntity(
                id = it.idDoctor,
                nama = it.nama,
                username = it.username,
                password = it.password
            )
            loginList.add(login)
        }
        return loginList
    }

    fun loginMapEntitiesToDomain(input: List<LoginEntity>): List<Login> =
        input.map {
            Login(
                id = it.id,
                nama = it.nama,
                username = it.username,
                password = it.password
            )
        }

    fun loginMapDomainToEntity(input: Login) = input.id?.let {
        input.nama?.let { it1 ->
            input.username?.let { it2 ->
                input.password?.let { it3 ->
                    LoginEntity(
                        id = it,
                        nama = it1,
                        username = it2,
                        password = it3
                    )
                }
            }
        }
    }
}