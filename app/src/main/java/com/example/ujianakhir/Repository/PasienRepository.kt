package com.example.ujianakhir.Repository

import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.service_api.PasienService
import okio.IOException

interface PasienRepository {

    suspend fun insertPasien(pasien: Pasien)
    suspend fun getPasien(): List<Pasien>
    suspend fun updatePasien(idPasien: Int, pasien: Pasien)
    suspend fun deletePasien(idPasien: Int)
    suspend fun getPasienById(idPasien: Int): Pasien

    class NetworkPasienRepository(
        private val pasienApiService: PasienService
    ) : PasienRepository {

        override suspend fun insertPasien(pasien: Pasien) {
            val response = pasienApiService.insertPasien(pasien)
        }

        override suspend fun updatePasien(idPasien: Int, pasien: Pasien) {
            pasienApiService.updatePasien(idPasien, pasien)
        }

        override suspend fun getPasien(): List<Pasien> =
            pasienApiService.getAllPasien()

        override suspend fun deletePasien(idPasien: Int) {
            try {
                val response = pasienApiService.deletePasien(idPasien)
                if (!response.isSuccessful) {
                    throw IOException(
                        "Failed to delete pasien. HTTP Status code:" +
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

        override suspend fun getPasienById(idPasien: Int): Pasien {
            return pasienApiService.getPasienById(idPasien)
        }
    }
}
