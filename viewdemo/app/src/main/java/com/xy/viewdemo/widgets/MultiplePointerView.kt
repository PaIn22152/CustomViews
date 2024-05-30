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
 * About      类描述：
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
    private var pointerId = 0
    
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
                pointerId = 0
                downX = event.x
                downY = event.y
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                val temPointerId = event.getPointerId(actionIndex)
                val x = event.getX(temPointerId)
                val y = event.getY(temPointerId)
                if (!isInImage(x, y)) {
                    return false
                }
                pointerId = temPointerId
                downX = x
                downY = y
                originOffsetX = offsetX
                originOffsetY = offsetY
                "ACTION_POINTER_DOWN  actionIndex=$actionIndex  pointerId=$pointerId".log()
            }
            
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.pointerCount - 2
                pointerId = event.getPointerId(actionIndex)
            }
            
            MotionEvent.ACTION_MOVE -> {
                val x = event.getX(pointerId)
                val y = event.getY(pointerId)
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