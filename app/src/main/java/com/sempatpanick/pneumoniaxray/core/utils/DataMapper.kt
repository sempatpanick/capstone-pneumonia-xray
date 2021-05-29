package com.sempatpanick.pneumoniaxray.core.utils

import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.DoctorEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DataDoctor
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Doctor
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

    fun doctorMapResponsesToEntities(input: List<DataDoctor>): List<DoctorEntity> {
        val doctorList = ArrayList<DoctorEntity>()

        input.map {
            val doctor = DoctorEntity(
                idDoctor = it.idDoctor,
                nama = it.nama,
                username = it.username,
                password = it.password
            )
            doctorList.add(doctor)
        }
        return doctorList
    }

    fun doctorMapEntitiesToDomain(input: List<DoctorEntity>): List<Doctor> =
        input.map {
            Doctor(
                idDoctor = it.idDoctor,
                nama = it.nama,
                username = it.username,
                password = it.password
            )
        }

    fun doctorMapDomainToEntity(input: Picture) = PictureEntity(
        idGambar = input.idGambar,
        namaGambar = input.namaGambar,
        lokasiGambar = input.lokasiGambar
    )
}