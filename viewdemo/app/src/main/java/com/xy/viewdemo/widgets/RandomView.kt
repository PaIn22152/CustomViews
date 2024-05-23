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
import androidx.appcompat.widget.AppCompatTextView
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
    "北京市",
    "深圳市",
    "上海市",
    "哈尔滨市",
    "呼和浩特市",
    "早市",
    "黑市",
)

private val SIZES = arrayOf(12, 20, 25)
private val X_PADDING = 16.dp.toInt()
private val Y_PADDING = 8.dp.toInt()

class RandomView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
    }
    private val random=Random()
    private val name by lazy { NAMES[random.nextInt(NAMES.size)] }
    private val textSize by lazy { SIZES[random.nextInt(SIZES.size)] }
    private val color by lazy { COLORS[random.nextInt(COLORS.size)] }
    
    init {
        text = name
        setTextSize(textSize.toFloat())
        setTextColor(Color.WHITE)
        paint.color = color
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }
    
    
    override fun onDraw(canvas: Canvas) {
        
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 4.dp, 4.dp, paint)
        super.onDraw(canvas)
    }
}