package com.example.ujianakhir.ui.view.jenisterapi

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
import com.example.ujianakhir.ui.viewmodel.jenisterapi.UpdateJenisTerapiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiJenisTerapiUpdate: DestinasiNavigasi {
    override val route = "updateJenisTerapi"
    override val titleRes = "Update JenisTerapi"
    const val ID_JENISTERAPI = "IdJenisTerapi"
    val routesWithArg = "$route/{$ID_JENISTERAPI}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateJenisTerapiScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateJenisTerapiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiJenisTerapiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodyJenisTerapi(
            modifier = Modifier.padding(padding),
            insertJenisTerapiUiState = viewModel.updateJenisTerapiUiState,
            onValueChange = viewModel::updateInsertJenisTerapiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateJenisTerapi()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}