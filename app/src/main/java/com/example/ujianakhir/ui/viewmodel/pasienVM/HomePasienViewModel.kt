package com.example.ujianakhir.ui.viewmodel.pasienVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.model.Pasien
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePasienUiState {
    data class Success(val pasien: List<Pasien>) : HomePasienUiState()
    object Error : HomePasienUiState()
    object Loading : HomePasienUiState()
}

class HomePasienViewModel(private val pas: PasienRepository) : ViewModel() {
    var pasienUIState: HomePasienUiState by mutableStateOf(HomePasienUiState.Loading)
        private set

    init {
        getPasien()
    }

    fun getPasien() {
        viewModelScope.launch {
            pasienUIState = HomePasienUiState.Loading
            pasienUIState = try {
                HomePasienUiState.Success(pas.getPasien())
            } catch (e: IOException) {
                HomePasienUiState.Error
            } catch (e: HttpException) {
                HomePasienUiState.Error
            }
        }
    }

    fun insertPasien(pasien: Pasien) {
        viewModelScope.launch {
            pasienUIState = HomePasienUiState.Loading
            try {
                pas.insertPasien(pasien)
                getPasien()
            } catch (e: IOException) {
                HomePasienUiState.Error
            } catch (e: HttpException) {
                HomePasienUiState.Error
            }
        }
    }

    fun deletePasien(idPasien: Int) {
        viewModelScope.launch {
            try {
                pas.deletePasien(idPasien)
            } catch (e: IOException) {
                pasienUIState = HomePasienUiState.Error
            } catch (e: HttpException) {
                pasienUIState = HomePasienUiState.Error
            }
        }
    }
}
