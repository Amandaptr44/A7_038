package com.example.ujianakhir.ui.viewmodel.terapisVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.TerapisRepository
import com.example.ujianakhir.model.Terapis
import kotlinx.coroutines.launch

class InsertTerapisViewModel (private val terapisRepository: TerapisRepository): ViewModel(){
    var uiState by mutableStateOf(InsertTerapisUiState())
        private set

    fun updateInsertTerapisState(insertTerapisUiEvent: InsertTerapisUiEvent){
        uiState = InsertTerapisUiState(insertTerapisUiEvent = insertTerapisUiEvent)
    }

    suspend fun insertTerapis(){
        viewModelScope.launch {
            try {
                terapisRepository.insertTerapis(uiState.insertTerapisUiEvent.toTerapis())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertTerapisUiState(
    val insertTerapisUiEvent: InsertTerapisUiEvent = InsertTerapisUiEvent()
)

data class InsertTerapisUiEvent(
    val idTerapis: Int = 0,
    val namaTerapis: String = "",
    val spesialisasi: String = "",
    val nomorIzin: String = "",
)

fun InsertTerapisUiEvent.toTerapis(): Terapis = Terapis(
    idTerapis = idTerapis,
    namaTerapis = namaTerapis,
    spesialisasi = spesialisasi,
    nomorIzin = nomorIzin,
)

fun Terapis.toTerapisUiState():InsertTerapisUiState = InsertTerapisUiState(
    insertTerapisUiEvent = toInsertTerapisUiEvent()
)

fun Terapis.toInsertTerapisUiEvent():InsertTerapisUiEvent = InsertTerapisUiEvent(
    idTerapis = idTerapis,
    namaTerapis = namaTerapis,
    spesialisasi = spesialisasi,
    nomorIzin = nomorIzin,
)

