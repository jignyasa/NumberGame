package com.ma.numbergame.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ma.numbergame.R
import com.ma.numbergame.db.entity.RandomNumberEntity
import kotlinx.android.synthetic.main.result_item.view.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ResultAdapter(val context:Context) :RecyclerView.Adapter<ResultAdapter.VHolder>() {
    private var alResults=ArrayList<RandomNumberEntity>()
    class VHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.result_item,parent,false)
        return VHolder(view)
    }

    override fun getItemCount(): Int {
        return alResults.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        val item=alResults.get(position)
        if(position==0)
        {
            holder.itemView.tvNo.text="1"
            holder.itemView.tvGuessNo.text="Guess No"
            holder.itemView.tvDateTime.text="DateTime"
            holder.itemView.tvResult.text="Result"
        }else {
            var cal=Calendar.getInstance()
            holder.itemView.tvNo.text = item.no.toString()
            holder.itemView.tvGuessNo.text = item.guessNo
            cal.set(Calendar.MILLISECOND,item.dateTime.toInt())
            holder.itemView.tvDateTime.text = getDateFormate("dd-MM-yyyy h:mm a",cal)
            holder.itemView.tvResult.text = item.result
        }
    }
    fun addData(list:ArrayList<RandomNumberEntity>)
    {
        alResults=list
        Log.e("data adapter",list.size.toString())
        notifyDataSetChanged()
    }

    fun getDateFormate(dateFormate:String,cal: Calendar):String
    {
        var sDate=""
        val sdf= SimpleDateFormat(dateFormate, Locale.ENGLISH)
        try {
            sDate=sdf.format(cal.timeInMillis)
        }catch (e: Exception)
        {
            Log.e("error",e.message)
        }
        return sDate
    }
}