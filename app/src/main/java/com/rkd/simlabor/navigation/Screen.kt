package com.rkd.simlabor.navigation

import okhttp3.Route

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register: Screen("register")

}