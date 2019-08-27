package com.perdev.viewlib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.utils
 * Date       2019/08/27 - 14:26
 * Author     Payne.
 * About      类描述：
 */
public class PixelUtil {

    private static final float  DENSITY = Resources.getSystem().getDisplayMetrics().density;
    private static final Canvas sCanvas = new Canvas();

    public static int dp2Px(int dp) {
        return Math.round(dp * DENSITY);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
