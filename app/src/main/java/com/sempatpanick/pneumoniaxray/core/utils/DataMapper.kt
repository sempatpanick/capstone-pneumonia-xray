package com.sempatpanick.pneumoniaxray.core.utils

import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.HistoryEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.LoginEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataHistory
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.History
import com.sempatpanick.pneumoniaxray.core.domain.model.Login
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture

object DataMapper {

    fun pictureMapResponsesToEntities(input: List<ListPictureResponseItem>): List<PictureEntity> {
        val pictureList = ArrayList<PictureEntity>()
        input.map {
            val picture = PictureEntity(
                id = it.id,
                nama = it.nama,
                url = it.url
            )
            pictureList.add(picture)
        }
        return pictureList
    }

    fun pictureMapEntitiesToDomain(input: List<PictureEntity>): List<Picture> =
        input.map {
            Picture(
                id = it.id,
                nama = it.nama,
                url = it.url
            )
        }

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

    fun historyMapResponsesToEntities(input: List<DataHistory>): List<HistoryEntity> {
        val historyList = ArrayList<HistoryEntity>()
        input.map {
            val history = HistoryEntity(
                idRiwayat = it.idRiwayat,
                idPasien = it.idPasien,
                namaPasien = it.namaPasien,
                telpPasien = it.telpPasien,
                alamatPasien = it.alamatPasien,
                tanggalLahirPasien = it.tanggalLahirPasien,
                idDokter = it.idDokter,
                namaDokter = it.namaDokter,
                idGambar = it.idGambar,
                urlGambar = it.urlGambar,
                prediction = it.prediction
            )
            historyList.add(history)
        }
        return historyList
    }

    fun historyMapEntitiesToDomain(input: List<HistoryEntity>): List<History> =
        input.map {
            History(
                idRiwayat = it.idRiwayat,
                idPasien = it.idPasien,
                namaPasien = it.namaPasien,
                telpPasien = it.telpPasien,
                alamatPasien = it.alamatPasien,
                tanggalLahirPasien = it.tanggalLahirPasien,
                idDokter = it.idDokter,
                namaDokter = it.namaDokter,
                idGambar = it.idGambar,
                urlGambar = it.urlGambar,
                prediction = it.prediction
            )
        }
}