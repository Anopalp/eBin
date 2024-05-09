package com.dicoding.ebin_v1.data.entity

data class Transaction(
    val date: String,
    val points: Int,
    val amount: Int,
    val type: String,
    val trashStation: String
)
