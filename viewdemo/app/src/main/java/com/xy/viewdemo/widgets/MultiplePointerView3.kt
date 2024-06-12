package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.xy.viewdemo.dp
import com.xy.viewdemo.log
import com.xy.viewdemo.utils.Utils

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/30 - 15:09
 * Author     Payne.
 * About      类描述：各自为战型多点触控，画板
 */
class MultiplePointerView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    
    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5.dp
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val paths = SparseArray<Path>()
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val index = event.actionIndex
                val id = event.findPointerIndex(index)
                val path = Path()
                path.moveTo(event.getX(index), event.getY(index))
                paths.put(id, path)
            }
            
            MotionEvent.ACTION_MOVE -> {
                for (index in 0 until paths.size()) {
                    "ACTION_MOVE start index=$index  paths.size=${paths.size()}  pointerCount=${event.pointerCount}".log()
                    val id = event.getPointerId(index)
                    "ACTION_MOVE index=$index  id=$id  paths.size=${paths.size()}".log()
                    paths.get(id).lineTo(event.getX(index), event.getY(index))
                }
                invalidate()
            }
            
            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                
                val id = event.findPointerIndex(event.actionIndex)
                "ACTION_POINTER_UP  event.actionIndex=${event.actionIndex}  id=$id  paths.size=${paths.size()}".log()
                paths.remove(id)
                invalidate()
            }
        }
        return true
    }
    
    override fun onDraw(canvas: Canvas) {
        for (i in 0 until paths.size()) {
            canvas.drawPath(paths.valueAt(i), paint)
        }
        
    }
    
}