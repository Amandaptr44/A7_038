package com.example.ujianakhir.model

import kotlinx.serialization.Serializable

@Serializable
data class SesiTerapi(

    val id_sesi: Int,

    val id_pasien: String,

    val id_terapis: String,

    val id_jenisterapi: String,

    val tanggal_sesi: String,

    val catatan_sesi: String
)



