package com.example.pesawatmu.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.Penumpangmu.ui.add.AddScreenPenumpang
import com.example.Penumpangmu.ui.add.DestinasiAddPenumpang
import com.example.Penumpangmu.ui.detail.DetailDestinasiPenumpang
import com.example.Penumpangmu.ui.detail.DetailPenumpangScreen
import com.example.contactapp_with_firebase.ui.detail.DetailDestinasiPesawat
import com.example.contactapp_with_firebase.ui.detail.DetailPesawatScreen
import com.example.pesawatmu.ui.add.AddScreenPesawat
import com.example.contactapp_with_firebase.ui.edit.EditDestinasiPesawat
import com.example.contactapp_with_firebase.ui.edit.EditScreenPesawat
import com.example.contactapp_with_firebase.ui.home.DestinasiHomePenumpang
import com.example.contactapp_with_firebase.ui.home.DestinasiHomePesawat
import com.example.contactapp_with_firebase.ui.home.HomeScreen
import com.example.contactapp_with_firebase.ui.home.HomeScreen2
import com.example.pesawatmu.model.SumberData.Kelamin
import com.example.pesawatmu.model.SumberData.Maskapai
import com.example.pesawatmu.ui.add.DestinasiAddpesawat
import com.example.pesawatmu.ui.edit.EditScreenPenumpang
import com.example.pesawatmu.ui.edit.EditDestinasiPenumpang
import com.example.pesawatmu.ui.home.DestinasiStart
import com.example.pesawatmu.ui.home.StartScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiStart.route,
        modifier = Modifier
    ) {
        composable(  // Destinasi Dari Halaman Start
            DestinasiStart.route
        ) {
            StartScreen(
                onNextButtonpesawatClicked = {
                    navController.navigate(DestinasiHomePesawat.route)
                },
                onNextButtonpenumpangClicked = {
                    navController.navigate(DestinasiHomePenumpang.route)
                }
            )
        }
        composable(DestinasiHomePesawat.route) { //Destinasi dari halaman home pesawat
            HomeScreen(
                navigateToItemAdd = {
                    navController.navigate(DestinasiAddpesawat.route)
                },
                navigateBack = { navController.popBackStack() },
                onDetailClick = { pesawatId ->
                    navController.navigate("${DetailDestinasiPesawat.route}/$pesawatId")
                    println("penumpangId: $pesawatId")
                },
            )
        }

        composable(DestinasiHomePenumpang.route) {
            HomeScreen2(
                navigateToItemAdd2 = {
                    navController.navigate(DestinasiAddPenumpang.route)
                },
                navigateBack = { navController.popBackStack() },
                onDetailClick = { penumpangId ->
                    navController.navigate("${DetailDestinasiPenumpang.route}/$penumpangId")
                    println("penumpangId: $penumpangId")
                },
            )
        }
        composable(DestinasiAddpesawat.route) {//destinasi ke halaman detail pesawat
            AddScreenPesawat(
                navigateBack = { navController.popBackStack()}, pilihanM = Maskapai
            )
        }
        composable(DestinasiAddPenumpang.route) {//destinasi ke halaman detail penumapng
            AddScreenPenumpang(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(// destinasi ke halaman detail
            route = DetailDestinasiPesawat.routeWithArgs,
            arguments = listOf(navArgument(DetailDestinasiPesawat.pesawatId) {
                type = NavType.StringType
            })
        )
        { backStackEntry ->
            val maskapaiId = backStackEntry.arguments?.getString(DetailDestinasiPesawat.pesawatId)
            maskapaiId?.let {
                DetailPesawatScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestinasiPesawat.route}/$maskapaiId")
                        println("maskapaiId: $maskapaiId")
                    }
                )
            }
        }
        composable(
            route = DetailDestinasiPenumpang.routeWithArgs,
            arguments = listOf(navArgument(DetailDestinasiPenumpang.penumpangId) {
                type = NavType.StringType
            })
        )
        { backStackEntry ->
            val penumpangId =
                backStackEntry.arguments?.getString(DetailDestinasiPenumpang.penumpangId)
            penumpangId?.let {
                DetailPenumpangScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestinasiPenumpang.route}/$penumpangId")
                        println("penumpangId: $penumpangId")
                    }
                )
            }
        }
        composable(
            route = EditDestinasiPesawat.routeWithArgs,
            arguments = listOf(navArgument(EditDestinasiPesawat.pesawatId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val pesawatId = backStackEntry.arguments?.getString(EditDestinasiPesawat.pesawatId)
            pesawatId?.let {
                EditScreenPesawat(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() },
                    pilihanM = Maskapai
                )
            }
        }
        composable(
            route = EditDestinasiPenumpang.routeWithArgs,
            arguments = listOf(navArgument(EditDestinasiPenumpang.penumpangId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val penumpangId =
                backStackEntry.arguments?.getString(EditDestinasiPenumpang.penumpangId)
            penumpangId?.let {
                EditScreenPenumpang(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }

        }
    }
}

