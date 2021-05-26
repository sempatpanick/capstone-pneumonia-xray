package com.sempatpanick.pneumoniaxray.core.data.source.remote.network

import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponse
import retrofit2.http.GET

interface ApiService {
    @GET("data-gambar.php")
    suspend fun getListPicture(): ListPictureResponse
}
