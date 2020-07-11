package com.ma.numbergame.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "random_number")
 class RandomNumberEntity() {
    @PrimaryKey(autoGenerate = true)
    var no:Int=0
    var guessNo:String=""
    var dateTime:Long=0
    var result:String=""
}