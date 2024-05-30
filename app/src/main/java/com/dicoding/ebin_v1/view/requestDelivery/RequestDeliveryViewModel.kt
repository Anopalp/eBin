package com.dicoding.ebin_v1.view.requestDelivery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ebin_v1.data.entity.UpdateBody
import com.dicoding.ebin_v1.data.response.UpdateRequestStatusResponse
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestDeliveryViewModel() : ViewModel() {

    private val _response = MutableLiveData<UpdateRequestStatusResponse?>()
    val response: LiveData<UpdateRequestStatusResponse?> = _response

    fun updateStatus(tid: String, uid: String, status: String) {
        try {
            Log.d("VERIFY", "tid: $tid\nuid: $uid\nstatus: $status")
            val client = ApiConfig.getApiService().updateRequestStatus(tid, UpdateBody(uid, status))

            client.enqueue(object : Callback<UpdateRequestStatusResponse> {
                override fun onResponse(
                    call: Call<UpdateRequestStatusResponse>,
                    response: Response<UpdateRequestStatusResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _response.value = responseBody
                            Log.d("UPDATE STATUS SUCCESS", _response.value.toString())
                        } else {
                            Log.e("UPDATE STATUS FAILURE", "Response body is null")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("UPDATE STATUS FAILURE", "Error: $errorBody")
                    }
                }

                override fun onFailure(call: Call<UpdateRequestStatusResponse>, t: Throwable) {
                    Log.e("API ERROR 1", "onFailure: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("API ERROR 2", "onFailure: ${e.message}")
        }
    }
}