package com.ma.numbergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ma.numbergame.db.DatabaseHelper
import com.ma.numbergame.db.entity.RandomNumberEntity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener{
    private val questionMark="?"
    private val success="8888"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btn10.setOnClickListener(this)
        btn11.setOnClickListener(this)
        btn12.setOnClickListener(this)
        btn13.setOnClickListener(this)
        btn14.setOnClickListener(this)
        btn15.setOnClickListener(this)
        btn16.setOnClickListener(this)
        btnResult.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val rand=(0..9)
        when(v?.id)
        {
            R.id.btn1->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn2->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn3->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn4->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn5->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn6->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn7->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn8->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn9->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn10->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn11->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn12->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn13->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn14->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn15->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btn16->{
                val no=rand.random()
                setRandomNumber(no)
            }
            R.id.btnResult->{
                if(validation())
                {
                    val no1=tvText1.text.toString()
                    val no2=tvText2.text.toString()
                    val no3=tvText3.text.toString()
                    val no4=tvText4.text.toString()
                    var number=no1.plus(no2).plus(no3).plus(no4)
                    if(number.equals(success)) {
                        Toast.makeText(this, "you are winner", Toast.LENGTH_SHORT).show()
                        addResultData(number,"you are winner")
                    }
                    else {
                        Toast.makeText(this, "you lose try again", Toast.LENGTH_SHORT).show()
                        addResultData(number,"you lose try again ")
                    }
                }
            }
        }
    }

    private fun addResultData(number: String, msg: String) {
        val item=RandomNumberEntity()
       // item.no=2
        item.guessNo=number
        item.dateTime=System.currentTimeMillis()
        item.result=msg
        Observable.fromCallable{
            DatabaseHelper.getDataBase(this).getRandomNumberDao().addResult(item)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<Unit>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Unit) {
                    clearText()
                    startActivity(Intent(this@MainActivity,ResultActivity::class.java))
                }

                override fun onError(e: Throwable) {
                    Log.e("error",e.message)
                }

            })

    }

    private fun clearText() {
        tvText1.setText(getString(R.string.question))
        tvText2.setText(getString(R.string.question))
        tvText3.setText(getString(R.string.question))
        tvText4.setText(getString(R.string.question))
    }

    private fun validation():Boolean {
        var isValid=false
        if(tvText1.text.toString().equals(questionMark))
        {
           isValid=false
            Toast.makeText(this,"please selct no",Toast.LENGTH_SHORT).show()
        }else if(tvText2.text.toString().equals(questionMark)){
            isValid=false
            Toast.makeText(this,"please selct no",Toast.LENGTH_SHORT).show()
        }else if(tvText3.text.toString().equals(questionMark)){
            isValid=false
            Toast.makeText(this,"please selct no",Toast.LENGTH_SHORT).show()
        }else if(tvText4.text.toString().equals(questionMark)){
            isValid=false
            Toast.makeText(this,"please selct no",Toast.LENGTH_SHORT).show()
        }else
        {
            isValid=true
        }
        return isValid
    }

    private fun setRandomNumber(no: Int) {
        if(tvText1.text.toString().equals(questionMark))
        {
            tvText1.text=no.toString()
        }else if(tvText2.text.toString().equals(questionMark)){
            tvText2.text=no.toString()
        }else if(tvText3.text.toString().equals(questionMark)){
            tvText3.text=no.toString()
        }else if(tvText4.text.toString().equals(questionMark)){
            tvText4.text=no.toString()
        }else
        {
            Toast.makeText(this,"Clear text",Toast.LENGTH_SHORT).show()
        }
    }
}