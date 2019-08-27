package com.perdev.customviews

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        pcv_am.setMax(100)
        pcv_am.addProgress(50, Color.parseColor("#991100"))
        pcv_am.addProgress(20, Color.parseColor("#003366"))
        
        
    }
}
