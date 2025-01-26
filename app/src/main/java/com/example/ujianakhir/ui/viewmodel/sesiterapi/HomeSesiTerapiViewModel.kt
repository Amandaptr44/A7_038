package com.example.ujianakhir.ui.viewmodel.sesiterapi


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.ujianakhir.Repository.SesiTerapiRepository
import com.example.ujianakhir.model.SesiTerapi
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeSesiTerapiUiState{
    data class Success(val sesiTerapi: List<SesiTerapi>): HomeSesiTerapiUiState()
    object Error : HomeSesiTerapiUiState()
    object Loading : HomeSesiTerapiUiState()
}

class HomeSesiTerapiViewModel(private val sesiTerapiRepository: SesiTerapiRepository): ViewModel() {
    var sesiterapiUIState: HomeSesiTerapiUiState by mutableStateOf(HomeSesiTerapiUiState.Loading)
        private set

    init {
        getSesiTerapi()
    }

    fun getSesiTerapi() {
        viewModelScope.launch {
            sesiterapiUIState = HomeSesiTerapiUiState.Loading
            sesiterapiUIState = try {
                HomeSesiTerapiUiState.Success(sesiTerapiRepository.getSesiTerapi())
            } catch (e: IOException) {
                HomeSesiTerapiUiState.Error
            } catch (e: HttpException) {
                HomeSesiTerapiUiState.Error
            }
        }
    }

    fun deleteSesiTerapi(idSesiTerapi : Int) {
        viewModelScope.launch {
            try {
                sesiTerapiRepository.deleteSesiTerapi(idSesiTerapi)
            } catch (e: IOException) {
                HomeSesiTerapiUiState.Error
            } catch (e: HttpException) {
                HomeSesiTerapiUiState.Error
            }
        }
    }
}