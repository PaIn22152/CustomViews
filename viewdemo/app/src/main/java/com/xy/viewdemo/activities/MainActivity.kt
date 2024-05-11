package com.xy.viewdemo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xy.viewdemo.R
import com.xy.viewdemo.utils.RandomUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        findViewById<View>(R.id.btn_dash).setOnClickListener {
            startActivity(Intent(this@MainActivity, DashActivity::class.java))
        }
        findViewById<View>(R.id.btn_pie).setOnClickListener {
            startActivity(Intent(this@MainActivity, PieActivity::class.java))
        }
        findViewById<View>(R.id.btn_text).setOnClickListener {
            startActivity(Intent(this@MainActivity, TextActivity::class.java))
        }
        findViewById<View>(R.id.btn_rounded).setOnClickListener {
            startActivity(Intent(this@MainActivity, RoundedImageActivity::class.java))
        }
        findViewById<View>(R.id.btn_edit_text).setOnClickListener {
            startActivity(Intent(this@MainActivity, EditTextActivity::class.java))
        }
        
    }
}