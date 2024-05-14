package com.xy.viewdemo.widgets

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.xy.viewdemo.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/07 - 11:32
 * Author     Payne.
 * About      类描述：仪表盘
 */

private val RADIUS = 130.dp
private val LENGTH = RADIUS * 0.8f
private val OPEN_ANGLE = 120f
private val NUM = 21

class DashView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.parseColor("#456789")
        strokeWidth = 5.dp
    }
    private var centerX = 0f
    private var centerY = 0f
    private var arc: RectF? = null
    
    private var pathEffect: PathDashPathEffect? = null
    private val path1 = Path()
    private val path = Path()
    private val path3 = Path()
    private val pathMeasure = PathMeasure()
    
    var value = 0f
        set(value) {
            field = value
            invalidate()
        }
    
    init {
        path.addRect(0f, 0f, 3.dp, 12.dp, Path.Direction.CW)
        
        path3.moveTo(15.dp, 0f)
        path3.lineTo(0f, 5.dp)
        path3.lineTo(0f, (-5).dp)
        path3.close()
        
        path.fillType = Path.FillType.INVERSE_EVEN_ODD
    }
    
    fun changeValue(f: Float) {
        smoothAnimator?.cancel()
        smoothAnimator = null
        value = f
        invalidate()
    }
    
    private var smoothAnimator: ObjectAnimator? = null//平滑改变value的动画
    
    fun changeValueSmooth(f: Float, duration: Long = 500) {
        smoothAnimator = ObjectAnimator.ofFloat(this, "value", f)
        smoothAnimator?.duration = duration
        smoothAnimator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }
            
            override fun onAnimationEnd(animation: Animator?) {
                smoothAnimator = null
            }
            
            override fun onAnimationCancel(animation: Animator?) {
            }
            
            override fun onAnimationRepeat(animation: Animator?) {
            
            }
        })
        smoothAnimator?.start()
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = width / 2f
        centerY = height / 2f
        
        path1.reset()
        path1.addArc(
            centerX - RADIUS,
            centerY - RADIUS,
            centerX + RADIUS,
            centerY + RADIUS, OPEN_ANGLE / 2 + 90, 360 - OPEN_ANGLE
        )
        
        pathMeasure.setPath(path1, false)
        pathEffect =
            PathDashPathEffect(
                path,
                (pathMeasure.length - 3.dp) / (NUM - 1),
                0f,
                PathDashPathEffect.Style.ROTATE
            )
    }
    
    override fun onDraw(canvas: Canvas) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5.dp
        canvas.drawPath(path1, paint)
        
        paint.pathEffect = pathEffect
        canvas.drawArc(arc!!, OPEN_ANGLE / 2 + 90, 360 - OPEN_ANGLE, false, paint)
        paint.pathEffect = null
        
        paint.style = Paint.Style.FILL
        canvas.drawOval(
            centerX - 5.dp,
            centerY - 5.dp,
            centerX + 5.dp,
            centerY + 5.dp, paint
        )
        
        val angle = 90f + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE) * value / (NUM - 1)
        val cos = cos(Math.toRadians(angle.toDouble()))
        val sin = sin(Math.toRadians(angle.toDouble()))
        paint.strokeWidth = 3.dp
        canvas.translate(centerX, centerY)
        canvas.drawLine(
            0f,
            0f,
            (LENGTH * cos).toFloat(),
            (LENGTH * sin).toFloat(),
            paint
        )
        
        canvas.translate((LENGTH * cos).toFloat(), (LENGTH * sin).toFloat())
        canvas.rotate(angle)
        canvas.drawPath(path3, paint)
        
        
    }
}