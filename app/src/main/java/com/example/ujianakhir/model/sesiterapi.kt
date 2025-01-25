package com.example.ujianakhir.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sesiterapi",
    foreignKeys = [
        ForeignKey(entity = Pasien::class, parentColumns = ["id"], childColumns = ["pasienId"]),
        ForeignKey(entity = Terapis::class, parentColumns = ["id"], childColumns = ["terapisId"]),
        ForeignKey(entity = Jenisterapi::class, parentColumns = ["id"], childColumns = ["jenisterapiId"])
    ]
)
data class SesiTerapi(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idSesiTerapi: Int,
    val idPasien: Int,
    val idTerapis: Int,
    val idJenisTerapi: Int,
    val tanggalsesi: String,
    val catatansesi: String
)
