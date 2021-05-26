package com.sempatpanick.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPictureResponse(

	@field:SerializedName("ListPictureResponse")
	val listPictureResponse: List<ListPictureResponseItem?>? = null
)

data class ListPictureResponseItem(

	@field:SerializedName("id_gambar")
	val idGambar: String? = null,

	@field:SerializedName("nama_gambar")
	val namaGambar: String? = null,

	@field:SerializedName("lokasi_gambar")
	val lokasiGambar: String? = null
)
