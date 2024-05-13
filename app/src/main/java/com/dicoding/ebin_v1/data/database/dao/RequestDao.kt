package com.dicoding.ebin_v1.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.ebin_v1.data.database.entity.RequestDb

@Dao
interface RequestDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(requestDb: RequestDb)

    @Update
    fun update(requestDb: RequestDb)

    @Delete
    fun delete(requestDb: RequestDb)

    @Query("SELECT * FROM requestdb ORDER BY requestId ASC")
    fun getAllRequests(): LiveData<List<RequestDb>>
}