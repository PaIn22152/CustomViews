package com.xy.viewdemo.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Path
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.xy.viewdemo.InvalidateAttr
import com.xy.viewdemo.dp

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/09 - 11:18
 * Author     Payne.
 * About      类描述：音乐软件歌词进度条效果
 */
private val max = 100f
private val SIZE = 30.dp

class GradientColorTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val textPaint = TextPaint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.LEFT
        textSize = SIZE
    }
    private val leftPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#ff3333")
        textSize = SIZE
        style = Paint.Style.STROKE
        strokeWidth = 1.dp
    }
    private val leftTextPath = Path()
    private val rightPaint = TextPaint().apply {
        isAntiAlias = true
        color = Color.parseColor("#666666")
        textSize = SIZE
    }
    private val textBound = Rect()
    private val left = Rect()
    private val right = Rect()
    private val fontMetrics = FontMetrics()
    
    //是否使用自定义委托属性有待验证
    private var text: Any by InvalidateAttr("text", "这里是歌词abcde哦哦哦", this)
    
    private var progress = 0f
        set(value) {
            field = value
            invalidate()
        }
    
    init {
        val animator = ObjectAnimator.ofFloat(this, "progress", 0f, 93f)
        animator.duration = 5000
        animator.startDelay = 2000
        animator.start()

//        leftPaint.setShadowLayer(10f,0f,0f,Color.parseColor("#ff3333"))
    }
    
    override fun onDraw(canvas: Canvas) {
        val content = text as String
        val currProgress = progress as Float
        
        textPaint.getTextBounds(content, 0, content.length, textBound)
        
        val leftWidth = (textBound.right - textBound.left) * currProgress / max
        val rightWidth = (textBound.right - textBound.left) - leftWidth

//        println("leftWidth=$leftWidth   rightWidth=$rightWidth")
        
        left.left = 0
        left.top = 0
        left.right = leftWidth.toInt()
        left.bottom = height
        
        leftPaint.getFontMetrics(fontMetrics)
        leftPaint.getTextPath(
            content,
            0,
            content.length,
            0f,
            fontMetrics.bottom - fontMetrics.top,
            leftTextPath
        )
        
        canvas.save()
        canvas.clipRect(left)
//        canvas.drawText(content, 0f, fontMetrics.bottom - fontMetrics.top, leftPaint)
        canvas.drawPath(leftTextPath, leftPaint)
        canvas.restore()
        
        right.left = leftWidth.toInt()
        right.top = 0
        right.right = textBound.right - textBound.left
        right.bottom = height
        canvas.save()
        canvas.clipRect(right)
        canvas.drawText(content, 0f, fontMetrics.bottom - fontMetrics.top, rightPaint)
        canvas.restore()
        
        
    }
}