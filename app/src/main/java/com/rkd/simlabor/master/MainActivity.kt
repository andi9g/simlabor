package com.rkd.simlabor.master

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.rkd.simlabor.LoginActivity
import com.rkd.simlabor.master.ui.theme.SimlaborTheme
import com.rkd.simlabor.navigation.BottomNavigationBar
import com.rkd.simlabor.topbar.TopBar
import com.rkd.simlabor.R
import com.rkd.simlabor.data.DataStoreManager
import com.rkd.simlabor.navigation.NavigationHost
import com.rkd.simlabor.network.ApiClient
import com.rkd.simlabor.sp.SharedPreferencesHelper
import com.rkd.simlabor.toast.showCustomToast
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sp = SharedPreferencesHelper(applicationContext)
        sp.getData("message")?.let {
            Toast.makeText(this, "Selamat Datang", Toast.LENGTH_LONG).show()
        }
        enableEdgeToEdge()
//        validateTokenAndNavigate()
        setContent {
            SimlaborTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        ),
                    topBar = {
                        TopBar(
                            title = "SIMLABOR",
                            onNotificationClick = {
                                Toast.makeText(this, "Notification Clicked!", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    NavigationHost(navController, Modifier.padding(innerPadding))
                }
            }
        }




    }







}

@Preview(showBackground = true)
@Composable
private fun tampil() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            ),
        topBar = {
            TopBar(
                title = "SIMLABOR",
                onNotificationClick = {
//                    Toast.makeText(this, "Notification Clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
            NavigationHost(navController, Modifier.padding(innerPadding))
    }
}