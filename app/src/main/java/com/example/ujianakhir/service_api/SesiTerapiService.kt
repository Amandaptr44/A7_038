package com.example.ujianakhir.service_api

import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.model.SesiTerapi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SesiTerapiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("sesiterapiinsert.php")
    suspend fun insertSesiTerapi(@Body sesiTerapi: SesiTerapi)

    @GET("sesiterapiread.php")
    //@GET(".")
    suspend fun getAllSesiTerapi():  List<SesiTerapi>

    @GET("sesiterapidetail.php/{id_sesiterspi}")
    suspend fun getSesiTerapiById(@Query("id_sesiterapi") idSesiTerapi: Int): SesiTerapi

    @PUT("sesiterapiedit.php/{id_sesiterapi}")
    //@PUT("{id_pasien}")
    suspend fun updateSesiTerapi(@Query("id_sesiterapi") idSesiTerapi: Int, @Body sesiTerapi: SesiTerapi)

    @DELETE("sesiterapidelete.php/{id_sesiterapi}")
    //@DELETE("{id_pasien}")
    suspend fun deleteSesiTerapi(@Query("id_sesiterapi") idSesiTerapi: Int): Response<Void>
}


