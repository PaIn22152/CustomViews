package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
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
 * About      类描述：接力型多点触控
 */
class MultiplePointerView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val bitmap = Utils.getAvatar(resources, 200.dp.toInt())
    private var downX = 0f
    private var downY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var trackingPointerId = 0
    
    private fun isInImage(x: Float, y: Float): Boolean {//判断手指是否在图片内
        return x - offsetX >= 0 && x - offsetX <= bitmap.width
                && y - offsetY >= 0 && y - offsetY <= bitmap.height
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (!isInImage(event.x, event.y)) {
                    return false
                }
                trackingPointerId = 0
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
                trackingPointerId = event.getPointerId(actionIndex)
                downX = x
                downY = y
                originOffsetX = offsetX
                originOffsetY = offsetY
                "ACTION_POINTER_DOWN  actionIndex=$actionIndex  pointerId=$trackingPointerId".log()
            }
            
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                if (pointerId == trackingPointerId) {
                    val newIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointerId = event.getPointerId(newIndex)
                    
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    originOffsetX = offsetX
                    originOffsetY = offsetY
                }
            }
            
            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(trackingPointerId)
                val x = event.getX(index)
                val y = event.getY(index)
                if (!isInImage(x, y)) {
                    return false
                }
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