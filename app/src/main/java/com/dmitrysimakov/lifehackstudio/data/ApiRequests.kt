package com.dmitrysimakov.lifehackstudio.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {
    @GET("/test_task/test.php")
    fun companies(): Call<List<CompanyItem>>

    @GET("/test_task/test.php")
    fun company(@Query("id") id: Long): Call<List<CompanyInfo>>
}