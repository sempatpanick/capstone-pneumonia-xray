package com.alvex.pneumoniaxray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    var idRiwayat: String,
    var idPasien: String,
    var namaPasien: String,
    var telpPasien: String,
    var alamatPasien: String,
    var tanggalLahirPasien: String,
    var idDokter: String,
    var namaDokter: String,
    var idGambar: String,
    var urlGambar: String,
    var prediction: String
) : Parcelable