package com.perdev.customviews

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var p = 0
    val handler = Handler()
    var run: Runnable = object : Runnable {
        override fun run() {
            if (p <= 100) {
                dpb_am.setProcess(p++)
                handler.postDelayed(this, 50)
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pcv_am.setMax(100)
        pcv_am.addProgress(50, Color.parseColor("#991100"))
        pcv_am.addProgress(20, Color.parseColor("#003366"))


        dpb_am.setMax(100)
        handler.postDelayed(run, 50)


    }
}
