package com.example.ujianakhir.di


import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.Repository.SesiTerapiRepository
import com.example.ujianakhir.Repository.TerapisRepository
import com.example.ujianakhir.service_api.JenisTerapiService
import com.example.ujianakhir.service_api.PasienService
import com.example.ujianakhir.service_api.SesiTerapiService
import com.example.ujianakhir.service_api.TerapisService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val pasienRepository: PasienRepository
    val terapisRepository: TerapisRepository
    val jenisTerapiRepository: JenisTerapiRepository
    val sesiTerapiRepository: SesiTerapiRepository
}

class TerapiContainer : AppContainer {

    private val baseUrl = "http://192.168.0.115:81/UjianAkhir/" //localhost diganti ip kalau run di hp
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pasienService: PasienService by lazy {
        retrofit.create(PasienService::class.java)
    }

    override val pasienRepository: PasienRepository by lazy {
        PasienRepository.NetworkPasienRepository(pasienService)
    }
//
    private val terapisService: TerapisService by lazy {
        retrofit.create(TerapisService::class.java)
    }

    override val terapisRepository: TerapisRepository by lazy {
        TerapisRepository.NetworkTerapisRepository(terapisService)
    }
//
    private val jenisTerapiService: JenisTerapiService by lazy {
        retrofit.create(JenisTerapiService::class.java)
    }

    override val jenisTerapiRepository: JenisTerapiRepository by lazy {
        JenisTerapiRepository.NetworkJenisTerapiRepository(jenisTerapiService)
    }

//
    private val sesiTerapiService: SesiTerapiService by lazy {
        retrofit.create(SesiTerapiService::class.java)
    }

    override val sesiTerapiRepository: SesiTerapiRepository by lazy {
        SesiTerapiRepository.NetworkSesiTerapiRepository(sesiTerapiService)
    }
}