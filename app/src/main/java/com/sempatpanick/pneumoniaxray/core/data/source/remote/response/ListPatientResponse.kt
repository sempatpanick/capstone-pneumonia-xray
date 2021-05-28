package com.sempatpanick.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPatientResponse(

	@field:SerializedName("data")
	val data: List<ListPatientResponseItem>
)

data class ListPatientResponseItem(

	@field:SerializedName("nomor_pasien")
	val nomorPasien: String,

	@field:SerializedName("telp")
	val telp: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("alamat")
	val alamat: String
)
