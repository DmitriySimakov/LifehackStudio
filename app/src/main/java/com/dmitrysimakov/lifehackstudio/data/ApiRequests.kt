package com.dmitrysimakov.lifehackstudio.data

import androidx.lifecycle.LiveData
import me.linshen.retrofit2.adapter.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {
    @GET("/test_task/test.php")
    fun companies(): LiveData<ApiResponse<List<CompanyItem>>>

    @GET("/test_task/test.php")
    fun company(@Query("id") id: Long): LiveData<ApiResponse<List<CompanyInfo>>>
}