package com.dicoding.ebin_v1.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrashStation(
    val trashStationId: String,
    val pic: String,
    val capacity: Double,
    val address: String,
    val openHours: String,
    val lat: Double,
    val lon: Double,
    val distance: Double? = null
) : Parcelable
