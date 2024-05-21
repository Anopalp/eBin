package com.dicoding.ebin_v1.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllUsersResponse(

	@field:SerializedName("GetAllUsersResponse")
	val getAllUsersResponse: List<GetAllUsersResponseItem?>? = null
) : Parcelable

@Parcelize
data class RequestItem(

	@field:SerializedName("reward")
	val reward: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("end")
	val end: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Trash(

	@field:SerializedName("plastic")
	val plastic: Int? = null,

	@field:SerializedName("paper")
	val paper: Int? = null
) : Parcelable

@Parcelize
data class GetAllUsersResponseItem(

	@field:SerializedName("request")
	val request: List<RequestItem?>? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phone")
	val phone: Long? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("point")
	val point: Int? = null,

	@field:SerializedName("transaction")
	val transaction: List<TransactionItem?>? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable

@Parcelize
data class TransactionItem(

	@field:SerializedName("reward")
	val reward: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("trashStation")
	val trashStation: String? = null,

	@field:SerializedName("trash")
	val trash: Trash? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable
