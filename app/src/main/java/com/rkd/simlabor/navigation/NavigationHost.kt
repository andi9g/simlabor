package com.rkd.simlabor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rkd.simlabor.master.HistoriScreen
import com.rkd.simlabor.master.HomeScreen
import com.rkd.simlabor.master.InventarisScreen
import com.rkd.simlabor.master.ProfileScreen
import com.rkd.simlabor.master.ScanScreen

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Inventaris.route) { InventarisScreen() }
        composable(Screen.Scan.route) { ScanScreen() }
        composable(Screen.Histori.route) { HistoriScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}