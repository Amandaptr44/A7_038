package com.example.ujianakhir.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Terapis(
    @SerialName("id_terapis")
    val id_terapis: Int,

    @SerialName("nama_terapis")
    val nama_terapis: String,

    val spesialisasi: String,

    @SerialName("nomor_izin_praktik")
    val nomor_izin_praktik: String,
)


