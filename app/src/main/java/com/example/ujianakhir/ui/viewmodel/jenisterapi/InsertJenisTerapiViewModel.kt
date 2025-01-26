package com.example.ujianakhir.ui.viewmodel.jenisterapi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.model.Jenisterapi
import kotlinx.coroutines.launch

class InsertJenisTerapiViewModel (private val jenisTerapiRepository: JenisTerapiRepository): ViewModel(){
    var uiState by mutableStateOf(InsertJenisTerapiUiState())
        private set

    fun updateInsertJenisTerapiState(insertJenisTerapiUiEvent: InsertJenisTerapiUiEvent){
        uiState = InsertJenisTerapiUiState(insertJenisTerapiUiEvent = insertJenisTerapiUiEvent)
    }

    suspend fun insertJenisTerapi(){
        viewModelScope.launch {
            try {
                jenisTerapiRepository.insertJenisTerapi(uiState.insertJenisTerapiUiEvent.toJenisTerapi())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertJenisTerapiUiState(
    val insertJenisTerapiUiEvent: InsertJenisTerapiUiEvent = InsertJenisTerapiUiEvent()
)

data class InsertJenisTerapiUiEvent(
    val idJenisTerapi: Int = 0,
    val namaJenisTerapi: String = "",
    val deskripsiTerapi: String = "",
)

fun InsertJenisTerapiUiEvent.toJenisTerapi(): Jenisterapi = Jenisterapi(
    idJenisTerapi = idJenisTerapi,
    namaJenisTerapi = namaJenisTerapi,
    deskripsiTerapi = deskripsiTerapi,
)

fun Jenisterapi.toJenisTerapiUiState():InsertJenisTerapiUiState = InsertJenisTerapiUiState(
    insertJenisTerapiUiEvent = toInsertJenisTerapiUiEvent()
)

fun Jenisterapi.toInsertJenisTerapiUiEvent():InsertJenisTerapiUiEvent = InsertJenisTerapiUiEvent(
    idJenisTerapi = idJenisTerapi,
    namaJenisTerapi = namaJenisTerapi,
    deskripsiTerapi = deskripsiTerapi,
)

