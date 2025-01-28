package com.example.ujianakhir.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pasien(

    @SerialName("id_pasien")
    val id_pasien: Int,

    @SerialName("nama_pasien")
    val nama_pasien: String,

    val alamat: String,

    @SerialName("no_telepon")
    val no_telepon: String,

    @SerialName("tanggal_lahir")
    val tanggal_lahir: String,

    @SerialName("riwayat_medikal")
    val riwayat_medikal: String
)



