package com.dicoding.ebin_v1.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val points: Int,
    val username: String,
    val fullName: String,
    val phone: String,
    val email: String,
    val address: String
) : Parcelable
