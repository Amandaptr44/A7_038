package com.example.ujianakhir.Repository

import com.example.ujianakhir.model.Jenisterapi
import com.example.ujianakhir.service_api.JenisTerapiService
import java.io.IOException

interface JenisTerapiRepository {

    suspend fun insertJenisTerapi(jenisTerapi: Jenisterapi)
    suspend fun getJenisTerapi(): List<Jenisterapi>
    suspend fun updateJenisTerapi(id_jenisterapi: Int, jenisTerapi: Jenisterapi)
    suspend fun deleteJenisTerapi(id_jenisterapi: Int)
    suspend fun getJenisTerapiById(id_jenisterapi: Int): Jenisterapi

    class NetworkJenisTerapiRepository(
        private val jenisterapiApiService: JenisTerapiService
    ) : JenisTerapiRepository {

        override suspend fun insertJenisTerapi(jenisTerapi: Jenisterapi) {
            jenisterapiApiService.insertJenisTerapi(jenisTerapi)
        }

        override suspend fun updateJenisTerapi(id_jenisterapi: Int, jenisTerapi: Jenisterapi) {
            jenisterapiApiService.updateJenisTerapi(id_jenisterapi, jenisTerapi)
        }

        override suspend fun getJenisTerapi(): List<Jenisterapi> =
            jenisterapiApiService.getAllJenisTerapi()

        override suspend fun deleteJenisTerapi(id_jenisterapi: Int) {
            try {
                val response = jenisterapiApiService.deleteJenisTerapi(id_jenisterapi)
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

        override suspend fun getJenisTerapiById(id_jenis_terapi: Int): Jenisterapi {
            return jenisterapiApiService.getJenisTerapiById(id_jenis_terapi)
        }
    }
}



