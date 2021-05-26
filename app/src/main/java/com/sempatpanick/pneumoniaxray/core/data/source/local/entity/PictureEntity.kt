package com.sempatpanick.pneumoniaxray.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture")
data class PictureEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idGambar")
    var idGambar: String,

    @ColumnInfo(name = "namaGambar")
    var namaGambar: String,

    @ColumnInfo(name = "lokasiGambar")
    var lokasiGambar: String,
)