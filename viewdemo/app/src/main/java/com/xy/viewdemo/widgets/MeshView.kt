package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.xy.viewdemo.dp

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/15 - 10:35
 * Author     Payne.
 * About      类描述：
 */
class MeshView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawable = MeshDrawable()
    override fun onDraw(canvas: Canvas) {
        drawable.setBounds(50.dp.toInt(),50.dp.toInt(),width,height)
        drawable.draw(canvas)
    }
}