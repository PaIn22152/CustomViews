package com.xy.viewdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        findViewById<View>(R.id.btn_dash).setOnClickListener {
            startActivity(Intent(this@MainActivity,DashActivity::class.java))
        }
        findViewById<View>(R.id.btn_pie).setOnClickListener {
            startActivity(Intent(this@MainActivity,PieActivity::class.java))
        }
        
    }
}