package com.example.ujianakhir.ui.view.terapis

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ujianakhir.ui.costumwidget.CostumeTopAppBar
import com.example.ujianakhir.ui.navigation.DestinasiNavigasi
import com.example.ujianakhir.ui.view.pasien.EntryBody
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.terapisVM.UpdateTerapisViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiTerapisUpdate: DestinasiNavigasi {
    override val route = "updateTerapis"
    override val titleRes = "Update Terapis"
    const val ID_TERAPIS = "Id_terapis"
    val routesWithArg = "$route/{$ID_TERAPIS}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinasiTerapisUpdate(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateTerapisViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiTerapisUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryTerapisBody(
            modifier = Modifier.padding(padding),
            insertTerapisUiState = viewModel.updateTerapisUiState,
            onTerapisValueChange = viewModel::updateInsertTerapisState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTerapis()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}