package com.perdev.viewlib.jnipackage;

import android.graphics.Bitmap;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.jnipackage
 * Date       2019/09/20 - 11:22
 * Author     Payne.
 * About      类描述：图片滤镜，连接jni
 */
public class BitmapFilter {

    static {
        System.loadLibrary("BitmapLib");
    }

    public static native void blackWhite(Bitmap input, Bitmap output);//黑白滤镜

    public static native void warm(Bitmap input, Bitmap output);//黑白滤镜


    public static native void cold(Bitmap input, Bitmap output);//黑白滤镜

}
