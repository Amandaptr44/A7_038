package com.example.ujianakhir.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiJenisTerapiHome
import com.example.ujianakhir.ui.view.jenisterapi.HomeJenisTerapiScreen
import com.example.ujianakhir.ui.view.pasien.DestinasiDetailPasien
import com.example.ujianakhir.ui.view.pasien.DestinasiEntryPasien
import com.example.ujianakhir.ui.view.pasien.DestinasiPasienHome
import com.example.ujianakhir.ui.view.pasien.DestinasiPasienUpdate
import com.example.ujianakhir.ui.view.pasien.DetailPasienScreen
import com.example.ujianakhir.ui.view.pasien.EntryPasienScreen
import com.example.ujianakhir.ui.view.pasien.HomePasienScreen
import com.example.ujianakhir.ui.view.sesiterapi.DestinasiSesiTerapiHome
import com.example.ujianakhir.ui.view.sesiterapi.HomeSesiTerapiScreen
import com.example.ujianakhir.ui.view.terapis.DestinasiTerapisHome
import com.example.ujianakhir.ui.view.terapis.HomeTerapisScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController(), modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = DestinasiPasienHome.route,
        modifier = modifier
    ) {
        // Menampilkan daftar pasien
        composable(DestinasiPasienHome.route) {
            HomePasienScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPasien.route) },
                navigateToItemEntryTerapis = { navController.navigate(DestinasiTerapisHome.route) },
                navigateToItemEntryJenisTerapi = { navController.navigate(DestinasiJenisTerapiHome.route) },
                navigateToItemEntrySesiTerapi = { navController.navigate(DestinasiSesiTerapiHome.route) },
                ondetailClick = { idPasien ->
                    navController.navigate("${DestinasiDetailPasien.route}/$idPasien")
                    println("PengelolaHalaman: idPasien = $idPasien")
                }
            )
        }

        // Membuat data pasien baru
        composable(DestinasiEntryPasien.route) {
            EntryPasienScreen(
                navigateBack = {
                    navController.navigate(DestinasiPasienHome.route) {
                        popUpTo(DestinasiPasienHome.route) {
                            inclusive = true }
                    }
                }
            )
        }

        // Melihat detail pasien
        composable(
            route = DestinasiDetailPasien.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailPasien.ID_PASIEN) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val idPasien = backStackEntry.arguments?.getInt(DestinasiDetailPasien.ID_PASIEN)
            idPasien?.let {
                DetailPasienScreen (
                    idPasien = it,
                    onEditClick = {
                        navController.navigate("${DestinasiPasienUpdate.route}/$it")
                    },
                    /*onBackClick = { navController.popBackStack() },
                    onViewSessionsClick = {
                        navController.navigate("${DestinasiSesi.route}/$it")
                    } */
                )
            }
        }

       // Mengedit data pasien
        composable(
            route = DestinasiPasienUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiPasienUpdate.ID_PASIEN) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val idPasien = backStackEntry.arguments?.getInt(DestinasiPasienUpdate.ID_PASIEN)
            idPasien?.let {
                DestinasiPasienUpdate(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiPasienHome.route) {
                            popUpTo(DestinasiPasienHome.route) { inclusive = true }
                        }
                    },
                    modifier = modifier,
                )
            }
        }

        // Menampilkan halaman terapis
        composable(DestinasiTerapisHome.route) {
            HomeTerapisScreen(
                navigateToItemEntryTerapis = { navController.navigate(DestinasiTerapisHome.route) },
                navigateToItemEntry = { navController.navigate(DestinasiPasienHome.route) },
                navigateBack = { navController.popBackStack() }
                  )
        }

        // Menampilkan halaman jenis terapi
        composable(DestinasiJenisTerapiHome.route) {
            HomeJenisTerapiScreen(
                navigateToItemEntryJenisTerapi = { navController.navigate(DestinasiJenisTerapiHome.route) },
                navigateToItemEntry = { navController.navigate(DestinasiPasienHome.route) },
                navigateBack = { navController.popBackStack() }
            )
        }

        // Menampilkan halaman sesi terapi
        composable(DestinasiSesiTerapiHome.route) {
            HomeSesiTerapiScreen(
                navigateToItemEntrySesiTerapi = { navController.navigate(DestinasiSesiTerapiHome.route) },
                navigateToItemEntry = { navController.navigate(DestinasiPasienHome.route) },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}


