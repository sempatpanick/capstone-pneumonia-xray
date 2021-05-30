package com.alvex.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DoctorResponse(

	@field:SerializedName("data")
	val data: List<DataDoctor>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataDoctor(

	@field:SerializedName("id_dokter")
	val idDoctor: String,

	@field:SerializedName("nm_dokter")
	val nama: String,

	@field:SerializedName("username_dokter")
	val username: String,

	@field:SerializedName("password_dokter")
	val password: String
)
