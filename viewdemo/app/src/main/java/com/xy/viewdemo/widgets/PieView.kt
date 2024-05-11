package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.xy.viewdemo.dp
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
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
    var floatNum = -1
        set(v) {
            if (v != field) {
                field = v
                invalidate()
            }
        }
    private val points = arrayOf(
        Point(5, 0),//0 x正轴，0°
        Point(5, 5),//1 第一象限，45°
        Point(0, 5),//2 y正轴，90°
        Point(-5, 5),//3 第二象限，135°
        Point(-500000, 1),//4 接近180，179.99°
        Point(-5, 0),//5 x负轴，180°
        Point(-500000, -1),//6 接近-180，-179.99°
        Point(-5, -5),//7 第三象限，-135°
        Point(0, -5),//8 y负轴，-90°
        Point(5, -5),//9 第四象限，-45°
    )
    init {
        for ((i, p) in points.withIndex()) {
            val angle1 = Math.toDegrees(atan(p.y.toFloat() / p.x).toDouble())
            val angle2 = Math.toDegrees(atan2(p.y.toDouble(), p.x.toDouble()))
            val len = Math.sqrt((p.x * p.x + p.y * p.y).toDouble())
            val acos = Math.toDegrees(acos(p.x / len))
            val asin = Math.toDegrees(asin(p.y / len))
            println("i=$i  angle1=${angle1}  angle2=${angle2}")
            println("i=$i  acos=${acos}  asin=${asin}")
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


//        canvas.translate(center_x, center_y)
//        canvas.drawOval((-3).dp, (-3).dp, 3.dp, 3.dp, paint)
//        paint.color = Color.parseColor("#00ccff")
//        for (p in points) {
//            canvas.translate(p.x * 30.dp, p.y * 30.dp)
//            canvas.drawOval((-3).dp, (-3).dp, 3.dp, 3.dp, paint)
//            canvas.translate(-p.x * 30.dp, -p.y * 30.dp)
//        }
    
    
    }
}