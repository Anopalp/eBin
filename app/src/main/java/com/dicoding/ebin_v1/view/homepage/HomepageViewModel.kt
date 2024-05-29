package com.dicoding.ebin_v1.view.homepage

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ebin_v1.data.response.TrashStationsResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomepageViewModel () : ViewModel() {

    private val _response = MutableLiveData<List<TrashStationsResponseItem>>()
    val response: LiveData<List<TrashStationsResponseItem>> = _response

    fun getAllTrashStations() {
        try {
            val client = ApiConfig.getApiService().getAllTrashStations()
            client.enqueue(object : Callback<List<TrashStationsResponseItem>> {
                override fun onResponse(
                    call: Call<List<TrashStationsResponseItem>>,
                    response: Response<List<TrashStationsResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _response.value = responseBody ?: emptyList()
                        }
                    } else {
                        Log.e("RESPONSE SUCCESSFUL", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<TrashStationsResponseItem>>, t: Throwable) {
                    Log.e("API ERROR", "onFailure: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("API ERROR", "onFailure: ${e.message}")
        }
    }

    fun getTrashStation(tid: String) {
        try {
            val client = ApiConfig.getApiService().getTrashstation(tid)

            client.enqueue(object : Callback<TrashStationsResponseItem> {
                override fun onResponse(
                    call: Call<TrashStationsResponseItem>,
                    response: Response<TrashStationsResponseItem>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _response.value = arrayListOf(responseBody)
                            Log.d("CALL TRASH STATION", response.toString())
                        }
                    } else {
                        Log.e("CALL TRASH STATION FAIL", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TrashStationsResponseItem>, t: Throwable) {
                    Log.e("API ERROR", "onFailure: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("API ERROR", "onFailure: ${e.message}")
        }
    }
}