package com.xy.viewdemo.widgets

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.widgets
 * Date       2024/05/17 - 10:34
 * Author     Payne.
 * About      类描述：
 */
class TagLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    
    private val bounds = mutableListOf<Rect>()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        paddingLeft
        var lineMaxHeight = 0
        var usedWidth = paddingLeft
        var usedHeight = paddingTop
        for ((index, child) in children.withIndex()) {
            if (index >= bounds.size) {
                bounds.add(Rect())
            }
            val lp = child.layoutParams
            
            val widthSpec = myMeasure(widthMeasureSpec, lp.width)
            val heightSpec = myMeasure(heightMeasureSpec, lp.height)
            println("888  MeasureSpec.getSize(widthSpec)=${MeasureSpec.getSize(widthSpec)}")
            println("888  parentWidth=${parentWidth}")
            println("888  usedWidth=${usedWidth}")
            if (MeasureSpec.getSize(widthSpec) > parentWidth - usedWidth - paddingLeft - paddingRight) {
                usedWidth = paddingLeft
                usedHeight += lineMaxHeight
                lineMaxHeight = 0
            }
            
            bounds[index].set(
                usedWidth,
                usedHeight,
                usedWidth + MeasureSpec.getSize(widthSpec),
                usedHeight + MeasureSpec.getSize(heightSpec)
            )
            usedWidth += MeasureSpec.getSize(widthSpec)
            lineMaxHeight = lineMaxHeight.coerceAtLeast(MeasureSpec.getSize(heightSpec))
            child.measure(widthSpec, heightSpec)
        }
        
        val realW = parentWidth
        val realH = usedHeight + lineMaxHeight + paddingBottom
        setMeasuredDimension(realW, realH)
        
    }
    
    fun myMeasure(parentMeasureSpec: Int, childSize: Int): Int {
        val parentMode = MeasureSpec.getMode(parentMeasureSpec)
        val parentSize = MeasureSpec.getSize(parentMeasureSpec)
        var size = 0
        var mode = 0
        
        when (parentMode) {
            MeasureSpec.EXACTLY -> {//精确值，设置多少就是多少
//                println("myMeasure  parentMode=EXACTLY")
                when (childSize) {
                    LayoutParams.MATCH_PARENT -> {
                        size = parentSize
                        mode = MeasureSpec.EXACTLY
                    }
                    
                    LayoutParams.WRAP_CONTENT -> {
                        size = parentSize
                        mode = MeasureSpec.AT_MOST
                    }
                    
                    else -> {
                        size = childSize
                        mode = MeasureSpec.EXACTLY
                    }
                }
            }
            
            MeasureSpec.AT_MOST -> {//至多，实际大小不超过建议值
//                println("myMeasure  parentMode=AT_MOST")
                when (childSize) {
                    LayoutParams.MATCH_PARENT -> {
                        size = parentSize
                        mode = MeasureSpec.AT_MOST
                    }
                    
                    LayoutParams.WRAP_CONTENT -> {
                        size = parentSize
                        mode = MeasureSpec.AT_MOST
                    }
                    
                    else -> {
                        size = childSize
                        mode = MeasureSpec.EXACTLY
                    }
                }
            }
            
            MeasureSpec.UNSPECIFIED -> {//任意大小，无限大
//                println("myMeasure  parentMode=UNSPECIFIED")
                when (childSize) {
                    LayoutParams.MATCH_PARENT -> {
                        size = 0
                        mode = MeasureSpec.UNSPECIFIED
                    }
                    
                    LayoutParams.WRAP_CONTENT -> {
                        size = 0
                        mode = MeasureSpec.UNSPECIFIED
                    }
                    
                    else -> {
                        size = childSize
                        mode = MeasureSpec.EXACTLY
                    }
                }
            }
        }
        return MeasureSpec.makeMeasureSpec(size, mode)
    }
    
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val bound = bounds[index]
            child.layout(bound.left, bound.top, bound.right, bound.bottom)
        }
    }
}