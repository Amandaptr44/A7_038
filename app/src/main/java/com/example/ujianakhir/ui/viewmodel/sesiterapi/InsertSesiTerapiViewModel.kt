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
    val idSesiTerapi: Int = 0,
    val idPasien: Int = 0,
    val idTerapis: Int = 0,
    val idJenisTerapi: Int = 0,
    val tanggalsesi: String = "",
    val catatansesi: String = "",
)

fun InsertSesiTerapiUiEvent.toSesiTerapi(): SesiTerapi = SesiTerapi(
    idSesiTerapi = idSesiTerapi,
    idPasien = idPasien,
    idTerapis = idTerapis,
    idJenisTerapi = idJenisTerapi,
    tanggalsesi = tanggalsesi,
    catatansesi = catatansesi,
)

fun SesiTerapi.toSesiTerapiUiState():InsertSesiTerapiUiState = InsertSesiTerapiUiState(
    insertSesiTerapiUiEvent = toInsertSesiTerapiUiEvent()
)

fun SesiTerapi.toInsertSesiTerapiUiEvent():InsertSesiTerapiUiEvent = InsertSesiTerapiUiEvent(
    idSesiTerapi = idSesiTerapi,
    idPasien = idPasien,
    idTerapis = idTerapis,
    idJenisTerapi = idJenisTerapi,
    tanggalsesi = tanggalsesi,
    catatansesi = catatansesi,
)

