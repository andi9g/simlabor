package com.rkd.simlabor.master.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rkd.simlabor.R
import com.rkd.simlabor.data.DataStoreManager
import com.rkd.simlabor.master.content.item.ItemInvetaris
import com.rkd.simlabor.model.AsetViewModel
import com.rkd.simlabor.model.AsetViewModelFactory
import kotlinx.coroutines.launch


//@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun show() {
    InventarisScreenShow(
        text = "DATA INVENTARIS"
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventarisScreenShow(text: String) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val viewModel: AsetViewModel = viewModel(
        factory = AsetViewModelFactory(dataStoreManager)
    )

    val asetList by viewModel.asetList
    var currentPage by remember { mutableStateOf(1) }
    var keyword by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()


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

//            var searchQuery by remember { (mutableStateOf("")) }




            Box(modifier = Modifier.padding(horizontal = 18.dp)) {


                // SearchBar untuk pencarian
                SearchMasterActivity(
                    query = keyword,
                    onQueryChange = { keyword = it },
                    onSearch = {
                        coroutineScope.launch {
                            viewModel.fetchData(keyword = keyword, page = currentPage)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Search"
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

                Column {
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                    ) {
                        items(asetList) { aset ->
                            ItemInvetaris(aset)
                        }

                    }
                }




            }

        }




}


