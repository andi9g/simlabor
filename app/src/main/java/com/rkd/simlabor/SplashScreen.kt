package com.rkd.simlabor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.rkd.simlabor.data.DataStoreManager
import com.rkd.simlabor.master.MainActivity
import com.rkd.simlabor.network.ApiClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class SplashScreen: ComponentActivity() {
    private lateinit var dataStoreManager: DataStoreManager

    override fun onStart() {
        super.onStart()

        dataStoreManager = DataStoreManager(applicationContext)

        setContent {
            SplashScreenShow { validateTokenAndNavigate() }
        }

    }

    private fun validateTokenAndNavigate() {
        lifecycleScope.launch {
            val token = dataStoreManager.accessToken.first()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = ApiClient.apiService.validateToken(mapOf("token" to token))
                    if (response.status == 1) {
                        navigateToHome() // Token valid, pindah ke HomeActivity
                    } else {
                        dataStoreManager.clearToken() // Token tidak valid, hapus token
                        navigateToLogin()
                    }
                } catch (e: Exception) {
                    navigateToLogin() // Error jaringan atau API
                }
            } else {
                navigateToLogin() // Token kosong, arahkan ke LoginActivity
            }
        }
    }

    private suspend fun validateToken(token: String) {
        try {
            val response = ApiClient.apiService.validateToken(mapOf("token" to token))
            if (response.status == 1) {
                navigateToHome()
            } else {
                dataStoreManager.clearToken()
                navigateToLogin()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "${e}", Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}


@Composable
fun SplashScreenShow(onFinish: () -> Unit) {
    LaunchedEffect(key1 = true) {
        onFinish() // Panggil validasi token setelah Splash Screen selesai
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.lightgreenActive2)) // Ganti dengan warna dari R.color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                // Menampilkan gambar default Android
                Image(
                    painter = painterResource(id = R.drawable.cube_logo),
                    contentDescription = "SIMLABOR",
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Indikator loading berputar dengan warna hijau
                CircularProgressIndicator(
                    color = Color.Green,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
