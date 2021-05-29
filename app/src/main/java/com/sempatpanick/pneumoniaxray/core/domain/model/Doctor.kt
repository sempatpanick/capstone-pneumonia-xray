package com.sempatpanick.pneumoniaxray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    var idDoctor: String? = null,
    var nama: String? = null,
    var username: String? = null,
    var password: String? = null
) : Parcelable