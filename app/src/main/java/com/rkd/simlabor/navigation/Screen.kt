package com.rkd.simlabor.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Inventaris : Screen("inventaris", "Inventaris", Icons.Filled.Add)
    object Scan : Screen("scan", "Scan", Icons.Default.QrCode2)
    object Histori : Screen("histori", "Histori", Icons.Default.History)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}