package com.dicoding.ebin_v1.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService() : ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://ebin-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}