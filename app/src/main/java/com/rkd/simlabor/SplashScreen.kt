package com.rkd.simlabor

import android.content.Context
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.rkd.simlabor.data.DataStoreManager
import com.rkd.simlabor.master.MainActivity
import com.rkd.simlabor.network.ApiClient
import com.rkd.simlabor.sp.SharedPreferencesHelper
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
        val sp = SharedPreferencesHelper(applicationContext)
        lifecycleScope.launch {
            val token = dataStoreManager.getToken()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = ApiClient.apiService.validateToken(mapOf("token" to token))
                    response.user?.let {
                        sp.saveData("name", it.name)
                        sp.saveData("email", it.email)
                    }
                    if (response.status == 1) {

                        sp.saveData("message", "Welcome")
                        navigateToHome()
                         // Token valid, pindah ke HomeActivity
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
//            try {
//                val token = "Bearer $token" // Ganti dengan token JWT yang valid
//                val response = ApiClient.apiService.getData(token)
//
//                if (response.isSuccessful) {
//                    val message = response.body()?.message ?: "No message available"
//                    Toast.makeText(null, message, Toast.LENGTH_LONG).show()
//                } else {
//                    val error = when (response.code()) {
//                        401 -> "Unauthorized: Invalid token"
//                        500 -> "Server error: Something went wrong"
//                        else -> "Unexpected error: ${response.code()}"
//                    }
//                    Toast.makeText(null, error, Toast.LENGTH_LONG).show()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(null, "Exception: ${e.message}", Toast.LENGTH_LONG).show()
//            }
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

    tampilan()
}



@Preview(showBackground = true)
@Composable
fun tampilan() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white)) // Ganti dengan warna dari R.color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                    .wrapContentSize(Alignment.Center)
            ) {
                // Menampilkan gambar default Android

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEndPercent = 80,
                            bottomStartPercent = 0,
                        )
                    )
                    .background(colorResource(id = R.color.purple_500))

                ) {
                    Box(
                        modifier = Modifier.padding(
                            top = 30.dp,
                            bottom = 70.dp,
                            start = 40.dp,
                            end = 80.dp
                        )
                    ){
                        Column {
                            Text(
                                text = "Selamat",
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "Datang Di",
                                modifier = Modifier.offset(y = (-10).dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(text = "Sistem Informasi Manajemen Laboratorium",
                                modifier = Modifier.offset(y = (-10).dp),
                                style = MaterialTheme.typography.titleLarge

                            )

                        }

                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.cube_logo),
//                    contentDescription = "SIMLABOR",
//                    modifier = Modifier
//                        .size(180.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
                // Indikator loading berputar dengan warna hijau
                CircularProgressIndicator(
                    color = Color.Green,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(80.dp))


                Box(
                    modifier = Modifier
                        .fillMaxSize() // Memenuhi seluruh layar
                        .padding(
                            top = 50.dp
                        )
                        .clip(
                            RoundedCornerShape(
                                topStart = 90.dp,
                                topEnd = 90.dp,
                            )
                        )
                        .background(colorResource(id = R.color.purple_500))
                ) {


                    Text(
                        text = "Aplikasi Laboratorium Komputer adalah \n" +
                                "platform untuk mengelola penggunaan, \n" +
                                "inventaris perangkat, jadwal pemakaian, \n" +
                                "serta pemeliharaan lab komputer.\n",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier
                            .align(Alignment.TopCenter) // Tetap di bawah
                            .padding(bottom = 16.dp, top = 90.dp) // Jarak dari bawah layar
                    )
                    Text(
                        text = "© 2024 Laboratorium Komputer",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // Tetap di bawah
                            .padding(bottom = 16.dp) // Jarak dari bawah layar
                    )
                }



                Spacer(modifier = Modifier.height(25.dp))


            }
        }
    }
}
