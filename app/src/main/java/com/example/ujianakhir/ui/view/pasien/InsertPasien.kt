package com.example.ujianakhir.ui.view.pasien

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ujianakhir.ui.costumwidget.CostumeTopAppBar
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
//import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertPasienSesiUiEvent
//import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertPasienSesiUiState
import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertPasienViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertUiEvent
import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertUiState
import kotlinx.coroutines.launch

object DestinasiEntryPasien : DestinasiNavigasi {
    override val route = "entry_pasien"
    override val titleRes = "Entry Pasien"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPasienScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPasienViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryPasien.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){
            innerPadding ->
        EntryBody(
            insertPasienUiState = viewModel.uiState,
            onPasienValueChange = viewModel::updateInsertPasienState,
            onSaveClick = {
                coroutineScope.launch{
                    viewModel.insertPasien()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertPasienUiState: InsertUiState,
    onPasienValueChange: (InsertUiEvent) -> Unit,
    onSaveClick:() -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertPasienUiState.insertUiEvent,
            onValueChange = onPasienValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.nama_pasien,
            onValueChange = { onValueChange(insertUiEvent.copy(nama_pasien = it)) },
            label = { Text("Nama Pasien") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.alamat,
            onValueChange = { onValueChange(insertUiEvent.copy(alamat = it)) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.no_telepon,
            onValueChange = { onValueChange(insertUiEvent.copy(no_telepon = it)) },
            label = { Text("Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggal_lahir,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggal_lahir = it)) },
            label = { Text("Tanggal Lahir") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.riwayat_medikal,
            onValueChange = { onValueChange(insertUiEvent.copy(riwayat_medikal = it)) },
            label = { Text("Riwayat Medikal") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}

//@Composable
//fun EntryBodySesiTerapi(
//    insertPasienSesiUiState: InsertPasienSesiUiState,
//    onPasienValueChange: (InsertPasienSesiUiEvent) -> Unit,
//    onSaveClick:() -> Unit,
//    modifier: Modifier = Modifier
//){
//    Column (
//        verticalArrangement = Arrangement.spacedBy(18.dp),
//        modifier = modifier.padding(12.dp)
//    ){
//        FormInputPasienSesi(
//            insertPasienSesiUiEvent = insertPasienSesiUiState.insertPasienSesiUiEvent,
//            onValueChange = onPasienValueChange,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Button(
//            onClick = onSaveClick,
//            shape = MaterialTheme.shapes.small,
//            modifier = Modifier.fillMaxWidth()
//        ){
//            Text(text = "Simpan")
//        }
//    }
//}
//
//@Composable
//fun FormInputPasienSesi(
//    insertPasienSesiUiEvent: InsertPasienSesiUiEvent,
//    modifier: Modifier = Modifier,
//    onValueChange: (InsertPasienSesiUiEvent) -> Unit = {},
//    enabled: Boolean = true
//) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        OutlinedTextField(
//            value = insertPasienSesiUiEvent.nama_terapis,
//            onValueChange = { onValueChange(insertPasienSesiUiEvent.copy(nama_terapis = it)) },
//            label = { Text("Tes pertanyaan") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
//
//        if (enabled) {
//            Text(
//                text = "Isi Semua Data!",
//                modifier = Modifier.padding(12.dp)
//            )
//        }
//        Divider(
//            thickness = 8.dp,
//            modifier = Modifier.padding(12.dp)
//        )
//    }
//}

