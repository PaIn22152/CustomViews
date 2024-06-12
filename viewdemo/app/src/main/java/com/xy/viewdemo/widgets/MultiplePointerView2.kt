package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.xy.viewdemo.dp
import com.xy.viewdemo.log
import com.xy.viewdemo.utils.Utils

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/30 - 15:09
 * Author     Payne.
 * About      类描述：协作型多点触控
 */
class MultiplePointerView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val bitmap = Utils.getAvatar(resources, 200.dp.toInt())
    private var downX = 0f
    private var downY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    
    private fun isInImage(x: Float, y: Float): Boolean {//判断手指是否在图片内
//        return x - offsetX >= 0 && x - offsetX <= bitmap.width
//                && y - offsetY >= 0 && y - offsetY <= bitmap.height
        return true
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (!isInImage(event.x, event.y)) {
                    return false
                }
                downX = event.x
                downY = event.y
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                val x = event.getX(actionIndex)
                val y = event.getY(actionIndex)
                if (!isInImage(x, y)) {
                    return false
                }
                downX = (downX+x)/2
                downY = (downY+y)/2
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                val newIndex = if (actionIndex == event.pointerCount - 1) {
                    event.pointerCount - 2
                } else {
                    event.pointerCount - 1
                }
                
                downX = event.getX(newIndex)
                downY = event.getY(newIndex)
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            
            MotionEvent.ACTION_MOVE -> {
                var sumX = 0f
                var sumY = 0f
                for (index in 0 until event.pointerCount) {
                    sumX += event.getX(index)
                    sumY += event.getY(index)
                }
                val x = sumX / event.pointerCount
                val y = sumY / event.pointerCount
                
                offsetX = x - downX + originOffsetX
                offsetY = y - downY + originOffsetY
                invalidate()
            }
        }
        return true
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }
    
}