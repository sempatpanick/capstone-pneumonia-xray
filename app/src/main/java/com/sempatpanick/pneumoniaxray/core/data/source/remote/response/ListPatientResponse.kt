package com.sempatpanick.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPatientResponse(

	@field:SerializedName("ListPatientResponse")
	val listPatientResponse: List<ListPatientResponseItem?>? = null
)

data class ListPatientResponseItem(

	@field:SerializedName("nomor_pasien")
	val nomorPasien: String? = null,

	@field:SerializedName("telp")
	val telp: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
