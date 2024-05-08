package com.dicoding.ebin_v1.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Requests(
    val owner: String,
    val amount: Int,
    val type: String,
    val reward: Int
) : Parcelable
