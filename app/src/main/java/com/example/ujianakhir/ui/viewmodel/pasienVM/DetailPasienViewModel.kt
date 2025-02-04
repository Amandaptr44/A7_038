package com.example.ujianakhir.ui.viewmodel.pasienVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.model.Pasien
import kotlinx.coroutines.launch


class DetailPasienViewModel(private val pasienRepository: PasienRepository) : ViewModel() {

    var uiState by mutableStateOf(DetailUiStatePasien())
        private set

    fun fetchDetailPasien(id_pasien: Int ) {
        viewModelScope.launch {
            uiState = DetailUiStatePasien(isLoading = true)
            try {
                val mahasiswa = pasienRepository.getPasienById(id_pasien)
                uiState = DetailUiStatePasien(detailUiEvent = mahasiswa.toDetailUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailUiStatePasien(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }

}

data class DetailUiStatePasien(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Pasien.toDetailUiEvent(): InsertUiEvent {
    return InsertUiEvent(
        id_pasien = id_pasien,
        nama_pasien = nama_pasien,
        alamat = alamat,
        no_telepon = no_telepon,
        tanggal_lahir = tanggal_lahir,
        riwayat_medikal = riwayat_medikal
    )
}