package com.example.ujianakhir.ui.viewmodel.sesiterapi

import com.example.ujianakhir.ui.viewmodel.jenisterapi.InsertJenisTerapiUiEvent
import com.example.ujianakhir.ui.viewmodel.jenisterapi.InsertJenisTerapiUiState
import com.example.ujianakhir.ui.viewmodel.jenisterapi.toJenisTerapi
import com.example.ujianakhir.ui.viewmodel.jenisterapi.toJenisTerapiUiState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.Repository.SesiTerapiRepository
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiJenisTerapiUpdate
import com.example.ujianakhir.ui.view.sesiterapi.DestinasiSesiTerapiUpdate
import kotlinx.coroutines.launch

class UpdateSesiTerapiViewModel(
    savedStateHandle: SavedStateHandle,
    private val SesiTerapiRepository: SesiTerapiRepository
): ViewModel(){
    var updateSesiTerapiUiState by mutableStateOf(InsertSesiTerapiUiState())
        private set
    private val _idSesiTerapi: Int = checkNotNull(savedStateHandle[DestinasiSesiTerapiUpdate.ID_SESITERAPI])
    init {
        viewModelScope.launch {
            updateSesiTerapiUiState = SesiTerapiRepository.getSesiTerapiById(_idSesiTerapi)
                .toSesiTerapiUiState()
        }
    }
    fun updateInsertSesiTerapiState(insertSesiTerapiUiEvent: InsertSesiTerapiUiEvent){
        updateSesiTerapiUiState = InsertSesiTerapiUiState(insertSesiTerapiUiEvent = insertSesiTerapiUiEvent)
    }
    suspend fun updateSesiTerapi(){
        viewModelScope.launch {
            try {
                SesiTerapiRepository.updateSesiTerapi(_idSesiTerapi, updateSesiTerapiUiState.insertSesiTerapiUiEvent.toSesiTerapi())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}