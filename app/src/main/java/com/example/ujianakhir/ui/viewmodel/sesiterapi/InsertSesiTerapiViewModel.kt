package com.example.ujianakhir.ui.viewmodel.sesiterapi


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianakhir.Repository.JenisTerapiRepository
import com.example.ujianakhir.Repository.PasienRepository
import com.example.ujianakhir.Repository.SesiTerapiRepository
import com.example.ujianakhir.Repository.TerapisRepository
import com.example.ujianakhir.model.Jenisterapi
import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.model.SesiTerapi
import com.example.ujianakhir.model.Terapis
import kotlinx.coroutines.launch

class InsertSesiTerapiViewModel (
    private val sesiTerapiRepository: SesiTerapiRepository,
    private val pasienRepository: PasienRepository,
    private val terapisRepository: TerapisRepository,
    private val jenisTerapiRepository: JenisTerapiRepository
): ViewModel(){
    var uiState by mutableStateOf(InsertSesiTerapiUiState())
        private set
    var pasienList by mutableStateOf<List<Pasien>>(emptyList())
        private set

    var terapisList by mutableStateOf<List<Terapis>>(emptyList())
        private set

    var jenisTerapiList by mutableStateOf<List<Jenisterapi>>(emptyList())
        private set

    init {
        fetchPasienList()
        fetchTerapisList()
        fetchJenisTerapiList()
    }

    fun updateInsertSesiTerapiState(insertSesiTerapiUiEvent: InsertSesiTerapiUiEvent){
        uiState = InsertSesiTerapiUiState(insertSesiTerapiUiEvent = insertSesiTerapiUiEvent)
    }

    suspend fun insertSesiTerapi(){
        viewModelScope.launch {
            try {
                sesiTerapiRepository.insertSesiTerapi(uiState.insertSesiTerapiUiEvent.toSesiTerapi())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun fetchPasienList() {
        viewModelScope.launch {
            try {
                pasienList = pasienRepository.getPasien()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchTerapisList() {
        viewModelScope.launch {
            try {
                terapisList = terapisRepository.getTerapis()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchJenisTerapiList() {
        viewModelScope.launch {
            try {
                jenisTerapiList = jenisTerapiRepository.getJenisTerapi()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertSesiTerapiUiState(
    val insertSesiTerapiUiEvent: InsertSesiTerapiUiEvent = InsertSesiTerapiUiEvent()
)

data class InsertSesiTerapiUiEvent(
    val id_sesi: Int = 0,
    val id_pasien: String = "",
    val id_terapis: String = "",
    val id_jenis_terapi: String = "",
    val tanggal_sesi: String = "",
    val catatan_sesi: String = "",
)

fun InsertSesiTerapiUiEvent.toSesiTerapi(): SesiTerapi = SesiTerapi(
    id_sesi = id_sesi,
    id_pasien = id_pasien,
    id_terapis = id_terapis,
    id_jenis_terapi = id_jenis_terapi,
    tanggal_sesi = tanggal_sesi,
    catatan_sesi = catatan_sesi,
)

fun SesiTerapi.toSesiTerapiUiState():InsertSesiTerapiUiState = InsertSesiTerapiUiState(
    insertSesiTerapiUiEvent = toInsertSesiTerapiUiEvent()
)

fun SesiTerapi.toInsertSesiTerapiUiEvent():InsertSesiTerapiUiEvent = InsertSesiTerapiUiEvent(
    id_sesi = id_sesi,
    id_pasien = id_pasien,
    id_terapis = id_terapis,
    id_jenis_terapi = id_jenis_terapi,
    tanggal_sesi = tanggal_sesi,
    catatan_sesi = catatan_sesi,
)

fun saveSesiTerapi(
    id_pasien: Int,
    id_terapis: Int,
    id_jenis_terapi: Int,
    tanggal_sesi: String,
    catatan_sesi: String
) {
    // Create the new Sesi data
    val newSesi = InsertSesiTerapiUiEvent(
        id_pasien = id_pasien.toString(),
        id_terapis = id_terapis.toString(),
        id_jenis_terapi = id_jenis_terapi.toString(),
        tanggal_sesi = tanggal_sesi,
        catatan_sesi = catatan_sesi
    )
}


