package com.example.ujianakhir.service_api

import com.example.ujianakhir.model.Pasien
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PasienService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("insertpasien.php")
    suspend fun insertPasien(@Body pasien: Pasien)

    @GET("readpasien.php")
    //@GET(".")
    suspend fun getAllPasien():  List<Pasien>

    @GET("detailpasien.php/{id_pasien}")
    suspend fun getPasienById(@Query("id_pasien") idPasien: Int): Pasien

    @PUT("editpasien.php/{id_pasien}")
    //@PUT("{id_pasien}")
    suspend fun updatePasien(@Query("id_pasien") idPasien: Int, @Body pasien: Pasien)

    @DELETE("deletepasien.php/{id_pasien}")
    //@DELETE("{id_pasien}")
    suspend fun deletePasien(@Query("id_pasien") idPasien: Int): Response<Void>
}
