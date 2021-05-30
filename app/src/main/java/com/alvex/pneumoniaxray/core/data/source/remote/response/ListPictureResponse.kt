package com.alvex.pneumoniaxray.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPictureResponse(

	@field:SerializedName("data")
	val data: List<ListPictureResponseItem>
)

data class ListPictureResponseItem(

	@field:SerializedName("id_gambar")
	val id: String,

	@field:SerializedName("nm_gambar")
	val nama: String,

	@field:SerializedName("url_gambar")
	val url: String
)
