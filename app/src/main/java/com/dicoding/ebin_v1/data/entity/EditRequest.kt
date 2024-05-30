package com.dicoding.ebin_v1.data.entity

data class EditRequest(
    val title: String,
    val description: String,
    val address: String,
    val reward: Int
)
