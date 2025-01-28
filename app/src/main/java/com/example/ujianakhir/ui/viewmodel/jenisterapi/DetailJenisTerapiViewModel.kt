package com.example.ujianakhir.ui.viewmodel.jenisterapi

import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertUiEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.model.Jenisterapi
import com.example.ujianakhir.model.Pasien
import kotlinx.coroutines.launch


class DetailJenisTerapiViewModel(private val jenisTerapiRepository: JenisTerapiRepository) : ViewModel() {

    var uiState by mutableStateOf(DetailUiStateJenisTerapi())
        private set

    fun fetchDetailJenisTerapi(id_jenisterapi: Int ) {
        viewModelScope.launch {
            uiState = DetailUiStateJenisTerapi(isLoading = true)
            try {
                val jenisterapi = jenisTerapiRepository.getJenisTerapiById(id_jenisterapi)
                uiState = DetailUiStateJenisTerapi(detailUiEventJenisTerapi = jenisterapi.toJenisTerapiDetailUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailUiStateJenisTerapi(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }

}

data class DetailUiStateJenisTerapi(
    val detailUiEventJenisTerapi: InsertJenisTerapiUiEvent = InsertJenisTerapiUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailUiEventJenisTerapi != InsertJenisTerapiUiEvent()
}

fun Jenisterapi.toJenisTerapiDetailUiEvent(): InsertJenisTerapiUiEvent {
    return InsertJenisTerapiUiEvent(
        id_jenis_terapi = id_jenis_terapi,
        nama_jenis_terapi = nama_jenis_terapi,
        deskripsi_terapi = deskripsi_terapi,
    )
}