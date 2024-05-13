package com.dicoding.ebin_v1.data.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class RequestDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "requestId")
    var requestId: Int = 0,

    @ColumnInfo(name = "owner")
    var owner: String? = null,

    @ColumnInfo(name = "taker")
    var taker: String? = null,

    @ColumnInfo(name = "amount")
    var amount: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "requestStatus")
    var requestStatus: String? = null,

    @ColumnInfo(name = "requestStart")
    var requestStart: String? = null,

    @ColumnInfo(name = "requestEnd")
    var requestEnd: String? = null,

    @ColumnInfo(name = "detail")
    var detail: String? = null,

    @ColumnInfo(name = "address")
    var address: String? = null,

    @ColumnInfo(name = "reward")
    var reward: String? = null
) : Parcelable
