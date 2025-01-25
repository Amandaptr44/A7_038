package com.example.ujianakhir.ui.viewmodel.pasienVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.ui.view.pasien.DestinasiPasienUpdate
import kotlinx.coroutines.launch

class UpdatePasienViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasienRepository: PasienRepository
): ViewModel(){
    var updateUiState by mutableStateOf(InsertUiState())
        private set
    private val _idPasien: Int = checkNotNull(savedStateHandle[DestinasiPasienUpdate.ID_PASIEN])
    init {
        viewModelScope.launch {
            updateUiState = pasienRepository.getPasienById(_idPasien)
                .toUiStatePasien()
        }
    }
    fun updateInsertPasienState(insertUiEvent: InsertUiEvent){
        updateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }
    suspend fun updatePasien(){
        viewModelScope.launch {
            try {
                pasienRepository.updatePasien(_idPasien, updateUiState.insertUiEvent.toPasien())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}