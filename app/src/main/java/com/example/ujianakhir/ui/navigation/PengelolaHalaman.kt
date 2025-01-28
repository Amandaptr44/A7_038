package com.example.ujianakhir.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiDetailJenisTerapi
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiEntryJenisTerapi
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiJenisTerapiHome
import com.example.ujianakhir.ui.view.jenisterapi.DestinasiJenisTerapiUpdate
import com.example.ujianakhir.ui.view.jenisterapi.DetailJenisTerapiScreen
import com.example.ujianakhir.ui.view.jenisterapi.EntryJenisTerapiScreen
import com.example.ujianakhir.ui.view.jenisterapi.HomeJenisTerapiScreen
import com.example.ujianakhir.ui.view.jenisterapi.UpdateJenisTerapiScreen
import com.example.ujianakhir.ui.view.pasien.DestinasiDetailPasien
import com.example.ujianakhir.ui.view.pasien.DestinasiEntryPasien

import com.example.ujianakhir.ui.view.pasien.DestinasiPasienHome
import com.example.ujianakhir.ui.view.pasien.DestinasiPasienSesiTerapi
import com.example.ujianakhir.ui.view.pasien.DestinasiPasienUpdate

import com.example.ujianakhir.ui.view.pasien.DetailPasienScreen
import com.example.ujianakhir.ui.view.pasien.EntryPasienScreen
import com.example.ujianakhir.ui.view.pasien.HomePasienScreen
import com.example.ujianakhir.ui.view.sesiterapi.DestinasiDetailSesiTerapi
import com.example.ujianakhir.ui.view.sesiterapi.DestinasiEntrySesiTerapi
import com.example.ujianakhir.ui.view.sesiterapi.DestinasiSesiTerapiHome
import com.example.ujianakhir.ui.view.sesiterapi.DestinasiSesiTerapiUpdate
import com.example.ujianakhir.ui.view.sesiterapi.DetailSesiTerapiScreen
import com.example.ujianakhir.ui.view.sesiterapi.EntrySesiTerapiScreen
import com.example.ujianakhir.ui.view.sesiterapi.HomeSesiTerapiScreen
import com.example.ujianakhir.ui.view.terapis.DestinasiDetailTerapis
import com.example.ujianakhir.ui.view.terapis.DestinasiTerapisEntry
import com.example.ujianakhir.ui.view.terapis.DestinasiTerapisHome
import com.example.ujianakhir.ui.view.terapis.DestinasiTerapisUpdate
import com.example.ujianakhir.ui.view.terapis.DetailTerapisScreen
import com.example.ujianakhir.ui.view.terapis.EntryTerapisScreen
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
                ondetailClick = { id_pasien ->
                    navController.navigate("${DestinasiDetailPasien.route}/$id_pasien")
//                    println("PengelolaHalaman: id_pasien = $id_pasien")
//                    println("${DestinasiDetailPasien.route}")
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
            val id_pasien = backStackEntry.arguments?.getInt(DestinasiDetailPasien.ID_PASIEN)
            id_pasien?.let {
                DetailPasienScreen (
                    id_pasien = it,
                    onEditClick = {
                        navController.navigate("${DestinasiPasienUpdate.route}/$it")
                    },
//                  onAddClick = {
//                      navController.navigate("${DestinasiPasienSesiTerapi.route}/$it")
//                  },
                    navigateBack = {
                        navController.navigate(DestinasiPasienHome.route) {
                            popUpTo(DestinasiPasienHome.route) {
                                inclusive = true }
                        }
                    },

                    onBackClick = { navController.popBackStack() },
                    onViewSessionsClick = {
                        navController.navigate("${DestinasiEntrySesiTerapi.route}/$it")
                    }
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
            val id_pasien = backStackEntry.arguments?.getInt(DestinasiPasienUpdate.ID_PASIEN)
            id_pasien?.let {
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

        // Mengedit data pasien sesi
        composable(
            route = DestinasiPasienSesiTerapi.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiPasienSesiTerapi.ID_PASIEN) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_pasien = backStackEntry.arguments?.getInt(DestinasiPasienSesiTerapi.ID_PASIEN)
            id_pasien?.let {
                DestinasiPasienSesiTerapi(
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
                navigateToItemEntryTerapis = { navController.navigate(DestinasiTerapisEntry.route) },
                navigateToItemEntry = { navController.navigate(DestinasiPasienHome.route) },
                navigateBack = { navController.popBackStack() },
                ondetailClick = { id_terapis ->
                    navController.navigate("${DestinasiDetailTerapis.route}/$id_terapis")
//
                }
                  )
        }

        // Membuat data terapis baru
        composable(DestinasiTerapisEntry.route) {
            EntryTerapisScreen(
                navigateBack = {
                    navController.navigate(DestinasiTerapisHome.route) {
                        popUpTo(DestinasiTerapisHome.route) {
                            inclusive = true }
                    }
                }
            )
        }

        // Melihat detail terapis
        composable(
            route = DestinasiDetailTerapis.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailTerapis.ID_TERAPIS) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_terapis = backStackEntry.arguments?.getInt(DestinasiDetailTerapis.ID_TERAPIS)
            id_terapis?.let {
                DetailTerapisScreen(
                    id_terapis = it,
                    onEditClick = {
                        navController.navigate("${DestinasiTerapisUpdate.route}/$it")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiTerapisHome.route) {
                            popUpTo(DestinasiTerapisHome.route) {
                                inclusive = true }
                        }
                    },

                    onBackClick = { navController.popBackStack() },
                    /*onViewSessionsClick = {
                        navController.navigate("${DestinasiSesi.route}/$it")
                    } */
                )
            }
        }

        // Mengedit data terapis
        composable(
            route = DestinasiTerapisUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiTerapisUpdate.ID_TERAPIS) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_terapis = backStackEntry.arguments?.getInt(DestinasiTerapisUpdate.ID_TERAPIS)
            id_terapis?.let {
                com.example.ujianakhir.ui.view.terapis.DestinasiTerapisUpdate(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiTerapisHome.route) {
                            popUpTo(DestinasiTerapisHome.route) { inclusive = true }
                        }
                    },
                    modifier = modifier,
                )
            }
        }

        // Menampilkan halaman jenis terapi
        composable(DestinasiJenisTerapiHome.route) {
            HomeJenisTerapiScreen(
                navigateToItemEntryJenisTerapi = { navController.navigate(DestinasiEntryJenisTerapi.route) },
                navigateToItemEntry = { navController.navigate(DestinasiPasienHome.route) },
                navigateBack = { navController.popBackStack() },
                ondetailClick = { id_jenisterapi ->
                    navController.navigate("${DestinasiDetailJenisTerapi.route}/$id_jenisterapi")}
            )
        }

        // Membuat data jenis terapi baru
        composable(DestinasiEntryJenisTerapi.route) {
            EntryJenisTerapiScreen(
                navigateBack = {
                    navController.navigate(DestinasiJenisTerapiHome.route) {
                        popUpTo(DestinasiJenisTerapiHome.route) {
                            inclusive = true }
                    }
                }
            )
        }

        // Melihat detail jenis terapi
        composable(
            route = DestinasiDetailJenisTerapi.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailJenisTerapi.ID_JENISTERAPI) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_jenisterapi = backStackEntry.arguments?.getInt(DestinasiDetailJenisTerapi.ID_JENISTERAPI)
            id_jenisterapi?.let {
                DetailJenisTerapiScreen (
                    id_jenisterapi = it,
                    onEditClick = {
                        navController.navigate("${DestinasiJenisTerapiUpdate.route}/$it")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiJenisTerapiUpdate.route) {
                            popUpTo(DestinasiJenisTerapiUpdate.route) {
                                inclusive = true }
                        }
                    },
                    onBackClick = { navController.popBackStack() },
//                    onViewSessionsClick = {
//                        navController.navigate("${DestinasiEntrySesiTerapi.route}/$it")
//                    }
                )
            }
        }

        // Mengedit data jenis terapi
        composable(
            route = DestinasiJenisTerapiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiJenisTerapiUpdate.ID_JENISTERAPI) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_jenisterapi = backStackEntry.arguments?.getInt(DestinasiJenisTerapiUpdate.ID_JENISTERAPI)
            id_jenisterapi?.let {
                UpdateJenisTerapiScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiJenisTerapiHome.route) {
                            popUpTo(DestinasiJenisTerapiHome.route) { inclusive = true }
                        }
                    },
                    modifier = modifier,
                )
            }
        }

        // Menampilkan halaman sesi terapi
        composable(DestinasiSesiTerapiHome.route) {
            HomeSesiTerapiScreen(
                navigateToItemEntrySesiTerapi = { navController.navigate(DestinasiEntrySesiTerapi.route) },
                navigateToItemEntry = { navController.navigate(DestinasiPasienHome.route) },
                navigateBack = { navController.popBackStack() }
            )
        }

        // Membuat data sesi terapi baru
        composable(DestinasiEntrySesiTerapi.route) {
            EntrySesiTerapiScreen(
                navigateBack = {
                    navController.navigate(DestinasiSesiTerapiHome.route) {
                        popUpTo(DestinasiSesiTerapiHome.route) {
                            inclusive = true }
                    }
                }
            )
        }

        // Melihat detail sesi terapi
        composable(
            route = DestinasiDetailSesiTerapi.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSesiTerapi.ID_SESITERAPI) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_sesi = backStackEntry.arguments?.getInt(DestinasiDetailSesiTerapi    .ID_SESITERAPI)
            id_sesi?.let {
                DetailSesiTerapiScreen(
                    id_sesi = it,
                    onEditClick = {
                        navController.navigate("${DestinasiSesiTerapiUpdate.route}/$it")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiSesiTerapiHome.route) {
                            popUpTo(DestinasiSesiTerapiHome.route) {
                                inclusive = true }
                        }
                    },

                    onBackClick = { navController.popBackStack() },
                    /*onViewSessionsClick = {
                        navController.navigate("${DestinasiSesi.route}/$it")
                    } */
                )
            }
        }
    }
}


