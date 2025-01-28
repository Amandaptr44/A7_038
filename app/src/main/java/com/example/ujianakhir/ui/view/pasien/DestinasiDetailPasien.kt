package com.example.ujianakhir.ui.view.pasien

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
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

object DestinasiDetailPasien : DestinasiNavigasi {
    override val route = "detail_pasien"
    override val titleRes = "Detail Pasien"
    const val ID_PASIEN = "id_pasien"
    val routeWithArgs = "$route/{$ID_PASIEN}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPasienScreen(
    id_pasien: Int,
    onEditClick: (Int) -> Unit = { },
    onAddClick: () -> Unit = { },
    navigateBack: () -> Unit,
    onBackClick: () -> Unit = { },
    onViewSessionsClick: () -> Unit ={},
    modifier: Modifier = Modifier,
    viewModel: DetailPasienViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val pasien = viewModel.uiState.detailUiEvent
    LaunchedEffect(id_pasien) {
        viewModel.fetchDetailPasien(id_pasien)
    }
    val isLoading = viewModel.uiState.isLoading
    val isError = viewModel.uiState.isError
    val errorMessage = viewModel.uiState.errorMessage
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detail Pasien") },
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
                onClick = { onEditClick(pasien.id_pasien) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Pasien")
            }

            FloatingActionButton(
                onClick = { onAddClick() }, // Hapus parameter pasien.id_pasien
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 80.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Sesi Terapi")
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

                                Text(text = "Id: ${pasien.id_pasien}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Nama: ${pasien.nama_pasien}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Alamat: ${pasien.alamat}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Nomor Telepon: ${pasien.no_telepon}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Tanggal Lahir: ${pasien.tanggal_lahir}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Riwayat Medikal: ${pasien.riwayat_medikal}", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    )
}