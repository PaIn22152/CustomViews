package com.xy.viewdemo

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import java.util.Objects
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/07 - 11:38
 * Author     Payne.
 * About      类描述：
 */

//dp 2 px
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp

private val map = HashMap<String, Any>()

class InvalidateAttr(val key: String, val def: Any, val view: View) : ReadWriteProperty<Any, Any> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Any {
        return map.getOrDefault(key, def)
    }
    
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Any) {
        map[key] = value
        view.invalidate()
    }
    
}