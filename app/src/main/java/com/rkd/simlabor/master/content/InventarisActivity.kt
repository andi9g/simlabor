package com.rkd.simlabor.master.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rkd.simlabor.R
import com.rkd.simlabor.master.content.item.ItemInvetaris
import com.rkd.simlabor.model.DataInvetarisModel


//@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun show() {
    InventarisScreenShow(text = "DATA INVENTARIS")
}


@Composable
fun InventarisScreenShow(text: String) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.purple_500))
    ) {

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .padding(vertical = 10.dp)
            ) {
                Box(modifier = Modifier) {
                    Icon(
                        imageVector = Icons.Default.DashboardCustomize,
                        contentDescription = "Icon",
                        modifier = Modifier.size(60.dp),
                        tint = colorResource(id = R.color.white)

                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(
                        text = "${text}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = colorResource(id = R.color.light),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row {
//                        Text(text = "Data - ", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            var searchQuery by remember { (mutableStateOf("")) }


            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { newQuery ->
                        searchQuery = newQuery
                    },
                    modifier = Modifier
                        .padding(
                            bottom = 20.dp
                        )

                )
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



                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                ) {
                    item{
                        for (i in 1..10) {
                            ItemInvetaris(dataInvetarisModel = DataInvetarisModel(
                                idbarang = 2,
                                namabarang = "Komputer",
                                stok = 12,
                                tahun = "2024",
                                gambar = "https://cdn-icons-png.flaticon.com/512/9187/9187604.png"
                            ))
                        }
                    }







                }


            }

        }




}


