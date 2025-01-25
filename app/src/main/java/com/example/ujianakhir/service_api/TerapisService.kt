package com.example.ujianakhir.service_api

import com.example.ujianakhir.model.Terapis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface TerapisService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("terapisinsert.php")
    suspend fun insertTerapis(@Body terapis: Terapis)

    @GET("terapisread.php")
    suspend fun getAllTerapis():  List<Terapis>

    @GET("terapisdetail.php/{id_terapis}")
    suspend fun getTerapisById(@retrofit2.http.Query("id_terapis") idTerapis: Int): Terapis

    @PUT("terapisedit.php/{id_terapis}")
    suspend fun updateTerapis(@retrofit2.http.Query("id_terapis") idTerapis: Int, @Body terapis: Terapis)

    @DELETE("terapisdelete.php/{id_terapis}")
    suspend fun deleteTerapis(@retrofit2.http.Query("id_terapis") idTerapis: Int): Response<Void>
}