package com.example.ujianakhir.ui.viewmodel.sesiterapi


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.SesiTerapiRepository
import com.example.ujianakhir.model.SesiTerapi
import com.example.ujianakhir.model.Terapis
import kotlinx.coroutines.launch

class InsertSesiTerapiViewModel (private val sesiTerapiRepository: SesiTerapiRepository): ViewModel(){
    var uiState by mutableStateOf(InsertSesiTerapiUiState())
        private set

    fun updateInsertSesiTerapiState(insertSesiTerapiUiEvent: InsertSesiTerapiUiEvent){
        uiState = InsertSesiTerapiUiState(insertSesiTerapiUiEvent = insertSesiTerapiUiEvent)
    }

    suspend fun insertSesiTerapi(){
        viewModelScope.launch {
            try {
                sesiTerapiRepository.insertSesiTerapi(uiState.insertSesiTerapiUiEvent.toSesiTerapi())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertSesiTerapiUiState(
    val insertSesiTerapiUiEvent: InsertSesiTerapiUiEvent = InsertSesiTerapiUiEvent()
)

data class InsertSesiTerapiUiEvent(
    val id_sesi: Int = 0,
    val id_pasien: Int = 0,
    val id_terapis: Int = 0,
    val id_jenis_terapi: Int = 0,
    val tanggal_sesi: String = "",
    val catatan_sesi: String = "",
)

fun InsertSesiTerapiUiEvent.toSesiTerapi(): SesiTerapi = SesiTerapi(
    id_sesi = id_sesi,
    id_pasien = id_pasien,
    id_terapis = id_terapis,
    id_jenis_terapi = id_jenis_terapi,
    tanggal_sesi = tanggal_sesi,
    catatan_sesi = catatan_sesi,
)

fun SesiTerapi.toSesiTerapiUiState():InsertSesiTerapiUiState = InsertSesiTerapiUiState(
    insertSesiTerapiUiEvent = toInsertSesiTerapiUiEvent()
)

fun SesiTerapi.toInsertSesiTerapiUiEvent():InsertSesiTerapiUiEvent = InsertSesiTerapiUiEvent(
    id_sesi = id_sesi,
    id_pasien = id_pasien,
    id_terapis = id_terapis,
    id_jenis_terapi = id_jenis_terapi,
    tanggal_sesi = tanggal_sesi,
    catatan_sesi = catatan_sesi,
)

