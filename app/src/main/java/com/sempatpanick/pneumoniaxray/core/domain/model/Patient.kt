package com.sempatpanick.pneumoniaxray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Patient(
    val nomorPasien: String,
    val telp: String,
    val nama: String,
    val alamat: String
) : Parcelable