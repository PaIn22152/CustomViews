package com.xy.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/07 - 16:33
 * Author     Payne.
 * About      类描述：
 */

private val RADIUS = 150.dp

private val ANGLES = floatArrayOf(60f, 100f, 45f, 45f, 110f)
private val COLORS = arrayOf(
    Color.parseColor("#5ba585"),
    Color.parseColor("#c85662"),
    Color.parseColor("#125698"),
    Color.parseColor("#c89063"),
    Color.parseColor("#dd5611")
)

class PieView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private var center_x = width / 2f
    private var center_y = height / 2f
    private var rectf: RectF? = null
    private var floatNum = -1
        set(v) {
            if (v != field) {
                field = v
                invalidate()
            }
        }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        center_x = width / 2f
        center_y = height / 2f
        rectf = RectF(
            center_x - RADIUS,
            center_y - RADIUS,
            center_x + RADIUS,
            center_y + RADIUS
        )
        
    }
    
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            return true
        }
        val x = event.x - center_x
        val y = event.y - center_y
        var num = ANGLES.size - 1
        if (sqrt((x * x + y * y).toDouble()) <= RADIUS) {
            var angle = Math.toDegrees(atan2(y, x).toDouble())
            if (angle < 0) {
                angle += 360
            }
            var start = 0f
            for ((i, a) in ANGLES.withIndex()) {
                if (angle <= start) {
                    num = i - 1
                    break
                }
                start += a
            }
            if (event.action == MotionEvent.ACTION_UP && floatNum == num) {
                num = -1
            }
            floatNum = num
            return true
        }
        floatNum = -1
        return false
    }
    
    override fun onDraw(canvas: Canvas) {
        
        var startAngle = 0f
        for ((index, color) in COLORS.withIndex()) {
            paint.color = color
            if (floatNum == index) {
                val angle = startAngle + ANGLES[index] / 2
                val x = cos(Math.toRadians(angle.toDouble())) * 10.dp
                val y = sin(Math.toRadians(angle.toDouble())) * 10.dp
                canvas.translate(x.toFloat(), y.toFloat())
                canvas.drawArc(rectf!!, startAngle, ANGLES[index], true, paint)
                canvas.translate(-x.toFloat(), -y.toFloat())
            } else {
                canvas.drawArc(rectf!!, startAngle, ANGLES[index], true, paint)
            }
            startAngle += ANGLES[index]
        }
        
        
    }
}