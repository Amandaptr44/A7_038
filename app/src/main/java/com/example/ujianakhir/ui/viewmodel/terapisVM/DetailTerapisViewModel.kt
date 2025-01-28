package com.example.ujianakhir.ui.viewmodel.terapisVM

import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertUiEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.Repository.TerapisRepository
import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.model.Terapis
import kotlinx.coroutines.launch


class DetailTerapisViewModel(private val terapisRepository: TerapisRepository) : ViewModel() {

    var uiState by mutableStateOf(DetailUiStateTerapis())
        private set

    fun fetchDetailTerapis(id_terapis: Int ) {
        viewModelScope.launch {
            uiState = DetailUiStateTerapis(isLoading = true)
            try {
                val terapis = terapisRepository.getTerapisById(id_terapis)
                uiState = DetailUiStateTerapis(detailUiEventTerapis = terapis.toTerapisDetailUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailUiStateTerapis(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }

}

data class DetailUiStateTerapis(
    val detailUiEventTerapis: InsertTerapisUiEvent = InsertTerapisUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailUiEventTerapis != InsertTerapisUiEvent()
}

fun Terapis.toTerapisDetailUiEvent(): InsertTerapisUiEvent {
    return InsertTerapisUiEvent(
        id_terapis = id_terapis,
        nama_terapis = nama_terapis,
        spesialisasi = spesialisasi,
        nomor_izin_praktik = nomor_izin_praktik,
    )
}