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
    
    private var type = 1//0自己实现measure  1measureChildWithMargins
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        var lineMaxHeight = 0
        var maxWidth = 0
        var usedWidth = paddingLeft
        var usedHeight = paddingTop
        for ((index, child) in children.withIndex()) {
            if (index >= bounds.size) {
                bounds.add(Rect())
            }
            val lp = child.layoutParams
            
            var widthSpec = 0
            var heightSpec = 0
            var childWidth = 0
            var childHeight = 0
            if (0 == type) {
                widthSpec = myMeasure(widthMeasureSpec, lp.width, usedWidth)
                heightSpec = myMeasure(heightMeasureSpec, lp.height, usedHeight)
                childWidth = MeasureSpec.getSize(widthSpec)
                childHeight = MeasureSpec.getSize(heightSpec)
            } else if (1 == type) {
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    paddingLeft,
                    heightMeasureSpec,
                    usedHeight
                )
                childWidth = child.measuredWidth
                childHeight = child.measuredHeight
            }
            
            
            
            
            println("888  chileWidth=${childWidth}")
            println("888  parentWidth=${parentWidth}")
            println("888  usedWidth=${usedWidth}")
            if (childWidth > parentWidth - usedWidth - paddingLeft - paddingRight) {
                usedWidth = paddingLeft
                usedHeight += lineMaxHeight
                lineMaxHeight = 0
                
                if (1 == type) {
                    measureChildWithMargins(
                        child,
                        widthMeasureSpec,
                        paddingLeft,
                        heightMeasureSpec,
                        usedHeight
                    )
                }
            }
            
            bounds[index].set(
                usedWidth,
                usedHeight,
                usedWidth + childWidth,
                usedHeight + childHeight
            )
            usedWidth += childWidth
            lineMaxHeight = lineMaxHeight.coerceAtLeast(childHeight)
            maxWidth = maxWidth.coerceAtLeast(usedWidth)
            if (0 == type) {
                child.measure(widthSpec, heightSpec)
            }
        }
        
        val realW = maxWidth
        val realH = usedHeight + lineMaxHeight + paddingBottom
        setMeasuredDimension(realW, realH)
        
    }
    
    fun myMeasure(parentMeasureSpec: Int, childSize: Int, used: Int): Int {
        val parentMode = MeasureSpec.getMode(parentMeasureSpec)
        val parentSize = MeasureSpec.getSize(parentMeasureSpec)
        var size = 0
        var mode = 0
        
        when (childSize) {
            LayoutParams.MATCH_PARENT -> {
                when (parentMode) {
                    MeasureSpec.EXACTLY -> {
                        size = parentSize - used
                        mode = MeasureSpec.EXACTLY
                    }
                    
                    MeasureSpec.AT_MOST -> {
                        size = parentSize - used
                        mode = MeasureSpec.AT_MOST
                    }
                    
                    MeasureSpec.UNSPECIFIED -> {
                        size = 0
                        mode = MeasureSpec.UNSPECIFIED
                    }
                }
            }
            
            LayoutParams.WRAP_CONTENT -> {
                when (parentMode) {
                    MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
                        size = parentSize - used
                        mode = MeasureSpec.AT_MOST
                    }
                    
                    MeasureSpec.UNSPECIFIED -> {
                        size = 0
                        mode = MeasureSpec.UNSPECIFIED
                    }
                }
            }
            
            else -> {
                size = childSize
                mode = MeasureSpec.EXACTLY
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
    
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}