package com.perdev.customviews

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tv_am_pie.setOnClickListener {
            startActivity(Intent(this, PieActivity::class.java))
        }
        tv_am_dial.setOnClickListener {
            startActivity(Intent(this, DialActivity::class.java))
        }
        tv_am_rcl.setOnClickListener {
            startActivity(Intent(this, RCLayoutActivity::class.java))
        }


    }
}
