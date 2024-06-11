package com.dicoding.ebin_v1.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TrashStationsResponse(

	@field:SerializedName("TrashStationsResponse")
	val trashStationsResponse: List<TrashStationsResponseItem?>? = null
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("latitude")
	val latitude: Float? = null,

	@field:SerializedName("longitude")
	val longitude: Float? = null
) : Parcelable

@Parcelize
data class TrashStationsResponseItem(

	@field:SerializedName("openHours")
	val openHours: OpenHours? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("available")
	val available: Boolean? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("capacity")
	val capacity: Float? = null
) : Parcelable

@Parcelize
data class OpenHours(

	@field:SerializedName("closeTime")
	val closeTime: Int? = null,

	@field:SerializedName("openTime")
	val openTime: Int? = null
) : Parcelable
