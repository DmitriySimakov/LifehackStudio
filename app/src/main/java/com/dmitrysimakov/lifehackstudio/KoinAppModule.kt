package com.dmitrysimakov.lifehackstudio

import com.dmitrysimakov.lifehackstudio.data.ApiRequests
import com.dmitrysimakov.lifehackstudio.ui.companies_list.CompaniesListViewModel
import com.dmitrysimakov.lifehackstudio.ui.company_info.CompanyInfoViewModel
import me.linshen.retrofit2.adapter.LiveDataCallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideCompaniesService() }
    viewModel { CompaniesListViewModel(get()) }
    viewModel { CompanyInfoViewModel(get()) }
}

private fun provideCompaniesService(): ApiRequests {
    return Retrofit.Builder()
        .baseUrl("https://lifehack.studio/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()
        .create(ApiRequests::class.java)
}