package com.example.ujianakhir.ui.viewmodel.sesiterapi

import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertUiEvent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.Repository.SesiTerapiRepository
import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.model.SesiTerapi
import kotlinx.coroutines.launch


class DetailSesiTerapiViewModel(private val sesiTerapiRepository: SesiTerapiRepository) : ViewModel() {

    var uiState by mutableStateOf(DetailUiStateSesiTerapi())
        private set

    fun fetchDetailSesiTerapi(id_sesi: Int ) {
        viewModelScope.launch {
            uiState = DetailUiStateSesiTerapi(isLoading = true)
            try {
                val sesiTerapi = sesiTerapiRepository.getSesiTerapiById(id_sesi)
                uiState = DetailUiStateSesiTerapi(detailUiEventSesiTerapi = sesiTerapi.toDetailUiEventSesiTerapi())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailUiStateSesiTerapi(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }

}

data class DetailUiStateSesiTerapi(
    val detailUiEventSesiTerapi: InsertSesiTerapiUiEvent = InsertSesiTerapiUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailUiEventSesiTerapi != InsertSesiTerapiUiEvent()
}

fun SesiTerapi.toDetailUiEventSesiTerapi(): InsertSesiTerapiUiEvent {
    return InsertSesiTerapiUiEvent(
        id_sesi = id_sesi,
        id_pasien = id_pasien,
        id_terapis = id_terapis,
        id_jenis_terapi = id_jenis_terapi,
        tanggal_sesi = tanggal_sesi,
        catatan_sesi = catatan_sesi
    )
}
