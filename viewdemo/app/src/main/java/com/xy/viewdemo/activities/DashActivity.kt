package com.xy.viewdemo.activities

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        
        val len = 200f
        val keyFrame1 = Keyframe.ofFloat(0.2f, 0.2f * len)//参数0 fraction表示时间完成度，参数1 value表示对应属性的值
        val keyFrame2 = Keyframe.ofFloat(0.4f, 0.6f * len)
        val keyFrame3 = Keyframe.ofFloat(1f, 1f * len)
        val holer1 = PropertyValuesHolder.ofKeyframe("radius", keyFrame1, keyFrame2, keyFrame3)
        val holer2 = PropertyValuesHolder.ofKeyframe("scaleX", keyFrame1, keyFrame2, keyFrame3)
        val animator = ObjectAnimator.ofPropertyValuesHolder(myview, holer1, holer2)
        animator.start()
        
        
    }
}