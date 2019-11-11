package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/10/29 - 18:17
 * Author     Payne.
 * About      类描述：
 */
public class ScrollTestView extends BaseView {

    public ScrollTestView(Context context) {
        super(context);
    }

    public ScrollTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] styleable() {
        return new int[0];
    }

    @Override
    protected void typed(TypedArray ta) {

    }

    @Override
    protected void onViewFinished() {

    }


    private int lastX;
    private int lastY;

    private int originalX = Integer.MAX_VALUE;
    private int originalY = Integer.MAX_VALUE;

    private static final long LONG_CLICK_TIME = 1000;


    private Handler handler;

    private void initHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
    }

    private boolean  longClick = false;
    private Runnable runnable  = new Runnable() {
        @Override
        public void run() {
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.width= (int) (layoutParams.width*1.1);
            layoutParams.height= (int) (layoutParams.height*1.1);
            longClick = true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.removeCallbacks(runnable);
                if (!longClick) {
                    layout(originalX, originalY, originalX + getWidth(), originalY + getHeight());
                }
                break;
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                if (originalX == Integer.MAX_VALUE) {
                    originalX = getLeft();
                }
                if (originalY == Integer.MAX_VALUE) {
                    originalY = getTop();
                }

                initHandler();
                longClick = false;
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, LONG_CLICK_TIME);
                break;
            case MotionEvent.ACTION_MOVE:
                if (longClick) {
                    int offsetX = x - lastX;
                    int offsetY = y - lastY;
                    layout(getLeft() + offsetX, getTop() + offsetY,
                            getRight() + offsetX, getBottom() + offsetY);
                }
                break;
        }

        return true;
    }
}
