package com.example.ujianakhir.ui.viewmodel.terapisVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.TerapisRepository
import com.example.ujianakhir.ui.view.terapis.DestinasiTerapisUpdate
import kotlinx.coroutines.launch

class UpdateTerapisViewModel(
    savedStateHandle: SavedStateHandle,
    private val terapisRepository: TerapisRepository
): ViewModel(){
    var updateTerapisUiState by mutableStateOf(InsertTerapisUiState())
        private set
    private val _idTerapis: Int = checkNotNull(savedStateHandle[DestinasiTerapisUpdate.ID_TERAPIS])
    init {
        viewModelScope.launch {
            updateTerapisUiState = terapisRepository.getTerapisById(_idTerapis)
                .toTerapisUiState()
        }
    }
    fun updateInsertTerapisState(insertTerapisUiEvent: InsertTerapisUiEvent){
        updateTerapisUiState = InsertTerapisUiState(insertTerapisUiEvent = insertTerapisUiEvent)
    }
    suspend fun updateTerapis(){
        viewModelScope.launch {
            try {
                terapisRepository.updateTerapis(_idTerapis, updateTerapisUiState.insertTerapisUiEvent.toTerapis())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}