package com.alvex.pneumoniaxray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScanReq(
    var idPatient: String,
    var idDoctor: String,
    var idImage: String
) : Parcelable