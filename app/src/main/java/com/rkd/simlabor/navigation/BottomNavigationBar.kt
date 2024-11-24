package com.rkd.simlabor.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rkd.simlabor.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.Inventaris, Screen.Scan, Screen.Histori, Screen.Profile)
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route


    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Garis di lebar penuh
                .height(1.5.dp) // Ketebalan border (1px)
                .background(colorResource(id = R.color.lightgrey)) // Warna biru untuk border top
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .background(colorResource(id = R.color.white))
                .padding(7.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            items.forEach { screen ->
                if (screen == Screen.Scan) {
                    FloatingActionButton(
                        onClick = { navController.navigate(screen.route) },
                        containerColor = colorResource(id = R.color.lightgreen),
                        modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            screen.icon,
                            contentDescription = screen.title,
                            modifier = Modifier
                                .size(50.dp),
                            tint = colorResource(id = R.color.purple_500)


                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.clip(
                            RoundedCornerShape(0.dp)
                        ),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = { navController.navigate(screen.route) },
                            modifier = Modifier.padding(0.dp)
                        ) {

                            Icon(
                                screen.icon,
                                contentDescription = screen.title,
                                tint = if (currentRoute == screen.route) colorResource(id = R.color.purple_700) else Color.Gray
                            )
                        }

                        Text(
                            text = screen.title,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .offset(y = (-7).dp),
                            color = if (currentRoute == screen.route) colorResource(id = R.color.purple_700) else Color.Gray
                        )

                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun menu() {
    BottomNavigationBar(navController = rememberNavController())
}