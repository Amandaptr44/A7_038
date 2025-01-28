package com.example.ujianakhir.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasienSesiTerapi(
    @SerialName("nama_terapis")
    val nama_terapis: String,
)

