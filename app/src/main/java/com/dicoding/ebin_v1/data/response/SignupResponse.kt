package com.dicoding.ebin_v1.data.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(

	@field:SerializedName("request")
	val request: List<Any?>? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("point")
	val point: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("transaction")
	val transaction: List<Any?>? = null,

	@field:SerializedName("username")
	val username: String? = null
)
