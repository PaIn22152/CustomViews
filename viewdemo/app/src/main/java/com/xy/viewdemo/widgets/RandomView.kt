package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.xy.viewdemo.dp
import java.util.Random

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/17 - 09:50
 * Author     Payne.
 * About      类描述：
 */
private val COLORS = arrayOf(
    Color.parseColor("#881111"),
    Color.parseColor("#5cd6d6"),
    Color.parseColor("#ff4d4d"),
    Color.parseColor("#ecc6d9"),
    Color.parseColor("#0066cc"),
    Color.parseColor("#c68c53"),
    Color.parseColor("#85e085"),
    Color.parseColor("#ccff33")
)

private val NAMES = arrayOf(
    "通信双方",
    "连接在运",
    "导致这个TC",
    "条TCP连接网",
    "不具有真正的",
    "的消息",
    "此当",
    "不通信之后，网关会出于网"
)

class RandomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val TEXT_SIZE = 15.dp
    private val paint = Paint().apply {
        isAntiAlias = true
        textSize = TEXT_SIZE
        textAlign = Paint.Align.CENTER
    }
    private val MIN_WIDTH = 5.dp
    private val FLOATING_WIDTH = 20.dp
    private val MIN_HEIGHT = 10.dp
    private val FLOATING_HEIGHT = 50.dp
    private val textBounds = Rect()
    private val name by lazy { NAMES[Random().nextInt(NAMES.size)] }
    private val colorDrawable by lazy {
        ColorDrawable(COLORS[Random().nextInt(COLORS.size)])
    }
    private val random = Random()
    private var randomW = 0f
    private var randomH = 0f
    
    init {
        paint.getTextBounds(name, 0, name.length, textBounds)
        randomW = textBounds.right - textBounds.left +
                random.nextInt(FLOATING_WIDTH.toInt()) + MIN_WIDTH
        randomH = textBounds.bottom - textBounds.top +
                random.nextInt(FLOATING_HEIGHT.toInt()) + MIN_HEIGHT
    }
    
    
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        
        println("onMeasure  randomW=$randomW  randomH=$randomH")
        setMeasuredDimension(randomW.toInt(), randomH.toInt())
    }
    
    override fun onDraw(canvas: Canvas) {
        println("onDraw  width=$width  height=$height")
        println("onDraw  randomW=$randomW  randomH=$randomH")
        colorDrawable.setBounds(0, 0, width, height)
        colorDrawable.draw(canvas)
        canvas.drawText(
            name,
            width / 2f,
            (height / 2 + (textBounds.bottom - textBounds.top) / 2).toFloat(),
            paint
        )
        
//        colorDrawable.setBounds(0, 0, randomW.toInt(), randomH.toInt())
//        colorDrawable.draw(canvas)
//        canvas.drawText(
//            name,
//            randomW / 2f,
//            (randomH / 2 + (textBounds.bottom - textBounds.top) / 2).toFloat(),
//            paint
//        )
    }
}