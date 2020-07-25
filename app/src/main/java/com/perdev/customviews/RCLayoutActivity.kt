package com.perdev.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rclayout.*

class RCLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rclayout)

        rc_layout.setTopLeftRadius(50)
        rc_layout.setTopRightRadius(5)
        rc_layout.setBottomLeftRadius(100)
        rc_layout.setBottomRightRadius(30)
    }
}
