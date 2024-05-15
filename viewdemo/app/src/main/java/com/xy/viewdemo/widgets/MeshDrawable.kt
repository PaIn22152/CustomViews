package com.xy.viewdemo.widgets

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import com.xy.viewdemo.dp

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/15 - 10:28
 * Author     Payne.
 * About      类描述：网格drawable
 */
class MeshDrawable : Drawable() {
    private val WIDTH = 50.dp
    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#882222")
        style = Paint.Style.STROKE
        strokeWidth = 2.dp
    }
    
    override fun draw(canvas: Canvas) {
        //竖线
        var x = bounds.left.toFloat()
        while (x <= bounds.right) {
            canvas.drawLine(x, bounds.top.toFloat(), x, bounds.bottom.toFloat(), paint)
            x += WIDTH
        }
        
        //横线
        var y = bounds.top.toFloat()
        while (y < bounds.bottom) {
            canvas.drawLine(bounds.left.toFloat(), y, bounds.right.toFloat(), y, paint)
            y += WIDTH
        }
    }
    
    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }
    
    override fun getAlpha(): Int {
        return paint.alpha
    }
    
    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
    
    override fun getColorFilter(): ColorFilter? {
        return paint.colorFilter
    }
    
    override fun getOpacity(): Int {
        return when (paint.alpha) {
            0 -> PixelFormat.TRANSPARENT
            0xff -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSLUCENT
        }
    }
}