package com.xy.viewdemo.interfaces

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.interfaces
 * Date       2024/05/28 - 16:10
 * Author     Payne.
 * About      类描述：
 */
interface MyOnDoubleTapListener : GestureDetector.OnDoubleTapListener {
    override fun onSingleTapConfirmed(e: MotionEvent): Boolean = true
    
    override fun onDoubleTap(e: MotionEvent): Boolean = true
    
    override fun onDoubleTapEvent(e: MotionEvent): Boolean = true
}