package com.perdev.viewlib;

import com.perdev.viewlib.utils.L;

/**
 * Project    CustomViews
 * Path       com.perdev.viewlib
 * Date       2019/08/27 - 10:00
 * Author     Payne.
 * About      类描述：
 */
public class Test {

    public static void test(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                L.thread();
            }
        }).start();
        L.thread();

    }
}
