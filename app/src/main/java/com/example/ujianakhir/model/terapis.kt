package com.example.ujianakhir.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Terapis(
    @SerialName("id_terapis")
    val idTerapis: Int,

    @SerialName("nama_terapis")
    val namaTerapis: String,

    val spesialisasi: String,

    @SerialName("nomor_izin")
    val nomorIzin: String,
)


