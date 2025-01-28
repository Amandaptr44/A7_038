package com.example.ujianakhir.ui.view.jenisterapi

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
import com.example.ujianakhir.model.Jenisterapi
import com.example.ujianakhir.model.Terapis
import com.example.ujianakhir.ui.costumwidget.CostumeTopAppBar
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.jenisterapi.HomeJenisTerapiUiState
import com.example.ujianakhir.ui.viewmodel.jenisterapi.HomeJenisTerapiViewModel


object DestinasiJenisTerapiHome : DestinasiNavigasi {
    override val route = "homejenisterapi"
    override val titleRes = "Home JenisTerapi"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJenisTerapiScreen(
    navigateToItemEntryJenisTerapi:() -> Unit,
    navigateToItemEntry:() -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    ondetailClick: (Int) -> Unit = {},

    viewModel: HomeJenisTerapiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiJenisTerapiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getJenisTerapi()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntryJenisTerapi,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Jenis Terapi")
            }
        },

        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(8.dp),
                content = {
                    Button(
                        onClick = navigateToItemEntry,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Home Pasien")
                    }
                }
            )
        }

    ) { innerPadding ->
        HomeStatusJenisTerapi(
            homeJenisTerapiUiState = viewModel.jenisterapiUIState,
            retryAction = { viewModel.getJenisTerapi() },
            modifier = Modifier.padding(innerPadding),
            ondetailClick = ondetailClick,
            ondeleteClick = {
                viewModel.deleteJenisTerapi(it.id_jenisterapi)
                viewModel.getJenisTerapi()
            }
        )
    }


}


@Composable
fun HomeStatusJenisTerapi(
    homeJenisTerapiUiState: HomeJenisTerapiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ondeleteClick: (Jenisterapi) -> Unit,
    ondetailClick: (Int) -> Unit
){
    when (homeJenisTerapiUiState){
        is HomeJenisTerapiUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeJenisTerapiUiState.Success ->
            if (homeJenisTerapiUiState.jenisterapi.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data")
                }
            }else{
                JenisTerapiLayout(
                    jenisterapi = homeJenisTerapiUiState.jenisterapi, modifier = modifier.fillMaxWidth(),
                    ondetailClick = {
                        ondetailClick(it.id_jenisterapi)
                    },
                    ondeleteClick = {
                        ondeleteClick(it)
                    }
                )
            }
        is HomeJenisTerapiUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun JenisTerapiLayout(
    jenisterapi: List<Jenisterapi>,
    modifier: Modifier = Modifier,
    ondetailClick: (Jenisterapi) -> Unit,
    ondeleteClick: (Jenisterapi) -> Unit = {}
){
    LazyColumn (
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(jenisterapi){kontak ->
            JenisTerapiCard(
                jenisterapi = kontak,
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
fun JenisTerapiCard(
    jenisterapi: Jenisterapi,
    modifier: Modifier = Modifier,
    ondeleteClick: (Jenisterapi) -> Unit = {}
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = jenisterapi.nama_jenis_terapi,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { ondeleteClick(jenisterapi)}){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }

                Text(
                    text = jenisterapi.deskripsi_terapi,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}