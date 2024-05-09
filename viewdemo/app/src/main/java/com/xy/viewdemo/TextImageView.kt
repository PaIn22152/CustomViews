package com.xy.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Rect
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/08 - 11:19
 * Author     Payne.
 * About      类描述：
 */

private val LONG_TEXT =
    "start Maecenas consectetur nunc ut diam condimentum aliquam. Quisque vitae elit metus. " +
            "Nullam dapibus tellus tellus, ut condimentum massa efficitur non. Sed quam ipsum, " +
            "mattis sed augue quis, dictum tincidunt odio. Vestibulum vel tellus eu mi convallis " +
            "auctor. Donec purus nisl, molestie a dui et, feugiat finibus mi. In laoreet viverra " +
            "est eu hendrerit. Integer efficitur euismod nisl, eu tincidunt leo consequat ac. " +
            "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus " +
            "mus. Nulla vel quam purus. Sed velit elit, pharetra nec arcu a, molestie ornare " +
            "felis. Sed et sem nec risus scelerisque blandit quis eget metus. end"

class TextImageView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 1.dp
        color = Color.parseColor("#ff8566")
    }
    private val textPaint = TextPaint().apply {
//        textSize = 50.dp
//        textAlign = Paint.Align.CENTER
        isAntiAlias = true
        textSize = 19.dp
        textAlign = Paint.Align.LEFT
    }
    private val textBounds = Rect()
    private var textContent = "abcd"
        set(value) {
            field = value
            invalidate()
        }
    private val fontMetrics = FontMetrics()
    private lateinit var staticLayout: StaticLayout
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        staticLayout =
            StaticLayout.Builder
                .obtain(LONG_TEXT, 0, LONG_TEXT.length, textPaint, width)
                .build()
    }
    
    override fun onDraw(canvas: Canvas) {

//        centerText(canvas)
//        longText(canvas)
//        longText2(canvas)
        mixTextImage(canvas)
        
    }
    
    var startIndex = 0
    val measureWidths = floatArrayOf()
    
    /**
     * 获取符合尺寸宽度的位图
     * @param width 位图的目标宽度
     */
    
    
    val bitmap = Utils.getAvatar(resources, 200.dp.toInt())
    
    private fun mixTextImage(canvas: Canvas) {
        
        canvas.drawBitmap(bitmap, width - 200.dp, 150.dp, paint)
//        canvas.drawRect(width - 200.dp, 150.dp, width.toFloat(), 350.dp, paint)
//        canvas.drawOval(width - 200.dp, 150.dp, width.toFloat(), 350.dp, paint)
        
        var count: Int
        textPaint.getFontMetrics(fontMetrics)
        var yOffset = fontMetrics.bottom - fontMetrics.top
        var w: Float
        while (startIndex < LONG_TEXT.length) {
            if (yOffset + fontMetrics.bottom < 150.dp
                || yOffset + fontMetrics.top > 150.dp + bitmap.height
            ) {
                w = width.toFloat()
            } else {
                w = width.toFloat() - 210.dp
            }
            count = textPaint.breakText(
                LONG_TEXT,
                startIndex,
                LONG_TEXT.length,
                true,
                w,
                measureWidths
            )
            canvas.drawText(
                LONG_TEXT,
                startIndex,
                startIndex + count,
                0f,
                yOffset,
                textPaint
            )
            startIndex += count
            yOffset += textPaint.fontSpacing
        }
        
    }
    
    private fun longText2(canvas: Canvas) {
        var count: Int
        textPaint.getFontMetrics(fontMetrics)
        var yOffset = fontMetrics.bottom - fontMetrics.top
        while (startIndex < LONG_TEXT.length) {
            count = textPaint.breakText(
                LONG_TEXT,
                startIndex,
                LONG_TEXT.length,
                true,
                width.toFloat(),
                measureWidths
            )
            canvas.drawText(
                LONG_TEXT,
                startIndex,
                startIndex + count,
                0f,
                yOffset,
                textPaint
            )
            startIndex += count
            yOffset += textPaint.fontSpacing
        }
    }
    
    private fun longText(canvas: Canvas) {

//        textPaint.getFontMetrics(fontMetrics)
//        canvas.drawText(LONG_TEXT, 0f, -fontMetrics.top, textPaint)
        staticLayout.draw(canvas)
    }
    
    fun changeContent(content: String) {
        textContent = content
    }
    
    private fun centerText(canvas: Canvas) {
        canvas.drawOval(50.dp, 50.dp, 250.dp, 250.dp, paint)
        canvas.drawLine(150.dp, 50.dp, 150.dp, 250.dp, paint)
        canvas.drawLine(50.dp, 150.dp, 250.dp, 150.dp, paint)
        
        textPaint.getTextBounds(textContent, 0, textContent.length, textBounds)
//        println("textBounds.bottom=${textBounds.bottom}  textBounds.top=${textBounds.top}")
//        println("textBounds.left=${textBounds.left}  textBounds.right=${textBounds.right}")
        val w = textBounds.right - textBounds.left
        val h = textBounds.bottom - textBounds.top


//        paint.color = Color.parseColor("#666666")
//        textBounds.left += (150.dp.toInt() - w / 2)
//        textBounds.right += (150.dp.toInt() - w / 2)
//        textBounds.top += (150.dp.toInt() + h / 2)
//        textBounds.bottom += (150.dp.toInt() + h / 2)
//        canvas.drawRect(textBounds, paint)
//
//        canvas.drawText(
//            textContent,
//            150.dp,
//            150.dp + h / 2,
////            150.dp - (textBounds.bottom + textBounds.top) / 2f,
//            textPaint
//        )
        
        
        textPaint.getFontMetrics(fontMetrics)
        println("fontMetrics.descent=${fontMetrics.descent}  fontMetrics.ascent=${fontMetrics.ascent}")
//        println("150.dp=${150.dp}   diff=${(fontMetrics.descent - fontMetrics.ascent) / 2f}    h / 2=${h / 2}")
        println("150.dp=${150.dp}   diff=${(fontMetrics.descent + fontMetrics.ascent) / 2f}    h / 2=${h / 2}")
        canvas.drawText(
            textContent,
            150.dp,
            150.dp + (fontMetrics.descent - fontMetrics.ascent) / 2f,
//            150.dp - (fontMetrics.descent + fontMetrics.ascent) / 2f,
            textPaint
        )
        
    }
}