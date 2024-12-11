package com.rkd.simlabor.model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rkd.simlabor.data.Aset
import com.rkd.simlabor.data.DataStoreManager
import com.rkd.simlabor.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class AsetViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    private val _asetList = mutableStateOf<List<Aset>>(emptyList())
    val asetList: State<List<Aset>> = _asetList

    init {
        viewModelScope.launch {
            fetchData(keyword = "", page = 1)
        }
    }

    suspend fun fetchData(keyword: String, page: Int) {
        try {
            // Ambil token dari DataStoreManager
            val token = dataStoreManager.getToken()
            if (!token.isNullOrEmpty()) {
                val bearerToken = "Bearer $token"
                val response = ApiClient.apiService.getAset(bearerToken, keyword, page)
                _asetList.value = response.data
            } else {
                Log.e("AsetViewModel", "Token is null or empty")
            }
        } catch (e: Exception) {
            Log.e("AsetViewModel", "Error fetching data: ${e.message}")
        }
    }
}


class AsetViewModelFactory(
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AsetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AsetViewModel(dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}