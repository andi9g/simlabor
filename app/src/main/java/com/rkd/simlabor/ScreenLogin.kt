package com.rkd.simlabor

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSimlabor(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    context: Context? = null
) {
    LoginForm()
}




@Preview(showBackground = true)
@Composable
private fun SimlaborLoginRegisterPrev() {
    val navController = rememberNavController() // Dummy NavController untuk preview
    LoginSimlabor()
}
