package com.xy.viewdemo.interfaces

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.interfaces
 * Date       2024/05/28 - 16:08
 * Author     Payne.
 * About      类描述：
 */
interface MyOnGestureListener : GestureDetector.OnGestureListener {
    
    override fun onDown(e: MotionEvent): Boolean = true
    
    override fun onShowPress(e: MotionEvent) {}
    
    override fun onSingleTapUp(e: MotionEvent): Boolean = true
    
    override fun onScroll(
        down: MotionEvent,
        current: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean = true
    
    override fun onLongPress(e: MotionEvent) {}
    
    override fun onFling(
        down: MotionEvent,
        current: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean = true
}