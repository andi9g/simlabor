package com.rkd.simlabor.master.content.item

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rkd.simlabor.model.DataInvetarisModel

@Composable
fun ItemInvetaris(
    dataInvetarisModel: DataInvetarisModel,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp) // Memberikan jarak pada Row untuk layout yang lebih baik
    ) {

        // Menampilkan gambar
        AsyncImage(
            model = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/480px-User_icon_2.svg.png",
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
                text = dataInvetarisModel.namabarang,
                style = MaterialTheme.typography.titleMedium
            )
            Row {
                Text(
                    text = "Jumlah: ${dataInvetarisModel.stok}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun show() {
    ItemInvetaris(
        dataInvetarisModel = DataInvetarisModel(
            idbarang = 1,
            namabarang = "Komputer",
            stok = 15,
            gambar = "https://cdn-icons-png.flaticon.com/512/9187/9187604.png",
            tahun = "2024"
        )
    )
}