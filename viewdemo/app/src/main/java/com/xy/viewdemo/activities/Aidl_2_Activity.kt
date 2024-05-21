package com.xy.viewdemo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xy.viewdemo.R
import com.xy.viewdemo.services.AIDLClient1
import com.xy.viewdemo.services.AIDLClient2

class Aidl_2_Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl_2)
        
        startService(Intent(this, AIDLClient2::class.java))
        
        findViewById<View>(R.id.btn_c2_2_server).setOnClickListener {
            AIDLClient2.client2.client2Server()
        }
    }
}