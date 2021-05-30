package com.sempatpanick.pneumoniaxray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    val id: String,
    val nama: String,
    val url: String
) : Parcelable