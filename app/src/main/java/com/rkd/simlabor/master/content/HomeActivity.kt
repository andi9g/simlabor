package com.rkd.simlabor.master.content

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rkd.simlabor.R



@Composable
fun HomeActivityShow(text: String) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
        .background(colorResource(id = R.color.purple_500))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(85.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape)
                    .padding(horizontal = 15.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.cube_logo),
                    contentDescription = "LOGO",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(85.dp)
                )

            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = "ANDI RIZKY BAYU PUTRA",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.light),
                    style = MaterialTheme.typography.titleLarge
                )
                Row {
                    Text(text = "Admin - ", color = MaterialTheme.colorScheme.primary)
                    Text(text = "Guru", color = MaterialTheme.colorScheme.primary)
                }
            }
        }








        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topEnd = 20.dp,
                        topStart = 20.dp
                    )
                )
                .background(colorResource(id = R.color.white)),

            ){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(30.dp),

                ) {
                Column{
                    Row {
                        Text(
                            text = "SELAMAT DATANG, ",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "SIMLABOR",
                            style = MaterialTheme.typography.titleLarge,
                            color = colorResource(id = R.color.purple_700),
                            fontWeight = FontWeight.Bold
                        )
                    }


                    Text(text = "Sistem Informasi Manajemen Laboratorium",
                        style = MaterialTheme.typography.titleMedium)

                    Spacer(modifier = Modifier.height(25.dp))

                    BackgroundImageButton(
                        onClick = {
                            Toast.makeText(context, "Selamat", Toast.LENGTH_LONG).show()
                        },
                        text1 = "Data Peminjaman",
                        text2 = "Total : 2",
                        imageVector = Icons.Default.DateRange
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    BackgroundImageButton(
                        onClick = {
                            Toast.makeText(context, "Selamat", Toast.LENGTH_LONG).show()
                        },
                        text1 = "Data Pengembalian",
                        text2 = "Total : 0",
                        imageVector = Icons.Default.Update
                    )

                }



            }





        }



    }


}

@Composable
fun BackgroundImageButton(onClick: () -> Unit, text1: String, text2: String, imageVector: ImageVector) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Memastikan button mengisi lebar penuh
            .clip(RoundedCornerShape(10.dp)) // Membuat sudut melengkung
            .border(1.dp, colorResource(id = R.color.lightgreenActive), RectangleShape)
            .background(colorResource(id = R.color.lightgreen)) // Background transparan untuk Box
            .clickable { onClick() },
        contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){

            Icon(imageVector = imageVector,
                contentDescription = "Icon",
                modifier = Modifier
                    .size(80.dp),
                tint = colorResource(id = R.color.lightgreenActive2)
            )


            Spacer(modifier = Modifier.width(15.dp))

            // Teks di atas gambar
            Column(
                modifier = Modifier
                    .fillMaxWidth(),

                ) {
                Text(
                    text = text1,
                    color = colorResource(id = R.color.purple_500),
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                )

                Text(
                    text = text2,
                    color = colorResource(id = R.color.grey),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }




    }
}



@Preview(showBackground = true)
@Composable
private fun lihat() {
    HomeActivityShow(text = "Text")
}