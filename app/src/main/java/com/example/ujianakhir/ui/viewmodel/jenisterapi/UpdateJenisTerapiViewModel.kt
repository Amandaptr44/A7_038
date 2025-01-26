package com.example.ujianakhir.ui.viewmodel.jenisterapi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiJenisTerapiUpdate
import kotlinx.coroutines.launch

class UpdateJenisTerapiViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisTerapiRepository: JenisTerapiRepository
): ViewModel(){
    var updateJenisTerapiUiState by mutableStateOf(InsertJenisTerapiUiState())
        private set
    private val _idJenisTerapi: Int = checkNotNull(savedStateHandle[DestinasiJenisTerapiUpdate.ID_JENISTERAPI])
    init {
        viewModelScope.launch {
            updateJenisTerapiUiState = jenisTerapiRepository.getJenisTerapiById(_idJenisTerapi)
                .toJenisTerapiUiState()
        }
    }
    fun updateInsertJenisTerapiState(insertJenisTerapiUiEvent: InsertJenisTerapiUiEvent){
        updateJenisTerapiUiState = InsertJenisTerapiUiState(insertJenisTerapiUiEvent = insertJenisTerapiUiEvent)
    }
    suspend fun updateJenisTerapi(){
        viewModelScope.launch {
            try {
                jenisTerapiRepository.updateJenisTerapi(_idJenisTerapi, updateJenisTerapiUiState.insertJenisTerapiUiEvent.toJenisTerapi())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}