package com.sempatpanick.pneumoniaxray.core.utils

import com.sempatpanick.pneumoniaxray.core.data.source.local.entity.PictureEntity
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponseItem
import com.sempatpanick.pneumoniaxray.core.domain.model.Picture

object DataMapper {

    fun mapResponsesToEntities(input: List<ListPictureResponseItem>): List<PictureEntity> {
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

    fun mapEntitiesToDomain(input: List<PictureEntity>): List<Picture> =
        input.map {
            Picture(
                idGambar = it.idGambar,
                namaGambar = it.namaGambar,
                lokasiGambar = it.lokasiGambar
            )
        }

    fun mapDomainToEntity(input: Picture) = PictureEntity(
        idGambar = input.idGambar,
        namaGambar = input.namaGambar,
        lokasiGambar = input.lokasiGambar
    )
}