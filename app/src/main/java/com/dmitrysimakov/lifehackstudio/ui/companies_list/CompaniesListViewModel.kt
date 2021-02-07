package com.dmitrysimakov.lifehackstudio.ui.companies_list

import androidx.lifecycle.*
import com.dmitrysimakov.lifehackstudio.data.ApiRequests
import com.dmitrysimakov.lifehackstudio.data.CompanyItem
import kotlinx.coroutines.launch
import retrofit2.await

class CompaniesListViewModel(
    private val api: ApiRequests
) : ViewModel() {

    val companies = liveData {
        emit(api.companies().await())
    }
}