package com.perdev.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_dial.*

class DialActivity : AppCompatActivity() {


    var p = 0
    val handler = Handler()
    var run: Runnable = object : Runnable {
        override fun run() {
            if (p <= 60) {
                dpb_am.setProcess(p++)
                handler.postDelayed(this, 1000)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dial)

        dpb_am.setMax(60)
        handler.post(run)
    }
}
