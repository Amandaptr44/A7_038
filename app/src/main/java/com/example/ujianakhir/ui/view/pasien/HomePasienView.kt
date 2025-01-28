package com.example.ujianakhir.ui.view.pasien

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ujianakhir.R
import com.example.ujianakhir.model.Pasien
import com.example.ujianakhir.ui.costumwidget.CostumeTopAppBar
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.HomePasienUiState
import com.example.ujianakhir.ui.viewmodel.pasienVM.HomePasienViewModel


object DestinasiPasienHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Pasien"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePasienScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemEntryTerapis: () -> Unit,
    navigateToItemEntryJenisTerapi: () -> Unit,
    navigateToItemEntrySesiTerapi: () -> Unit,
    modifier: Modifier = Modifier,
    ondetailClick: (Int) -> Unit = {},
    viewModel: HomePasienViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiPasienHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPasien()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pasien")
            }
        },

        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(8.dp),
                content = {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = navigateToItemEntryTerapis, // Navigasi ke halaman terapis
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Terapis")
                        }

                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = navigateToItemEntryJenisTerapi,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Jenis Terapi")
                        }

                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = navigateToItemEntrySesiTerapi,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Sesi Terapi")
                        }
                    }
                }
            )
        }

    ) { innerPadding ->
        HomePasienStatus(
            homePasienUiState = viewModel.pasienUIState,
            retryAction = { viewModel.getPasien() },
            modifier = Modifier.padding(innerPadding),
            ondetailClick = ondetailClick,
            ondeleteClick = {
                it.id_pasien?.let { id ->
                    viewModel.deletePasien(it.id_pasien)
                    viewModel.getPasien()
                    //viewModel.insertPasien()
                }
            }
        )
    }
}

@Composable
fun HomePasienStatus(
    homePasienUiState: HomePasienUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ondeleteClick: (Pasien) -> Unit,
    ondetailClick: (Int) -> Unit
) {
    when (homePasienUiState) {
        is HomePasienUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomePasienUiState.Success ->
            if (homePasienUiState.pasien.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Pasien")
                }
            } else {
                PasienLayout(
                    pasien = homePasienUiState.pasien,
                    modifier = modifier.fillMaxWidth(),
                    ondetailClick = {
                        ondetailClick(it.id_pasien)
                    },
                    ondeleteClick = {
                        ondeleteClick(it)
                    }
                )
            }
        is HomePasienUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
        else -> {}
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PasienLayout(
    pasien: List<Pasien>,
    modifier: Modifier = Modifier,
    ondetailClick: (Pasien) -> Unit,
    ondeleteClick: (Pasien) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pasien) { pasienItem ->
            PasienCard(
                pasien = pasienItem,
                modifier = Modifier
                    .fillMaxWidth()
                .clickable {ondetailClick(pasienItem)},
                ondeleteClick = {
                    ondeleteClick(pasienItem)
                }
            )
        }
    }
}

@Composable
fun PasienCard(
    pasien: Pasien,
    modifier: Modifier = Modifier,
    ondeleteClick: (Pasien) -> Unit = {},

) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pasien.nama_pasien,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { ondeleteClick(pasien) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }

            }
            Text(
                text = pasien.alamat,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pasien.no_telepon,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }



}
