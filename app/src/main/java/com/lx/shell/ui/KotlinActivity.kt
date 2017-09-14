package com.lx.shell.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.apkfuns.logutils.LogUtils

import com.lx.rentcheck.R
//import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity(){


var name:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
       // tvKotlin.setText("啊啊啊")

        any()
    }

    fun any(a:Int = 0,b:String = ""){
        LogUtils.d("a="+a+"b="+b)
    }



}
