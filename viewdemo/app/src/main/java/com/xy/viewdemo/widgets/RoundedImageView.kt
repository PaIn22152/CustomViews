package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import com.xy.viewdemo.utils.Utils
import com.xy.viewdemo.dp

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/09 - 15:43
 * Author     Payne.
 * About      类描述：
 */
private val MARGING = 50.dp

class RoundedImageView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
//        style = Paint.Style.STROKE
//        strokeWidth = 2.dp
        color = Color.parseColor("#456789")
    }
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val bitmap = Utils.getAvatar(resources, 300.dp.toInt())
    private val circulePath = Path()
    private val leftTop = 10.dp
    private val rightTop = 20.dp
    private val rightBottom = 30.dp
    private val leftBottom = 40.dp
    private val cornerPath = Path()
    
    override fun onDraw(canvas: Canvas) {
        
        //xfermode方式绘制圆形图片
//       draw1(canvas)
        
        //clip限制绘制区域的方式绘制圆形图片，圆弧边有锯齿状，且无法改变
//       draw2(canvas)
        
        //xfermode方式绘制圆角图片
        draw3(canvas)
        
        
    }
    
    private fun draw1(canvas: Canvas) {
        //xfermode方式绘制圆形图片
        val count1 = canvas.saveLayer(
            width / 2f - 150.dp,
            height / 2f - 150.dp,
            width / 2f + 150.dp,
            height / 2f + 150.dp,
            paint
        )
        canvas.drawCircle(width / 2f, height / 2f, 150.dp, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, width / 2f - 150.dp, height / 2f - 150.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(count1)
    }
    
    private fun draw2(canvas: Canvas) {
        //clip限制绘制区域的方式绘制圆形图片
        circulePath.moveTo(MARGING, MARGING + 150.dp)
        circulePath.addOval(
            MARGING,
            MARGING,
            MARGING + 300.dp,
            MARGING + 300.dp,
            Path.Direction.CW
        )
        canvas.save()
        canvas.clipPath(circulePath)
        canvas.drawBitmap(bitmap, MARGING, MARGING, paint)
        canvas.restore()
        
    }
    
    private fun draw3(canvas: Canvas) {
        //绘制圆角图片
        cornerPath.moveTo(MARGING, MARGING + leftTop)
        cornerPath.arcTo(
            MARGING,
            MARGING,
            MARGING + leftTop * 2,
            MARGING + leftTop * 2,
            180f,
            90f,
            false
        )
        cornerPath.lineTo(MARGING + bitmap.width - rightTop, MARGING)
        
        cornerPath.arcTo(
            MARGING + bitmap.width - rightTop * 2,
            MARGING,
            MARGING + bitmap.width,
            MARGING + rightTop * 2,
            270f,
            90f,
            false
        )
        cornerPath.lineTo(MARGING + bitmap.width, MARGING + bitmap.height - rightBottom)
        cornerPath.arcTo(
            MARGING + bitmap.width - rightBottom * 2,
            MARGING + bitmap.height - rightBottom * 2,
            MARGING + bitmap.width,
            MARGING + bitmap.height,
            0f,
            90f,
            false
        )
        cornerPath.lineTo(MARGING + leftBottom, MARGING + bitmap.height)
        cornerPath.arcTo(
            MARGING,
            MARGING + bitmap.height - leftBottom * 2,
            MARGING + leftBottom * 2,
            MARGING + bitmap.height,
            90f,
            90f,
            false
        )
        cornerPath.close()
        
        val count = canvas.saveLayer(
            MARGING,
            MARGING,
            MARGING + bitmap.width,
            MARGING + bitmap.height,
            paint
        )
        canvas.drawPath(cornerPath, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, MARGING, MARGING, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
    }
}