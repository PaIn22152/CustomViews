package com.xy.viewdemo.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.ViewCompat
import com.xy.viewdemo.dp
import com.xy.viewdemo.interfaces.MyOnDoubleTapListener
import com.xy.viewdemo.interfaces.MyOnGestureListener
import com.xy.viewdemo.interfaces.MyOnScaleGestureListener
import com.xy.viewdemo.log
import com.xy.viewdemo.utils.Utils
import java.lang.Float.max
import kotlin.math.min

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/28 - 15:30
 * Author     Payne.
 * About      类描述：
 */
class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    MyOnGestureListener, MyOnDoubleTapListener, MyOnScaleGestureListener, Runnable {
    private val bitmap = Utils.getAvatar(resources, 300.dp.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var big = false
    
    private val animator by lazy {
        ObjectAnimator.ofFloat(
            this,
            "currentScale",
            smallScale,
            bigScale
        )
    }
    private val gesture = GestureDetector(context, this)
    private val scaleGesture = ScaleGestureDetector(context, this)
    private val scroller = OverScroller(context)
    
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGesture.onTouchEvent(event)
        if (!scaleGesture.isInProgress) {
            gesture.onTouchEvent(event)
        }
        return true
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        
        originOffsetX = (width - bitmap.width) / 2f
        originOffsetY = (height - bitmap.height) / 2f
        
        smallScale = min(width.toFloat() / bitmap.width, height.toFloat() / bitmap.height)
        bigScale = max(width.toFloat() / bitmap.width, height.toFloat() / bitmap.height) * 1.3f
        
        currentScale = smallScale
    }
    
    override fun onDraw(canvas: Canvas) {
        val fraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * fraction, offsetY * fraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint)
    }
    
    override fun onDoubleTap(e: MotionEvent): Boolean {//双击
        big = !big
        if (big) {
            ObjectAnimator.ofFloat(
                this,
                "currentScale",
                currentScale,
                bigScale
            ).start()
        } else {
            ObjectAnimator.ofFloat(
                this,
                "currentScale",
                currentScale,
                smallScale
            ).start()
        }
        return true
    }
    
    override fun onScroll(//拖动
        down: MotionEvent,
        current: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (big) {
            offsetX -= distanceX
            offsetY -= distanceY
            fixOffset()
            invalidate()
        }
        return true
    }
    
    private fun fixOffset() {
        if (offsetX < -(bitmap.width * bigScale - width) / 2) {
            offsetX = -(bitmap.width * bigScale - width) / 2
        }
        if (offsetX > (bitmap.width * bigScale - width) / 2) {
            offsetX = (bitmap.width * bigScale - width) / 2
        }
        if (offsetY < -(bitmap.height * bigScale - height) / 2) {
            offsetY = -(bitmap.height * bigScale - height) / 2
        }
        if (offsetY > (bitmap.height * bigScale - height) / 2) {
            offsetY = (bitmap.height * bigScale - height) / 2
        }
    }
    
    override fun onFling(//惯性滑动
        down: MotionEvent,
        current: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        scroller.fling(
            offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
            (-(bitmap.width * bigScale - width) / 2).toInt(),
            ((bitmap.width * bigScale - width) / 2).toInt(),
            (-(bitmap.height * bigScale - height) / 2).toInt(),
            ((bitmap.height * bigScale - height) / 2).toInt()
        )
        ViewCompat.postOnAnimation(this, this)
        return true
    }
    
    override fun onScale(detector: ScaleGestureDetector): Boolean {//缩放手势
        val tem = currentScale * detector.scaleFactor
        if (tem < smallScale || tem > bigScale) {
            return false
        } else {
            currentScale = tem
            return true
        }
    }
    
    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
        offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
        fixOffset()
        return true
    }
    
    override fun run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
            ViewCompat.postOnAnimation(this, this)
        }
    }
    
    
}