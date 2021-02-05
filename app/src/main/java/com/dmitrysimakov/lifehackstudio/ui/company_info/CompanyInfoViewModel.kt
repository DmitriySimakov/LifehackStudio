package com.dmitrysimakov.lifehackstudio.ui.company_info

import android.util.Log
import androidx.lifecycle.*
import com.dmitrysimakov.lifehackstudio.data.ApiRequests
import com.dmitrysimakov.lifehackstudio.data.CompanyInfo
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class CompanyInfoViewModel(
    private val api: ApiRequests
) : ViewModel() {

    private val _companyInfo = MutableLiveData<CompanyInfo>()
    val companyInfo: LiveData<CompanyInfo> = _companyInfo

    fun setCompanyId(id: Long) {
        viewModelScope.launch {
            try {
                val response = api.company(id).awaitResponse()
                if (response.isSuccessful) {
                    _companyInfo.value = response.body()?.first()
                } else {
                    setErrorInfo()
                }
            } catch (e: Exception) {
                setErrorInfo()
                Log.e(TAG, "setCompanyId: ", e)
            }
        }
    }

    private fun setErrorInfo() {
        _companyInfo.value = CompanyInfo(description = "Не удалось загрузить страницу")
    }

    companion object {
        private val TAG = CompanyInfoViewModel::class.simpleName
    }
}