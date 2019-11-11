package com.perdev.viewlib.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.utils
 * Date       2019/11/11 - 16:50
 * Author     Payne.
 * About      类描述：
 * 获取手机的最小屏幕宽度
 */
public class SWUtil {

    public static int getSW(Context context) {
        Configuration config = context.getResources().getConfiguration();
        int smallestScreenWidth = config.smallestScreenWidthDp;
        return smallestScreenWidth;
    }

}
