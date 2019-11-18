package com.perdev.customviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_snow.*

class SnowActivity : AppCompatActivity() {

    var num = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snow)

//        tv_reduce.setOnClickListener {
//            num -= 10
//            sg.setSnowflakeNumber(num)
//        }
//        tv_add.setOnClickListener {
//            num += 10
//            sg.setSnowflakeNumber(num)
//        }
    }
}
