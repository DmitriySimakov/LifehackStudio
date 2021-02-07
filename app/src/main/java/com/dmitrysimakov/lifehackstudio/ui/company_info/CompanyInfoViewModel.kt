package com.dmitrysimakov.lifehackstudio.ui.company_info

import android.util.Log
import androidx.lifecycle.*
import com.dmitrysimakov.lifehackstudio.data.ApiRequests
import com.dmitrysimakov.lifehackstudio.data.CompanyInfo
import kotlinx.coroutines.launch
import retrofit2.await

class CompanyInfoViewModel(
    private val api: ApiRequests
) : ViewModel() {

    private val companyId = MutableLiveData<Long>()

    val companyInfo = companyId.switchMap { id -> api.company(id) }

    fun setCompanyId(id: Long) {
        companyId.value = id
    }
}