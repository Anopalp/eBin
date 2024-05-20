package com.dicoding.ebin_v1.view.marketplace

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketplaceViewModel() : ViewModel() {

    private val _response = MutableLiveData<List<GetAllRequestResponseItem>>()
    val response: LiveData<List<GetAllRequestResponseItem>> = _response

    fun getAllRequests() {
        try {
            val client = ApiConfig.getApiService().getAllRequests()
            client.enqueue(object : Callback<List<GetAllRequestResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllRequestResponseItem>>,
                    response: Response<List<GetAllRequestResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _response.value = responseBody ?: emptyList()
                        } else {
                            Log.e("RESPONSE SUCCESSFUL", "onFailure: ${response.message()}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetAllRequestResponseItem>>, t: Throwable) {
                    Log.e("API ERROR", "onFailure: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("API ERROR", "onFailure: ${e.message}")
            _response.value = arrayListOf(GetAllRequestResponseItem())
        }
    }
}