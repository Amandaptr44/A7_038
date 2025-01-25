package com.example.ujianakhir.ui.viewmodel.pasienVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.model.Pasien
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
    val idPasien: Int = 0,
    val namaPasien: String = "",
    val alamat: String = "",
    val nomorTelepon: String = "",
    val tanggalLahir: String = "",
    val riwayatMedikal: String = "",
)

fun InsertUiEvent.toPasien(): Pasien = Pasien(
    idPasien = idPasien,
    namaPasien = namaPasien,
    alamat = alamat,
    nomorTelepon = nomorTelepon,
    tanggalLahir = tanggalLahir,
    riwayatMedikal = riwayatMedikal
)

fun Pasien.toUiStatePasien():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pasien.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idPasien = idPasien,
    namaPasien = namaPasien,
    alamat = alamat,
    nomorTelepon = nomorTelepon,
    tanggalLahir = tanggalLahir,
    riwayatMedikal = riwayatMedikal
)

