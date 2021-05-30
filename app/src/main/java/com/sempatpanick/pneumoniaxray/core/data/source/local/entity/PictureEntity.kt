package com.sempatpanick.pneumoniaxray.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture")
data class PictureEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_gambar")
    var id: String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "url")
    var url: String,
)