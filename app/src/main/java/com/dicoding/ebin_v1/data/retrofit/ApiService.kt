package com.dicoding.ebin_v1.data.retrofit

import com.dicoding.ebin_v1.data.entity.RequestDetail
import com.dicoding.ebin_v1.data.entity.UserDetail
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.data.response.PostRequestResponse
import com.dicoding.ebin_v1.data.response.SignupResponse
import com.dicoding.ebin_v1.data.response.TrashStationsResponse
import com.dicoding.ebin_v1.data.response.TrashStationsResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("trash-stations")
    fun getAllTrashStations() : Call<List<TrashStationsResponseItem>>

    @GET("trash-stations/{id}")
    fun getTrashstation(
        @Path("id") id: String
    ) : Call<TrashStationsResponseItem>

    @GET("users")
    fun getAllUsers() : Call<List<GetAllUsersResponseItem>>

    @POST("users")
    fun postUser(@Body userDetail: UserDetail): Call<SignupResponse>

    @PUT("users/{id}")
    fun editUser(
        @Path("id") id: String,
        @Body userDetail: UserDetail
    ) : Call<SignupResponse>

    @GET("requests")
    fun getAllRequests() : Call<List<GetAllRequestResponseItem>>

    @POST("requests")
    fun postRequest(@Body requestDetail: RequestDetail): Call<PostRequestResponse>
}
