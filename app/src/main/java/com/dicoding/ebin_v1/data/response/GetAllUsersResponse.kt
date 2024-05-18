package com.dicoding.ebin_v1.data.response

import com.google.gson.annotations.SerializedName

data class GetAllUsersResponse(

	@field:SerializedName("GetAllUsersResponse")
	val getAllUsersResponse: List<GetAllUsersResponseItem?>? = null
)

data class Start(

	@field:SerializedName("seconds")
	val seconds: Int? = null,

	@field:SerializedName("nanoseconds")
	val nanoseconds: Int? = null
)

data class Timestamp(

	@field:SerializedName("seconds")
	val seconds: Int? = null,

	@field:SerializedName("nanoseconds")
	val nanoseconds: Int? = null
)

data class TransactionItem(

	@field:SerializedName("reward")
	val reward: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("trashStation")
	val trashStation: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Timestamp? = null,

	@field:SerializedName("trash")
	val trash: Trash? = null
)

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

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("transaction")
	val transaction: List<TransactionItem?>? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Trash(

	@field:SerializedName("plastic")
	val plastic: Int? = null,

	@field:SerializedName("paper")
	val paper: Int? = null
)

data class End(

	@field:SerializedName("seconds")
	val seconds: Int? = null,

	@field:SerializedName("nanoseconds")
	val nanoseconds: Int? = null
)

data class RequestItem(

	@field:SerializedName("reward")
	val reward: Int? = null,

	@field:SerializedName("senderID")
	val senderID: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("receiverID")
	val receiverID: String? = null,

	@field:SerializedName("start")
	val start: Start? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("end")
	val end: End? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
