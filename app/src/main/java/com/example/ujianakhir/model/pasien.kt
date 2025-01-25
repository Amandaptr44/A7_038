package com.example.ujianakhir.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pasien(

    @SerialName("id_pasien")
    val idPasien: Int,

    @SerialName("nama_pasien")
    val namaPasien: String,

    val alamat: String,

    @SerialName("no_telepon")
    val nomorTelepon: String,

    @SerialName("tanggal_lahir")
    val tanggalLahir: String,

    @SerialName("riwayat_medikal")
    val riwayatMedikal: String
)

