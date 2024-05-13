package com.dicoding.ebin_v1.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TrashStationsResponse(

	@field:SerializedName("TrashStationsResponse")
	val trashStationsResponse: List<TrashStationsResponseItem?>? = null
)

@Parcelize
data class OpenHours(

	@field:SerializedName("closeTime")
	val closeTime: Int? = null,

	@field:SerializedName("openTime")
	val openTime: Int? = null
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("latitude")
	val latitude: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Int? = null
) : Parcelable

@Parcelize
data class TrashStationsResponseItem(

	@field:SerializedName("openHours")
	val openHours: OpenHours? = null,

	@field:SerializedName("available")
	val available: Boolean? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("capacity")
	val capacity: Int? = null
) : Parcelable
