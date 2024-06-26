package com.xy.viewdemo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.activities
 * Date       2024/06/07 - 16:35
 * Author     Payne.
 * About      类描述：
 */
public class ThreadJavaActivity extends Activity {

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private static void d(String s) {
        Log.d(tag, s);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "ThreadJavaActivity create");

//        myThread1 = new MyThread1();
//        myThread1.start();
//        new MyThread2().start();

//        HandlerThread handlerThread = new HandlerThread("ht88888");
//        MyHandlerThread handlerThread = new MyHandlerThread("ht88888");
//        handlerThread.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    handlerThread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                d("handlerThread.join()");
//            }
//        }).start();
//
//        Handler handler = new Handler(handlerThread.getLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                d("2000  thread=" + Thread.currentThread().getName());
//            }
//        }, 2000);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                d("5000  thread=" + Thread.currentThread().getName());
//                handlerThread.getLooper().quit();
//            }
//        }, 5000);

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.currentThread().setName("t66666");
//                    d("executorService start");
////                    while (!Thread.currentThread().isInterrupted()) {
////
////                    }
//
//                    Thread.sleep(300000);
//                    d("executorService end");
//                } catch (Exception e) {
//                    d("executorService e=" + e);
//                } finally {
//                    d("executorService finally");
//                }
//
//            }
//        });
//        mainHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                executorService.shutdown();
//                d("executorService.shutdown()");
//
//                executorService.shutdownNow();
//                d("executorService.shutdownNow()");
//            }
//        }, 2000);

        thread1 = new Thread1();
        thread1.start();
        thread2 = new Thread2();
        thread2.start();
        new Thread3().start();

    }

    private static          Thread1 thread1;
    private static          Thread2 thread2;
    private static volatile boolean end = false;

    private static synchronized void test1() {
        d("test1 start");
        while (!end) {

        }
        d("test1 end");
    }

    private static synchronized void test2() {
        d("test2 start");
    }

    private static class Thread1 extends Thread {

        @Override
        public void run() {
            super.run();
            d("Thread1 start");
            test1();
            d("Thread1 end");
        }
    }

    private static class Thread2 extends Thread {

        @Override
        public void run() {
            super.run();
            d("Thread2 start");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            test2();
            d("Thread2 end");
        }
    }

    private static class Thread3 extends Thread {

        @Override
        public void run() {
            super.run();
            d("Thread3 start");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            end = true;
//            thread2.interrupt();
            d("Thread3 end");
        }

    }

    ExecutorService executorService = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());
    private void ttest(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.shutdown();
        executorService.shutdownNow();
    }

    static               MyThread1 myThread1;
    private static final Object    monitor = new Object();
    private static final String    tag     = "taglog";

    public static void sync1() {
        Log.d(tag, "sync1 start");
        synchronized (monitor) {
            Log.d(tag, "sync1 start synchronized");
            try {
                Log.d(tag, "sync1 before wait");
                monitor.wait();
                Log.d(tag, "sync1 after wait");
            } catch (InterruptedException e) {
                Log.d(tag, "sync1 e=" + e);
                e.printStackTrace();
            }
            Log.d(tag, "sync1 synchronized end");
        }
        Log.d(tag, "sync1 end");
    }

    public static void sync2() {
        Log.d(tag, "sync2 start");
        synchronized (monitor) {
            Log.d(tag, "sync2 start synchronized");

//            monitor.notifyAll();
//            Log.d(tag, "sync2  monitor.notifyAll() ");

            myThread1.interrupt();
            Log.d(tag, "sync2  myThread1.interrupt() ");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            try {
//                Thread.sleep(200000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Log.d(tag, "sync2 end");
        }
    }


    static class MyHandlerThread extends Thread {

        public MyHandlerThread(String name) {
            super(name);
        }

        private Looper mLooper;
        private Object monitor = new Object();

        public Looper getLooper() {
            if (mLooper == null) {
                synchronized (monitor) {
                    try {
                        if (mLooper == null) {
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return mLooper;
        }

        @Override
        public void run() {
            Looper.prepare();
            synchronized (monitor) {
                mLooper = Looper.myLooper();
                monitor.notifyAll();
            }
            Looper.loop();
        }
    }

    static class MyThread1 extends Thread {

        @Override
        public void run() {
            setName("MyThread11111");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sync1();
        }
    }

    static class MyThread2 extends Thread {

        @Override
        public void run() {
            setName("MyThread2222");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sync2();
        }
    }
}