package com.sempatpanick.pneumoniaxray.core.data.source.remote.network

import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.DoctorResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPatientResponse
import com.sempatpanick.pneumoniaxray.core.data.source.remote.response.ListPictureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data-gambar.php")
    suspend fun getListPicture(): ListPictureResponse

    @GET("data-pasien.php")
    suspend fun getListPatient(@Query("query") query: String): ListPatientResponse

    @GET("data-dokter.php")
    suspend fun getDoctor(
        @Query("username") username: String,
        @Query("password") password: String
    ): DoctorResponse
}
