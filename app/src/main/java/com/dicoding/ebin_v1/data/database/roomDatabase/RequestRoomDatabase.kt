package com.dicoding.ebin_v1.data.database.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.ebin_v1.data.database.dao.RequestDao
import com.dicoding.ebin_v1.data.database.entity.RequestDb

@Database(entities = [RequestDb::class], version = 1)
abstract class RequestRoomDatabase : RoomDatabase() {
    abstract fun requestDao(): RequestDao

    companion object {

    }
}