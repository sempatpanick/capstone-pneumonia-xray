package com.sempatpanick.pneumoniaxray.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor")
data class DoctorEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idDoctor")
    var idDoctor: String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String
)