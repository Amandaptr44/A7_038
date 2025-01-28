package com.example.ujianakhir.ui.view.sesiterapi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.DetailPasienViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.DetailSesiTerapiViewModel

object DestinasiDetailSesiTerapi : DestinasiNavigasi {
    override val route = "detail_sesiterapi"
    override val titleRes = "Detail Sesi Terapi"
    const val ID_SESITERAPI = "id_sesi"
    val routeWithArgs = "$route/{$ID_SESITERAPI}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSesiTerapiScreen(
    id_sesi: Int,
    onEditClick: (Int) -> Unit = { },
    navigateBack: () -> Unit,
    onBackClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailSesiTerapiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val sesiTerapi = viewModel.uiState.detailUiEventSesiTerapi
    LaunchedEffect(id_sesi) {
        viewModel.fetchDetailSesiTerapi(id_sesi)
    }
    val isLoading = viewModel.uiState.isLoading
    val isError = viewModel.uiState.isError
    val errorMessage = viewModel.uiState.errorMessage
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detail Sesi Terapi") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(sesiTerapi.id_sesi) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Sesi Terapi")
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                else if (isError) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else if (viewModel.uiState.isUiEventNotEmpty) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Text(text = "Id: ${sesiTerapi.id_sesi}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Nama: ${sesiTerapi.id_pasien}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Alamat: ${sesiTerapi.id_terapis}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Nomor Telepon: ${sesiTerapi.id_jenis_terapi}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Tanggal Lahir: ${sesiTerapi.tanggal_sesi}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Riwayat Medikal: ${sesiTerapi.catatan_sesi}", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    )
}