package com.example.ujianakhir.ui.viewmodel.terapisVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.ujianakhir.Repository.TerapisRepository
import com.example.ujianakhir.model.Terapis
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeTerapisUiState{
    data class Success(val terapis: List<Terapis>): HomeTerapisUiState()
    object Error : HomeTerapisUiState()
    object Loading : HomeTerapisUiState()
}

class HomeTerapisViewModel(private val terapisRepository: TerapisRepository): ViewModel() {
    var terapisUIState: HomeTerapisUiState by mutableStateOf(HomeTerapisUiState.Loading)
        private set

    init {
        getTerapis()
    }

    fun getTerapis() {
        viewModelScope.launch {
            terapisUIState = HomeTerapisUiState.Loading
            terapisUIState = try {
                HomeTerapisUiState.Success(terapisRepository.getTerapis())
            } catch (e: IOException) {
                HomeTerapisUiState.Error
            } catch (e: HttpException) {
                HomeTerapisUiState.Error
            }
        }
    }

    fun deleteTerapis(idTerapis : Int) {
        viewModelScope.launch {
            try {
                terapisRepository.deleteTerapis(idTerapis)
            } catch (e: IOException) {
                HomeTerapisUiState.Error
            } catch (e: HttpException) {
                HomeTerapisUiState.Error
            }
        }
    }
}