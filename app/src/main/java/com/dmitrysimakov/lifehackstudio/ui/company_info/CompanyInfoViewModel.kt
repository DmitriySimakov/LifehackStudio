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

    val companyInfo = companyId.switchMap { id ->
        liveData {
            val info = try {
                api.company(id).await().first()
            } catch (e: Exception) {
                Log.e(TAG, "setCompanyId: ", e)
                CompanyInfo(description = "Не удалось загрузить страницу")
            }
            emit(info)
        }
    }

    fun setCompanyId(id: Long) {
        companyId.value = id
    }

    companion object {
        private val TAG = CompanyInfoViewModel::class.simpleName
    }
}