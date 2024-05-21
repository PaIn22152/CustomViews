package com.xy.viewdemo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xy.viewdemo.R
import com.xy.viewdemo.services.AIDLClient1

class Aidl_1_Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl_1)
        
        startService(Intent(this, AIDLClient1::class.java))
        
        findViewById<View>(R.id.btn_c1_2_server).setOnClickListener {
            AIDLClient1.client1.client2Server()
        }
    }
}