package com.sempatpanick.pneumoniaxray.core.utils

import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PatientEntity
import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPatientResponseItem
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Patient
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

    fun patientMapResponsesToEntities(input: List<ListPatientResponseItem>): List<PatientEntity> {
        val patientList = ArrayList<PatientEntity>()
        input.map {
            val patient = PatientEntity(
                nomorPasien = it.nomorPasien,
                telp = it.telp,
                nama = it.nama,
                alamat = it.alamat
            )
            patientList.add(patient)
        }
        return patientList
    }

    fun patientMapEntitiesToDomain(input: List<PatientEntity>): List<Patient> =
        input.map {
            Patient(
                nomorPasien = it.nomorPasien,
                telp = it.telp,
                nama = it.nama,
                alamat = it.alamat
            )
        }
}