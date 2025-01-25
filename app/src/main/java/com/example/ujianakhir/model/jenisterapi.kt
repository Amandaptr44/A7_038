package com.example.ujianakhir.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jenisterapi(
    @SerialName("id_jenisterapi")
    val idJenisTerapi: Int,

    @SerialName("nama_jenis_terapis")
    val namaJenisTerapi: String,

    @SerialName("deskripsi_terapi")
    val deskripsiTerapi: String,
)



