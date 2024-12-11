package com.rkd.simlabor.data

data class Aset(
    val idaset: Int,
    val namaaset: String,
    val tahunaset: String,
    val jumlahaset: Int,
    val created_at: String?,
    val updated_at: String?
)

data class DataResponseAset(
    val data: List<Aset>
)