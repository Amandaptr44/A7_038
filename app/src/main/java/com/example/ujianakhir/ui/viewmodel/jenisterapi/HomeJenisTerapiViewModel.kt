package com.example.ujianakhir.ui.viewmodel.jenisterapi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.model.Jenisterapi
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeJenisTerapiUiState{
    data class Success(val jenisterapi: List<Jenisterapi>): HomeJenisTerapiUiState()
    object Error : HomeJenisTerapiUiState()
    object Loading : HomeJenisTerapiUiState()
}

class HomeJenisTerapiViewModel(private val jenisTerapiRepository: JenisTerapiRepository): ViewModel() {
    var jenisterapiUIState: HomeJenisTerapiUiState by mutableStateOf(HomeJenisTerapiUiState.Loading)
        private set

    init {
        getJenisTerapi()
    }

    fun getJenisTerapi() {
        viewModelScope.launch {
            jenisterapiUIState = HomeJenisTerapiUiState.Loading
            jenisterapiUIState = try {
                HomeJenisTerapiUiState.Success(jenisTerapiRepository.getJenisTerapi())
            } catch (e: IOException) {
                HomeJenisTerapiUiState.Error
            } catch (e: HttpException) {
                HomeJenisTerapiUiState.Error
            }
        }
    }

    fun deleteJenisTerapi(idJenisTerapi : Int) {
        viewModelScope.launch {
            try {
                jenisTerapiRepository.deleteJenisTerapi(idJenisTerapi)
            } catch (e: IOException) {
                HomeJenisTerapiUiState.Error
            } catch (e: HttpException) {
                HomeJenisTerapiUiState.Error
            }
        }
    }
}