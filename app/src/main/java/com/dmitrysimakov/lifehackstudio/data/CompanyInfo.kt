package com.dmitrysimakov.lifehackstudio.data

data class CompanyInfo(
    val id: Long,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
)