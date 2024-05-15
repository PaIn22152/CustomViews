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
    private val TEXT_MARGIN = 8.dp
    private val floatTextPaint = TextPaint().apply {
        isAntiAlias = true
        color = Color.parseColor("#990000")
        textAlign = Paint.Align.LEFT
    }
    private val fontMetrics = FontMetrics()
    private var originPaddingTop = 0
    private var progress = 0f
        //表示动画进度
        set(value) {
            field = value
            invalidate()
        }
    private var floatingShown = false
    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "progress", 0f, 1f)
    }
    var floatHintAble = true
        //是否开启悬浮提示词功能
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (originPaddingTop + TEXT_MARGIN + textSize).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        originPaddingTop,
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }
    
    
    init {
        originPaddingTop = paddingTop
        floatTextPaint.textSize = textSize
        floatTextPaint.getFontMetrics(fontMetrics)
        
        for (i in 0 until attrs.attributeCount) {
            println("attrs name=${attrs.getAttributeName(i)}  value=${attrs.getAttributeValue(i)}")
        }
        
        val attrRes = intArrayOf(R.attr.showFloatText, R.attr.testName)//表示需要过滤出来的数组
        val attr = context.obtainStyledAttributes(attrs, attrRes)
        floatHintAble = attr.getBoolean(0, true)//根据数组索引获取值
        attr.recycle()
        
        if (floatHintAble) {
            setPadding(
                paddingLeft,
                (originPaddingTop + TEXT_MARGIN + textSize).toInt(),
                paddingRight,
                paddingBottom
            )
        }
    }
    
    
    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        val last = floatingShown
        floatingShown = text.isNotEmpty()
        if (!last && floatingShown) {
            animator.start()
        } else if (last && !floatingShown) {
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
        
        floatTextPaint.alpha = (progress * 0xff).toInt()
        canvas.drawText(
            hint.toString(),
            5.dp,
            (fontMetrics.descent - fontMetrics.ascent) + (1 - progress) * (fontMetrics.descent - fontMetrics.ascent),
            floatTextPaint
        )
        
    }
    
}