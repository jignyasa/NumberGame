package com.ma.numbergame.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ma.numbergame.db.dao.RandomNumberDao
import com.ma.numbergame.db.entity.RandomNumberEntity

@Database(entities = [RandomNumberEntity::class],version = 1,exportSchema = false)
abstract class DatabaseHelper :RoomDatabase()
{
   companion object{
       fun getDataBase(context: Context):DatabaseHelper
       {
           var INSTANCE:DatabaseHelper?=null
           if(INSTANCE==null)
               INSTANCE=Room.databaseBuilder(context,DatabaseHelper::class.java,"random_number").allowMainThreadQueries().build()
       return INSTANCE
       }
   }
    abstract fun getRandomNumberDao():RandomNumberDao
}