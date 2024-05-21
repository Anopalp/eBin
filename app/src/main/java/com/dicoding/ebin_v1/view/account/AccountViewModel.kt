package com.dicoding.ebin_v1.view.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel() : ViewModel() {

    private val _response = MutableLiveData<List<GetAllUsersResponseItem>>()
    val response: LiveData<List<GetAllUsersResponseItem>> = _response

    fun getUser() {
        try {
            val client = ApiConfig.getApiService().getAllUsers()
            client.enqueue(object : Callback<List<GetAllUsersResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllUsersResponseItem>>,
                    response: Response<List<GetAllUsersResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _response.value = responseBody ?: emptyList()
                            Log.d("GET USER 1", _response.value.toString())
                        } else {
                            Log.e("GET USER 2", "onFailure: ${response.message()}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetAllUsersResponseItem>>, t: Throwable) {
                    Log.e("API ERROR 1", "onFailure: ${t.message}")
                }
            })
        } catch (e: Exception) {
            Log.e("API ERROR 2", "onFailure: ${e.message}")
        }
    }
}