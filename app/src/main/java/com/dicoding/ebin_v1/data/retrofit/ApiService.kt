package com.dicoding.ebin_v1.data.retrofit

import com.dicoding.ebin_v1.data.entity.UserDetail
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.data.response.SignupResponse
import com.dicoding.ebin_v1.data.response.TrashStationsResponse
import com.dicoding.ebin_v1.data.response.TrashStationsResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("trash-stations")
    fun getAllTrashStations() : Call<List<TrashStationsResponseItem>>

    @GET("users")
    fun getAllUsers() : Call<List<GetAllUsersResponseItem>>

    @POST("users")
    fun postUser(@Body userDetail: UserDetail): Call<SignupResponse>
}