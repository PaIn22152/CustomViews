package com.xy.viewdemo

import android.content.res.Resources
import android.util.TypedValue

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/07 - 11:38
 * Author     Payne.
 * About      类描述：
 */

//px 2 dp
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp
    