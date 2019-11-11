package com.perdev.viewlib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.utils
 * Date       2019/11/11 - 16:16
 * Author     Payne.
 * About      类描述：
 * 今日头条屏幕适配方案实现
 */
public class ScreenAdaptUtil {

    // 系统的Density
    private static float sNoncompatDensity;
    // 系统的ScaledDensity
    private static float sNoncompatScaledDensity;

    private static void d(String s) {
        L.d("sa667", s);
    }

    public static void doDensity(Activity activity) {
        final Application application = activity.getApplication();
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = displayMetrics.density;
            d(" 6 sNoncompatDensity = " + sNoncompatDensity);
            sNoncompatScaledDensity = displayMetrics.scaledDensity;
            d(" 5 sNoncompatScaledDensity = " + sNoncompatScaledDensity);
            // 监听在系统设置中切换字体
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        d("4  onConfigurationChanged ");
                        sNoncompatScaledDensity = application.getResources()
                                .getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        // 此处以360dp的设计图作为例子
        d(" 7 widthPixels = " + displayMetrics.widthPixels);
//        float targetDensity = (float) displayMetrics.widthPixels / 300f;
        float targetDensity = (float) displayMetrics.widthPixels / 360f;
//        float targetDensity = (float) displayMetrics.widthPixels / 400f;
        d(" 3 targetDensity = " + targetDensity);
        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        d(" 2 targetScaledDensity = " + targetScaledDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        d(" 1 targetDensityDpi = " + targetDensityDpi);
        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
        d("");
    }

}
