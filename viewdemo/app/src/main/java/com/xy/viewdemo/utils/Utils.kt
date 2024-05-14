package com.xy.viewdemo.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.xy.viewdemo.R

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/09 - 15:54
 * Author     Payne.
 * About      类描述：
 */
class Utils {
    companion object {
        fun getAvatar(res: Resources, width: Int): Bitmap {
            //获取options对象
            val options = BitmapFactory.Options()
            //配置中设置属性获取图片的长宽设置
            options.inJustDecodeBounds = true
            //对图片进行解码
            BitmapFactory.decodeResource(res, R.drawable.test, options)
            //取消获取图片的长宽的设置
            options.inJustDecodeBounds = false
            options.inDensity = options.outWidth   //实际宽度
            options.inTargetDensity = width   //目标宽度
            return BitmapFactory.decodeResource(res, R.drawable.test, options)
        }
        
        fun getBitmap(res: Resources, id: Int, width: Int): Bitmap {
            //获取options对象
            val options = BitmapFactory.Options()
            //配置中设置属性获取图片的长宽设置
            options.inJustDecodeBounds = true
            //对图片进行解码
            BitmapFactory.decodeResource(res, id, options)
            //取消获取图片的长宽的设置
            options.inJustDecodeBounds = false
            options.inDensity = options.outWidth   //实际宽度
            options.inTargetDensity = width   //目标宽度
            return BitmapFactory.decodeResource(res, id, options)
        }
    }
}