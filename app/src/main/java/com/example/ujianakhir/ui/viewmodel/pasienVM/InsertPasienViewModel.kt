package com.example.ujianakhir.ui.viewmodel.pasienVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.model.PasienSesiTerapi
import kotlinx.coroutines.launch

class InsertPasienViewModel (private val pasienRepository: PasienRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPasienState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPasien(){
        viewModelScope.launch {
            try {
                pasienRepository.insertPasien(uiState.insertUiEvent.toPasien())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_pasien: Int = 0,
    val nama_pasien: String = "",
    val alamat: String = "",
    val no_telepon: String = "",
    val tanggal_lahir: String = "",
    val riwayat_medikal: String = "",
)

fun InsertUiEvent.toPasien(): Pasien = Pasien(
    id_pasien = id_pasien,
    nama_pasien = nama_pasien,
    alamat = alamat,
    no_telepon = no_telepon,
    tanggal_lahir = tanggal_lahir,
    riwayat_medikal = riwayat_medikal
)

fun Pasien.toUiStatePasien():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pasien.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    id_pasien = id_pasien,
    nama_pasien = nama_pasien,
    alamat = alamat,
    no_telepon = no_telepon,
    tanggal_lahir = tanggal_lahir,
    riwayat_medikal = riwayat_medikal
)

data class InsertPasienSesiUiState(
    val insertPasienSesiUiEvent: InsertPasienSesiUiEvent = InsertPasienSesiUiEvent()
)

data class InsertPasienSesiUiEvent(
    val nama_terapis: String = "",
)

fun InsertPasienSesiUiEvent.toPasienSesi(): PasienSesiTerapi = PasienSesiTerapi(
    nama_terapis = nama_terapis,
)

fun PasienSesiTerapi.toUiStatePasienSesi():InsertPasienSesiUiState = InsertPasienSesiUiState(
    insertPasienSesiUiEvent = toInsertPasienSesiUiEvent()
)

fun PasienSesiTerapi.toInsertPasienSesiUiEvent():InsertPasienSesiUiEvent = InsertPasienSesiUiEvent(
    nama_terapis = nama_terapis,
)








