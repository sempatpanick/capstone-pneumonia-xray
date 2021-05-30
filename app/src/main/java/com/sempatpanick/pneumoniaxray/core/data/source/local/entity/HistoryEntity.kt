package com.sempatpanick.pneumoniaxray.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_riwayat")
    var idRiwayat: String,

    @ColumnInfo(name = "id_pasien")
    var idPasien: String,

    @ColumnInfo(name = "nama_pasien")
    var namaPasien: String,

    @ColumnInfo(name = "telp_pasien")
    var telpPasien: String,

    @ColumnInfo(name = "alamat_pasien")
    var alamatPasien: String,

    @ColumnInfo(name = "tanggal_lahir_pasien")
    var tanggalLahirPasien: String,

    @ColumnInfo(name = "id_dokter")
    var idDokter: String,

    @ColumnInfo(name = "nama_dokter")
    var namaDokter: String,

    @ColumnInfo(name = "id_gambar")
    var idGambar: String,

    @ColumnInfo(name = "url_gambar")
    var urlGambar: String,

    @ColumnInfo(name = "prediction")
    var prediction: String
)