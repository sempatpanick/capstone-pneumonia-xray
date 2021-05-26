package com.sempatpanick.pneumoniaxray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    val idGambar: String,
    val namaGambar: String,
    val lokasiGambar: String
) : Parcelable