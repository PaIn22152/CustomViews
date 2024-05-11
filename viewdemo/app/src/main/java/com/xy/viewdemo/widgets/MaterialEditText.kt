package com.xy.viewdemo.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.xy.viewdemo.R
import com.xy.viewdemo.dp

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/11 - 14:51
 * Author     Payne.
 * About      类描述：
 */
class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private val floatTextPaint = TextPaint().apply {
        isAntiAlias = true
        color = Color.parseColor("#990000")
        textAlign = Paint.Align.LEFT
    }
    private val fontMetrics = FontMetrics()
    private var progress = 0f
        //表示动画进度
        set(value) {
            field = value
            invalidate()
        }
    private var showFloat = false
    private var floatHintAble = false
        //是否开启悬浮提示词功能
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }
    
    
    init {
        floatTextPaint.textSize = textSize
        floatTextPaint.getFontMetrics(fontMetrics)
        
        
        for (i in 0 until attrs.attributeCount) {
            println("attrs name${attrs.getAttributeName(i)}  value=${attrs.getAttributeValue(i)}")
        }
        
        val attr = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        floatHintAble = attr.getBoolean(R.styleable.MaterialEditText_showFloatText, true)
        attr.recycle()
    }
    
    private val animator = ObjectAnimator.ofFloat(this, "progress", 0f, 1f)
    
    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        val last = showFloat
        showFloat = text.isNotEmpty()
        if (!last && showFloat) {
            animator.start()
        } else if (last && !showFloat) {
            animator.reverse()
        }
    }
    
    override fun onDraw(canvas: Canvas) {
        if (floatHintAble && hint.isNotEmpty()) {
            drawFloat(canvas)
        }
        
        super.onDraw(canvas)
    }
    
    private fun drawFloat(canvas: Canvas) {
        setPadding(0, (fontMetrics.bottom - fontMetrics.top + 10.dp).toInt(), 0, 0)
        floatTextPaint.alpha = (progress * 0xff).toInt()
        canvas.drawText(
            hint.toString(),
            0f,
            (fontMetrics.descent - fontMetrics.ascent) + (1 - progress) * (fontMetrics.descent - fontMetrics.ascent),
            floatTextPaint
        )
        
    }
    
}