package com.xy.viewdemo.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.xy.viewdemo.R
import com.xy.viewdemo.dp
import com.xy.viewdemo.utils.Utils

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/14 - 09:52
 * Author     Payne.
 * About      类描述：camera实现三维效果
 */
class CameraView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
    }
    private val camera = Camera()
    
//        private val pageBitmap = Utils.getBitmap(resources, R.drawable.page, 300.dp.toInt())
    private val pageBitmap = Utils.getBitmap(resources, R.drawable.test, 300.dp.toInt())
    private var bitmapLeft = 0f
    private var bitmapTop = 0f
    
    var progress = 0f
        //动画进度
        set(value) {
            field = value
            invalidate()
        }
    
    init {
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
        camera.rotateX(30f)
        
        val animator = ObjectAnimator.ofFloat(this, "progress", 0f, 1f)
        animator.startDelay = 2000
        animator.duration = 3000
        animator.start()
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        bitmapLeft = (w / 2 - pageBitmap.width / 2).toFloat()
        bitmapTop = (h / 2 - pageBitmap.height / 2).toFloat()
    }
    
    override fun onDraw(canvas: Canvas) {


//        canvas.translate(width / 2f, height / 2f)
//        camera.applyToCanvas(canvas)
//        canvas.translate(-width / 2f, -height / 2f)
//        canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)

//        canvas.save()
//        canvas.clipRect(0f,0f,width.toFloat(),height/2f)
//        canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)
//        canvas.restore()
//
//        canvas.save()
//        canvas.translate(width / 2f, height / 2f)
//        camera.applyToCanvas(canvas)
//        canvas.translate(-width / 2f, -height / 2f)
//        canvas.clipRect(0f,height/2f,width.toFloat(),height.toFloat())
//        canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)
//        canvas.restore()
        
        //正着思考
        val rotate = progress * 360
        val angle = progress * 20
        canvas.withSave {
            canvas.translate(width / 2f, height / 2f)
            canvas.rotate(-rotate)
            canvas.translate(-width / 2f, -height / 2f)
            canvas.clipRect(0f, 0f, width.toFloat(), height / 2f)
            canvas.translate(width / 2f, height / 2f)
            canvas.rotate(rotate)
            canvas.translate(-width / 2f, -height / 2f)
            canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)
        }

        canvas.withSave {
            canvas.translate(width / 2f, height / 2f)
            canvas.rotate(-rotate)
            camera.rotateX(angle)
            camera.applyToCanvas(canvas)
            camera.rotateX(-angle)
            canvas.translate(-width / 2f, -height / 2f)
            canvas.clipRect(0f, height / 2f, width.toFloat(), height.toFloat())
            canvas.translate(width / 2f, height / 2f)
            canvas.rotate(rotate)
            canvas.translate(-width / 2f, -height / 2f)
            canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)
        }
        
        //反着思考
//        val rotate = progress * 360
//        val angle = progress * 50
//        canvas.withSave {
//
//            canvas.clipRect(0f, 0f, width.toFloat(), height / 2f)
//            canvas.rotate(rotate)
//            canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)
//        }
//
//        canvas.withSave {
//            canvas.translate(width / 2f, height / 2f)
//            canvas.rotate(-rotate)
//            camera.rotateX(angle)
//            camera.applyToCanvas(canvas)
//            camera.rotateX(-angle)
//            canvas.translate(-width / 2f, -height / 2f)
//            canvas.clipRect(0f, height / 2f, width.toFloat(), height.toFloat())
//            canvas.translate(width / 2f, height / 2f)
//            canvas.rotate(rotate)
//            canvas.translate(-width / 2f, -height / 2f)
//            canvas.drawBitmap(pageBitmap, bitmapLeft, bitmapTop, paint)
//        }
        
    }
    
}