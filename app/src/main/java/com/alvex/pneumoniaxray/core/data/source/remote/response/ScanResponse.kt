package com.alvex.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("prediction")
	val prediction: String,

	@field:SerializedName("id_patient")
	val idPatient: String
)
