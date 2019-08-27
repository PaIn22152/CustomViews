package com.perdev.viewlib.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Project    CustomViews
 * Path       com.perdev.viewlib.utils
 * Date       2019/08/27 - 10:29
 * Author     Payne.
 * About      类描述：
 */
public class L {

    private static final String TAG     = "vl_def_tag";
    private static final int    LOG_MAX = 4000;

    private static boolean LOG_SHOW  = true;
    private static long    startTime = 0;
    private static long    lastTime  = 0;

    public static void time() {
        long curTime = System.currentTimeMillis();
        L.d(" time = " + (curTime - lastTime));
        lastTime = curTime;
    }

    public static void time(String tag) {
        long curTime = System.currentTimeMillis();
        L.d(tag, " time = " + (curTime - lastTime));
        lastTime = curTime;
    }

    public static void thread() {
        d(" thread = " + Thread.currentThread().toString());
    }

    public static void thread(String tag) {
        d(tag, " thread = " + Thread.currentThread().toString());
        d(" thread = " + Thread.currentThread().getName());
    }

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void end() {
        long time = System.currentTimeMillis() - startTime;
        d(" used time = " + time + "  ms.");
    }

    public static void end(String tag) {
        long time = System.currentTimeMillis() - startTime;
        d(tag, " used time = " + time + "  ms.");
    }

    public static void show(boolean show) {
        LOG_SHOW = show;
    }


    public static void d(String tag, String s) {
        if (!LOG_SHOW) {
            return;
        }

        if (s.length() > LOG_MAX) {
            Log.d(tag, s.substring(0, LOG_MAX));
            d(tag, s.substring(LOG_MAX));
        } else {
            Log.d(tag, s);
        }

    }

    public static void d(Object s) {
        if (s != null) {
            d(TAG, s.toString());
        } else {
            d(TAG, "null");
        }

    }

    public static void d(String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        d(TAG, s);
    }


    public static void d(String tag, String header, Object[] os) {
        if (os == null) {
            d(header + " array == null ");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("[");
        int pos = 0;
        for (Object tem : os) {
            if (pos == os.length - 1) {
                sb.append(tem);
            } else {
                sb.append(tem).append(" , ");
            }
            pos++;
        }
        sb.append("]");
        d(tag, sb.toString());
    }

    public static void d(String header, Object[] os) {
        d(TAG, header, os);
    }

    public static <T> void d(String tag, String header, List<T> list) {
        d(tag, header, list.toArray());
    }

    public static <T> void d(String header, List<T> list) {
        d(TAG, header, list.toArray());
    }


    public static void e(Exception err) {
        e("  error = ", err);
    }

    public static void e(String header, Exception err) {
        err.printStackTrace();
        e(header + err);
    }

    public static void e(String tag, String s) {
        if (!LOG_SHOW) {
            return;
        }

        if (s.length() > LOG_MAX) {
            Log.e(tag, s.substring(0, LOG_MAX));
            e(tag, s.substring(LOG_MAX));
        } else {
            Log.e(tag, s);
        }

    }

    public static void e(Object s) {
        e(TAG, s.toString());
    }

    public static void e(String s) {
        e(TAG, s);
    }

    public static void e(String tag, String header, Object[] os) {
        if (os == null) {
            e(header + " array == null ");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("[");
        int pos = 0;
        for (Object tem : os) {
            if (pos == os.length - 1) {
                sb.append(tem);
            } else {
                sb.append(tem).append(" , ");
            }
            pos++;
        }
        sb.append("]");
        e(tag, sb.toString());
    }

    public static void e(String header, Object[] os) {
        e(TAG, header, os);
    }

    public static void e(String tag, String header, List<Object> list) {
        e(tag, header, list.toArray());
    }

    public static <T> void e(String header, List<T> list) {
        e(TAG, header, list.toArray());
    }
}
