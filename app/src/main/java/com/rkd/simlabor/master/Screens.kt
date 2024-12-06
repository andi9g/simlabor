package com.rkd.simlabor.master

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rkd.simlabor.master.content.HomeActivityShow
import com.rkd.simlabor.master.content.InventarisScreenShow


@Composable
fun HomeScreen(context: Context) {
    HomeActivityShow(text = "Home", context)
}

@Composable
fun InventarisScreen() {
    InventarisScreenShow(text = "DATA INVENTARIS")
}

@Composable
fun ScanScreen(context: Context) {
    HomeActivityShow(text = "Home Screen", context)
}

@Composable
fun HistoriScreen(context: Context) {
    HomeActivityShow(text = "Home Screen", context)
}


@Composable
fun ProfileScreen(context: Context) {
    HomeActivityShow(text = "Home Screen", context)
}

@Preview(showBackground = true)
@Composable
private fun lihat() {
    InventarisScreen()
}