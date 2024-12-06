package com.rkd.simlabor.sp

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    // Fungsi untuk menyimpan data
    fun saveData(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    // Fungsi untuk mengambil data
    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    // Fungsi untuk menghapus data
    fun clearData(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }
}