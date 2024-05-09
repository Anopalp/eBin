package com.dicoding.ebin_v1.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Requests(
    val owner: String,
    val amount: Int,
    val type: String,
    val reward: Int,
    val requestStart: String? = null,
    val requestEnd: String? = null
) : Parcelable
