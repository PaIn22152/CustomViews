package com.xy.viewdemo.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xy.viewdemo.R
import com.xy.viewdemo.widgets.DashView

class DashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        
        val myview = findViewById<DashView>(R.id.myview)
        val btn_1 = findViewById<View>(R.id.btn_1)
        val btn_2 = findViewById<View>(R.id.btn_2)
        val btn_3 = findViewById<View>(R.id.btn_3)
        
        btn_1.setOnClickListener {
            myview.changeValue(0f)
        }
        btn_2.setOnClickListener {
            myview.changeValueSmooth(10f)
        }
        btn_3.setOnClickListener {
            myview.changeValue(20f)
        }
        
        
    }
}