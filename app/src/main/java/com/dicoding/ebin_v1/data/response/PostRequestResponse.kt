package com.dicoding.ebin_v1.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PostRequestResponse(

	@field:SerializedName("reward")
	val reward: Int? = null,

	@field:SerializedName("senderID")
	val senderID: SenderID? = null,

	@field:SerializedName("receiverID")
	val receiverID: ReceiverID? = null,

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

