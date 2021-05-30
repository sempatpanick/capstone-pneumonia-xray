package com.alvex.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListHistoryResponse(

	@field:SerializedName("data")
	val data: List<DataHistory>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataHistory(

	@field:SerializedName("id_riwayat")
	val idRiwayat: String,

	@field:SerializedName("id_pasien")
	val idPasien: String,

	@field:SerializedName("nm_pasien")
	val namaPasien: String,

	@field:SerializedName("telp_pasien")
	val telpPasien: String,

	@field:SerializedName("almt_pasien")
	val alamatPasien: String,

	@field:SerializedName("tgllahir_pasien")
	val tanggalLahirPasien: String,

	@field:SerializedName("id_dokter")
	val idDokter: String,

	@field:SerializedName("nm_dokter")
	val namaDokter: String,

	@field:SerializedName("id_gambar")
	val idGambar: String,

	@field:SerializedName("url_gambar")
	val urlGambar: String,

	@field:SerializedName("prediction")
	val prediction: String
)
