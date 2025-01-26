package com.example.ujianakhir.ui.view.sesiterapi

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
import com.example.ujianakhir.model.SesiTerapi
import com.example.ujianakhir.ui.costumwidget.CostumeTopAppBar
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.HomeSesiTerapiUiState
import com.example.ujianakhir.ui.viewmodel.sesiterapi.HomeSesiTerapiViewModel


object DestinasiSesiTerapiHome : DestinasiNavigasi {
    override val route = "homesesiterapi"
    override val titleRes = "Home Sesi Terapi"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSesiTerapiScreen(
    navigateToItemEntrySesiTerapi: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    ondetailClick: (Int) -> Unit = {},
    viewModel: HomeSesiTerapiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiSesiTerapiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getSesiTerapi()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntrySesiTerapi,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Sesi Terapi")
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



                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = navigateToItemEntry,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Daftar Pasien")
                        }

                    }
                }
            )
        }

    ) { innerPadding ->
        HomeStatusSesiTerapi(
            homeSesiTerapiUiState = viewModel.sesiterapiUIState,
            retryAction = { viewModel.getSesiTerapi() },
            modifier = Modifier.padding(innerPadding),
            ondetailClick = ondetailClick,
            ondeleteClick = {
                viewModel.deleteSesiTerapi(it.idSesiTerapi)
                viewModel.getSesiTerapi()
            }
        )
    }
}


@Composable
fun HomeStatusSesiTerapi(
    homeSesiTerapiUiState: HomeSesiTerapiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ondeleteClick: (SesiTerapi) -> Unit,
    ondetailClick: (Int) -> Unit
){
    when (homeSesiTerapiUiState){
        is HomeSesiTerapiUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeSesiTerapiUiState.Success ->
            if (homeSesiTerapiUiState.sesiTerapi.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ditemukan")
                }
            }else{
                SesiTerapiLayout(
                    sesiterapi = homeSesiTerapiUiState.sesiTerapi, modifier = modifier.fillMaxWidth(),
                    ondetailClick = {
                        ondetailClick(it.idSesiTerapi)
                    },
                    ondeleteClick = {
                        ondeleteClick(it)
                    }
                )
            }
        is HomeSesiTerapiUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

//The home Screen displaying the loading message
@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

//the home screen displaying error message with re-attempt button
@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
fun SesiTerapiLayout(
    sesiterapi: List<SesiTerapi>,
    modifier: Modifier = Modifier,
    ondetailClick: (SesiTerapi) -> Unit,
    ondeleteClick: (SesiTerapi) -> Unit = {}
){
    LazyColumn (
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(sesiterapi){kontak ->
            SesiTerapiCard(
                sesiterapi = kontak,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { ondetailClick(kontak) },
                ondeleteClick = {
                    ondeleteClick(kontak)
                }
            )
        }
    }
}

@Composable
fun SesiTerapiCard(
    sesiterapi: SesiTerapi,
    modifier: Modifier = Modifier,
    ondeleteClick: (SesiTerapi) -> Unit = {}
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sesiterapi.tanggalsesi,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { ondeleteClick(sesiterapi) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }

                Text(
                    text = sesiterapi.catatansesi,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}