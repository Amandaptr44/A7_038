package com.example.ujianakhir.ui.view.sesiterapi

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
import com.example.ujianakhir.ui.viewmodel.PenyediaViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.UpdateSesiTerapiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiSesiTerapiUpdate: DestinasiNavigasi {
    override val route = "updateSesiTerapi"
    override val titleRes = "Update SesiTerapi"
    const val ID_SESITERAPI = "IdSesiTerapi"
    val routesWithArg = "$route/{$ID_SESITERAPI}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateSesiTerapiScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateSesiTerapiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiSesiTerapiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodySesiTerapi(
            insertSesiTerapiUiState = viewModel.updateSesiTerapiUiState,
            onValueChange = viewModel::updateInsertSesiTerapiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateSesiTerapi()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            },

            pasienList = viewModel.pasienList,
            terapisList = viewModel.terapisList,
            jenisTerapiList = viewModel.jenisTerapiList,
            modifier = Modifier.padding(padding)
        )
    }
}