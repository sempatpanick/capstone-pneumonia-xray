package com.alvex.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPatientResponse(

	@field:SerializedName("data")
	val data: List<ListPatientResponseItem>
)

data class ListPatientResponseItem(

	@field:SerializedName("id_pasien")
	val id: String,

	@field:SerializedName("nm_pasien")
	val nama: String,

	@field:SerializedName("telp_pasien")
	val telp: String,

	@field:SerializedName("almt_pasien")
	val alamat: String,

	@field:SerializedName("tgllahir_pasien")
	val tanggal_lahir: String
)
