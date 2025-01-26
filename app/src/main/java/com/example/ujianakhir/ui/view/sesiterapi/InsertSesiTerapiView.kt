package com.example.ujianakhir.ui.view.sesiterapi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ujianakhir.ui.costumwidget.CostumeTopAppBar
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.view.pasien.FormInput
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.jenisterapi.InsertJenisTerapiUiEvent
import com.example.ujianakhir.ui.viewmodel.jenisterapi.InsertJenisTerapiUiState
import com.example.ujianakhir.ui.viewmodel.jenisterapi.InsertJenisTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.InsertSesiTerapiUiEvent
import com.example.ujianakhir.ui.viewmodel.sesiterapi.InsertSesiTerapiUiState
import com.example.ujianakhir.ui.viewmodel.sesiterapi.InsertSesiTerapiViewModel
import kotlinx.coroutines.launch

object DestinasiEntrySesiTerapi : DestinasiNavigasi {
    override val route = "entry_sesiterapi"
    override val titleRes = "Entry SesiTerapi"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrySesiTerapiScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertSesiTerapiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntrySesiTerapi.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){
            innerPadding ->
        EntryBodySesiTerapi(
            insertSesiTerapiUiState = viewModel.uiState,
            onValueChange = viewModel::updateInsertSesiTerapiState,
            onSaveClick = {
                coroutineScope.launch{
                    viewModel.insertSesiTerapi()
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
fun EntryBodySesiTerapi(
    insertSesiTerapiUiState: InsertSesiTerapiUiState,
    onValueChange: (InsertSesiTerapiUiEvent) -> Unit,
    onSaveClick:() -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInputSesiTerapi(
            insertSesiTerapiUiEvent = insertSesiTerapiUiState.insertSesiTerapiUiEvent,
            onValueChange = onValueChange,
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
fun FormInputSesiTerapi(
    insertSesiTerapiUiEvent: InsertSesiTerapiUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertSesiTerapiUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertSesiTerapiUiEvent.tanggalsesi,
            onValueChange = {onValueChange(insertSesiTerapiUiEvent.copy(tanggalsesi = it))},
            label = { Text("Tanggal Sesi Terapi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertSesiTerapiUiEvent.catatansesi,
            onValueChange = {onValueChange(insertSesiTerapiUiEvent.copy(catatansesi = it))},
            label = { Text("Catatan Sesi Terapi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if(enabled){
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