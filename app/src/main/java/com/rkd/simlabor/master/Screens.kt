package com.rkd.simlabor.master

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rkd.simlabor.master.content.HomeActivityShow


@Composable
fun HomeScreen() {
    HomeActivityShow(text = "Home Screen")
}

@Composable
fun InventarisScreen() {
    HomeActivityShow(text = "Home Screen")
}

@Composable
fun ScanScreen() {
    HomeActivityShow(text = "Home Screen")
}

@Composable
fun HistoriScreen() {
    HomeActivityShow(text = "Home Screen")
}


@Composable
fun ProfileScreen() {
    HomeActivityShow(text = "Home Screen")
}

@Preview(showBackground = true)
@Composable
private fun lihat() {
    InventarisScreen()
}