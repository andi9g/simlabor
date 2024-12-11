package com.rkd.simlabor.master.content.item

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rkd.simlabor.R
import com.rkd.simlabor.data.Aset

@Composable
fun ItemInvetaris(
    aset: Aset,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Memberikan jarak pada Row untuk layout yang lebih baik
                .padding(top = 5.dp) // Memberikan jarak pada Row untuk layout yang lebih baik
        ) {

            // Menampilkan gambar
            AsyncImage(
                model = "https://t4.ftcdn.net/jpg/04/70/29/97/360_F_470299797_UD0eoVMMSUbHCcNJCdv2t8B2g1GVqYgs.jpg",
                contentDescription = "Gambar Url",
                modifier = Modifier
                    .size(80.dp), // Opsional: border untuk gambar
                contentScale = ContentScale.Crop,
                onError = {
                    Log.d("error", "error")
                }
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Kolom untuk nama barang dan jumlah
            Column(
                modifier = Modifier.weight(1f) // Agar Column mengambil sisa ruang yang ada
            ) {
                Text(
                    text = aset.namaaset,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Text(
                        text = "Jumlah: ${aset.jumlahaset}",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(start = 85.dp))
            Divider(
                color = colorResource(id = R.color.grey), // Warna garis
                thickness = 1.dp, // Ketebalan garis
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun show() {
    ItemInvetaris(aset = Aset(
        1,
        "Coba",
        "2023",
        2,
        "",
        ""
    ))
}