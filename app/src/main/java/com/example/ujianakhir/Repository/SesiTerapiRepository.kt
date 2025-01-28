package com.example.ujianakhir.Repository

import com.example.ujianakhir.model.SesiTerapi
import com.example.ujianakhir.service_api.SesiTerapiService
import com.example.ujianakhir.service_api.TerapisService
import okio.IOException


interface SesiTerapiRepository {

    suspend fun insertSesiTerapi(sesiTerapi: SesiTerapi)
    suspend fun getSesiTerapi(): List<SesiTerapi>
    suspend fun updateSesiTerapi(idSesiTerapi: Int, sesiTerapi: SesiTerapi)
    suspend fun deleteSesiTerapi(idSesiTerapi: Int)
    suspend fun getSesiTerapiById(idSesiTerapi: Int): SesiTerapi

    class NetworkSesiTerapiRepository(
        private val sesiterapiApiService: SesiTerapiService
    ) : SesiTerapiRepository {

        override suspend fun insertSesiTerapi(sesiTerapi: SesiTerapi) {
            sesiterapiApiService.insertSesiTerapi(sesiTerapi)
        }

        override suspend fun updateSesiTerapi(id_sesi: Int, sesiTerapi: SesiTerapi) {
            sesiterapiApiService.updateSesiTerapi(id_sesi, sesiTerapi)
        }

        override suspend fun getSesiTerapi(): List<SesiTerapi> =
            sesiterapiApiService.getAllSesiTerapi()

        override suspend fun deleteSesiTerapi(id_sesi: Int) {
            try {
                val response = sesiterapiApiService.deleteSesiTerapi(id_sesi)
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

        override suspend fun getSesiTerapiById(idSesiTerapi: Int): SesiTerapi {
            return sesiterapiApiService.getSesiTerapiById(idSesiTerapi)
        }
    }
}
