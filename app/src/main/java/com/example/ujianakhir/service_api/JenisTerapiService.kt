package com.example.ujianakhir.service_api

import com.example.ujianakhir.model.Jenisterapi
import com.example.ujianakhir.model.Terapis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface JenisTerapiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("jenisterapiinsert.php")
    suspend fun insertJenisTerapi(@Body jenisterapi: Jenisterapi)

    @GET("jenisterapiread.php")
    suspend fun getAllJenisTerapi():  List<Jenisterapi>

    @GET("jenisterapidetail.php/{id_jenisterapi}")
    suspend fun getJenisTerapiById(@retrofit2.http.Query("id_jenisterapi") idJenisTerapi: Int): Jenisterapi

    @PUT("jenisterapiedit.php/{id_jenisterapi}")
    suspend fun updateJenisTerapi(@retrofit2.http.Query("id_jenisterapi") idJenisTerapi: Int, @Body jenisterapi: Jenisterapi)

    @DELETE("jenisterapidelete.php/{id_jenisterapi}")
    suspend fun deleteJenisTerapi(@retrofit2.http.Query("id_jenis_terapi") idJenisTerapi: Int): Response<Void>
}