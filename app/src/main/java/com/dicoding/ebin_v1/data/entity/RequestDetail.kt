package com.dicoding.ebin_v1.data.entity

data class RequestDetail(
    val receiverID: String,
    val title: String,
    val description: String,
    val address: String,
    val reward: Int
)
