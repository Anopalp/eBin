package com.dicoding.ebin_v1.data.response

import com.google.gson.annotations.SerializedName

data class PostRequestResponse(

	@field:SerializedName("reward")
	val reward: Int? = null,

	@field:SerializedName("senderID")
	val senderID: Any? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("receiverID")
	val receiverID: String? = null,

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("end")
	val end: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
