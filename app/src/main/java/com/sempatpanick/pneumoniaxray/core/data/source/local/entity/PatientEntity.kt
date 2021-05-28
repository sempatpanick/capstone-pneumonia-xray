package com.sempatpanick.pneumoniaxray.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient")
data class PatientEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nomorPasien")
    var nomorPasien: String,

    @ColumnInfo(name = "telp")
    var telp: String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "alamat")
    var alamat: String
)