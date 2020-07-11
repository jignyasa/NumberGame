package com.ma.numbergame.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ma.numbergame.db.entity.RandomNumberEntity

@Dao
interface RandomNumberDao {
    @Query("SELECT * FROM random_number")
    fun getResults():LiveData<List<RandomNumberEntity>>

    @Insert
    fun addResult(item:RandomNumberEntity)
}