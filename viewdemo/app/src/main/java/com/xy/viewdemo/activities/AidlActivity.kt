package com.xy.viewdemo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xy.viewdemo.R
import com.xy.viewdemo.services.AIDLClient1
import com.xy.viewdemo.services.AIDLServer

class AidlActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)
        
       
        
        findViewById<View>(R.id.btn_c1).setOnClickListener {
            startActivity(Intent(this@AidlActivity, Aidl_1_Activity::class.java))
        }
        findViewById<View>(R.id.btn_c2).setOnClickListener {
            startActivity(Intent(this@AidlActivity, Aidl_2_Activity::class.java))
        }
        findViewById<View>(R.id.btn_callback_c1).setOnClickListener {
            AIDLServer.server2Client()
        }
    }
}