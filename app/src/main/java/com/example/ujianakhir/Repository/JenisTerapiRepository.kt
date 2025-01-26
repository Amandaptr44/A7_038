package com.example.ujianakhir.Repository

import com.example.ujianakhir.model.Jenisterapi
import com.example.ujianakhir.service_api.JenisTerapiService
import java.io.IOException

interface JenisTerapiRepository {

    suspend fun insertJenisTerapi(jenisTerapi: Jenisterapi)
    suspend fun getJenisTerapi(): List<Jenisterapi>
    suspend fun updateJenisTerapi(idJenisTerapi: Int, jenisTerapi: Jenisterapi)
    suspend fun deleteJenisTerapi(idJenisTerapi: Int)
    suspend fun getJenisTerapiById(idJenisTerapi: Int): Jenisterapi

    class NetworkJenisTerapiRepository(
        private val jenisterapiApiService: JenisTerapiService
    ) : JenisTerapiRepository {

        override suspend fun insertJenisTerapi(jenisTerapi: Jenisterapi) {
            jenisterapiApiService.insertJenisTerapi(jenisTerapi)
        }

        override suspend fun updateJenisTerapi(idJenisTerapi: Int, jenisTerapi: Jenisterapi) {
            jenisterapiApiService.updateJenisTerapi(idJenisTerapi, jenisTerapi)
        }

        override suspend fun getJenisTerapi(): List<Jenisterapi> =
            jenisterapiApiService.getAllJenisTerapi()

        override suspend fun deleteJenisTerapi(idJenisTerapi: Int) {
            try {
                val response = jenisterapiApiService.deleteJenisTerapi(idJenisTerapi)
                if (!response.isSuccessful) {
                    throw IOException(
                        "Failed to delete jenisterapi. HTTP Status code:" +
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

        override suspend fun getJenisTerapiById(idJenisTerapi: Int): Jenisterapi {
            return jenisterapiApiService.getJenisTerapiById(idJenisTerapi)
        }
    }
}



