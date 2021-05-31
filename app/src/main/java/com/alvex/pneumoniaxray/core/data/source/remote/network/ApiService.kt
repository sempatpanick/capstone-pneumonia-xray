package com.alvex.pneumoniaxray.core.data.source.remote.network

import com.alvex.pneumoniaxray.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data-image.php")
    suspend fun getListPicture(): ListPictureResponse

    @GET("data-patient.php")
    suspend fun getListPatient(@Query("query") query: String): ListPatientResponse

    @GET("data-doctor.php")
    suspend fun getLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): DoctorResponse

    @GET("data-history.php")
    suspend fun getListHistory(): ListHistoryResponse

    @GET("scan.php")
    suspend fun getScan(
        @Query("id_patient") id_patient: String,
        @Query("id_doctor") id_doctor: String,
        @Query("id_image") id_image: String
    ): ScanResponse
}
