package com.perdev.customviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pie.*

class PieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie)

        pcv_ap_.setMax(100)
        pcv_ap_.addProgress(10,Color.parseColor("#339900"))
        pcv_ap_.addProgress(25, Color.parseColor("#991100"))
        pcv_ap_.addProgress(20, Color.parseColor("#003366"))
    }
}
