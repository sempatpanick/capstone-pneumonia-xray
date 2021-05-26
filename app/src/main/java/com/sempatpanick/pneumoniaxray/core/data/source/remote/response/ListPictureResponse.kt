package com.sempatpanick.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPictureResponse(

	@field:SerializedName("data")
	val data: List<ListPictureResponseItem>
)

data class ListPictureResponseItem(

	@field:SerializedName("id_gambar")
	val idGambar: String,

	@field:SerializedName("nama_gambar")
	val namaGambar: String,

	@field:SerializedName("lokasi_gambar")
	val lokasiGambar: String
)
