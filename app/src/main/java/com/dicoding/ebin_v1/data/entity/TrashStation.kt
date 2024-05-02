package com.dicoding.ebin_v1.data.entity

data class TrashStation(
    val trashStationId: String,
    val pic: String,
    val capacity: Double,
    val lat: Double,
    val lon: Double,
    val distance: Double? = null
)
