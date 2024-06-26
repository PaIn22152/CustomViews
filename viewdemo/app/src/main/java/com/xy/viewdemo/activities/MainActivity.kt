package com.xy.viewdemo.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.xy.viewdemo.R

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
        findViewById<View>(R.id.btn_camera).setOnClickListener {
            startActivity(Intent(this@MainActivity, CameraActivity::class.java))
        }
        findViewById<View>(R.id.btn_mesh).setOnClickListener {
            startActivity(Intent(this@MainActivity, MeshActivity::class.java))
        }
        findViewById<View>(R.id.btn_tag).setOnClickListener {
            startActivity(Intent(this@MainActivity, TagLayoutActivity::class.java))
        }
        findViewById<View>(R.id.btn_aidl).setOnClickListener {
            startActivity(Intent(this@MainActivity, AidlActivity::class.java))
        }
        findViewById<View>(R.id.btn_scale).setOnClickListener {
            startActivity(Intent(this@MainActivity, ScaleActivity::class.java))
        }
        findViewById<View>(R.id.btn_multiple_pointer).setOnClickListener {
            startActivity(Intent(this@MainActivity, MultiplePointerActivity::class.java))
        }
        findViewById<View>(R.id.btn_thread).setOnClickListener {
            startActivity(Intent(this@MainActivity, ThreadJavaActivity::class.java))
        }
        findViewById<View>(R.id.btn_constraint).setOnClickListener {
            startActivity(Intent(this@MainActivity, ConstraintActivity::class.java))
        }
        findViewById<View>(R.id.btn_motion).setOnClickListener {
            startActivity(Intent(this@MainActivity, MotionActivity::class.java))
        }

//        startActivity(Intent(this@MainActivity, ConstraintActivity::class.java))
        
        requestPermissions()
        
        
    }
    
    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }
}