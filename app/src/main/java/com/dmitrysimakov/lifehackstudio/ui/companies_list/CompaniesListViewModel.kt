package com.dmitrysimakov.lifehackstudio.ui.companies_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitrysimakov.lifehackstudio.data.ApiRequests
import com.dmitrysimakov.lifehackstudio.data.CompanyItem
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class CompaniesListViewModel(
    private val api: ApiRequests
) : ViewModel() {

    private val _companies = MutableLiveData<List<CompanyItem>>()
    val companies: LiveData<List<CompanyItem>> = _companies

    fun loadCompanies() {
        viewModelScope.launch {
            val response = api.companies().awaitResponse()
            if (response.isSuccessful) {
                _companies.value = response.body()
            }
        }
    }
}