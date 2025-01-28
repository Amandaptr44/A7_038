package com.example.ujianakhir.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jenisterapi(
    @SerialName("id_jenisterapi")
    val id_jenisterapi: Int,

    @SerialName("nama_jenis_terapi")
    val nama_jenis_terapi: String,

    @SerialName("deskripsi_terapi")
    val deskripsi_terapi: String,
)



