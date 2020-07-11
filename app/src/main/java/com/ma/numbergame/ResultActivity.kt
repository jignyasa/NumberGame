package com.ma.numbergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ma.numbergame.adapter.ResultAdapter
import com.ma.numbergame.db.DatabaseHelper
import com.ma.numbergame.db.entity.RandomNumberEntity
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*
import kotlin.collections.ArrayList

class ResultActivity : AppCompatActivity() {
    private lateinit var adapter: ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        adapter= ResultAdapter(this)
        rvResult.adapter=adapter
        rvResult.layoutManager=LinearLayoutManager(this)
        getResultData()
    }

    private fun getResultData() {
        DatabaseHelper.getDataBase(this).getRandomNumberDao().getResults().observe(this, Observer {
            val item=RandomNumberEntity()
           // list.add(item)
            if(it.size>0) {
                var list=it as ArrayList<RandomNumberEntity>
                list.add(0,RandomNumberEntity())
                 adapter.addData(list)
            }
        })
    }
}