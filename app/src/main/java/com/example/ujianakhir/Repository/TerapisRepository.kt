package com.example.ujianakhir.Repository

import com.example.ujianakhir.model.Terapis
import com.example.ujianakhir.service_api.TerapisService
import okio.IOException


interface TerapisRepository {

    suspend fun insertTerapis(terapis: Terapis)
    suspend fun getTerapis(): List<Terapis>
    suspend fun updateTerapis(idTerapis: Int, terapis: Terapis)
    suspend fun deleteTerapis(idTerapis: Int)
    suspend fun getTerapisById(idTerapis: Int): Terapis

    class NetworkTerapisRepository(
        private val terapisApiService: TerapisService
    ) : TerapisRepository {

        override suspend fun insertTerapis(terapis: Terapis) {
            val response =terapisApiService.insertTerapis(terapis)
        }

        override suspend fun updateTerapis(idTerapis: Int, terapis: Terapis) {
            terapisApiService.updateTerapis(idTerapis, terapis)
        }

        override suspend fun getTerapis(): List<Terapis> =
            terapisApiService.getAllTerapis()

        override suspend fun deleteTerapis(idTerapis: Int) {
            try {
                val response = terapisApiService.deleteTerapis(idTerapis)
                if (!response.isSuccessful) {
                    throw IOException(
                        "Failed to delete terapis. HTTP Status code:" +
                                " ${response.code()}"
                    )
                } else {
                    response.message()
                    println(response.message())
                }
            } catch (e: Exception) {
                throw e
            }
        }

        override suspend fun getTerapisById(idTerapis: Int): Terapis {
            return terapisApiService.getTerapisById(idTerapis)
        }
    }
}
