package com.xy.viewdemo.interfaces

import android.view.ScaleGestureDetector

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.interfaces
 * Date       2024/05/29 - 14:04
 * Author     Payne.
 * About      类描述：
 */
interface MyOnScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
    override fun onScale(detector: ScaleGestureDetector): Boolean {
        return true
    }
    
    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        return true
    }
    
    override fun onScaleEnd(detector: ScaleGestureDetector) {
    
    }
}