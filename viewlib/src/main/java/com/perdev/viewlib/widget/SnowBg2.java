package com.perdev.viewlib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.perdev.viewlib.R;
import com.perdev.viewlib.utils.L;
import com.perdev.viewlib.utils.PixelUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/11/18 - 11:30
 * Author     Payne.
 * About      类描述：
 */
public class SnowBg2 extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;
    //绘图的Canvas
    private Canvas        mCanvas;
    //子线程标志位
    private boolean       mIsDrawing;


    protected int     mWidth    = 0;
    protected int     mHeight   = 0;
    protected int     minWH     = 0;
    protected boolean mAttached = false;


    private int snowflakeNumber  = 100;//雪花数量
    private int snowflakeSizeMin = 8;//雪花大小范围
    private int snowflakeSizeMax = 16;//雪花大小范围

    private Random random;
    private Paint  paint;

    private static int[] snowflake = {
            R.mipmap.snow1,
            R.mipmap.snow2,
            R.mipmap.snow3,
            R.mipmap.snow4
    };

    private Bitmap[] bitmaps = new Bitmap[snowflake.length];

    private List<Snow> points = new ArrayList<>();


    public SnowBg2(Context context) {
        this(context, null);
    }

    public SnowBg2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnowBg2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        //开启子线程
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
//            if(f){
            f = false;
            drawSomething();
//            }

        }
        try {
            Thread.sleep(20);
        } catch (Exception e) {

        }
    }

    boolean f = true;

    private void startAnim() {

        for (int i = 0; i < points.size(); i++) {
            Snow snow = points.get(i);
            snow.y = snow.y + snow.size / 4f;

            snow.x = snow.x + snow.rightOffset;

            points.set(i, snow);
        }
//        getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                invalidate();
//            }
//        }, 5);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        minWH = Math.min(mWidth, mHeight);


    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttached = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttached = false;

    }


    private Bitmap changeBitmapSize(int p, int size) {
        //获取原始bitmap
//        int p = random.nextInt(snowflake.length);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), snowflake[p]);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //设置想要的大小
//        int size = random.nextInt(snowflakeSizeMax - snowflakeSizeMin) + snowflakeSizeMin;
        int newWidth = PixelUtil.dp2Px(size);
        int newHeight = PixelUtil.dp2Px(size);

        //计算压缩的比率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //获取新的bitmap
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.getWidth();
        bitmap.getHeight();
        return bitmap;
    }

    private void drawSnow(Canvas canvas, int x, int y, int size) {
        Rect rect = new Rect();
        rect.left = x;
        rect.top = y;
        rect.right = rect.left + size * 2;
        rect.bottom = rect.top + size * 2;
        canvas.drawBitmap(bitmaps[size % bitmaps.length], null, rect, paint);

        //                canvas.drawBitmap(changeBitmapSize(points.get(i).snowflakePos, points.get(i).size),
//                        points.get(i).point.x,
//                        points.get(i).point.y, paint);

//        paint.setColor(Color.WHITE);
//        paint.setTextSize(size * 3);
//        canvas.drawText("$", x,y, paint);

    }

    private void drawSnows(Canvas canvas) {

        L.d("");
        List<Integer> removeList = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {

            //雪花在屏幕内
            if (points.get(i).x > -100
                    && points.get(i).x < mWidth + 10
                    && points.get(i).y > -100
                    && points.get(i).y < mHeight + points.get(i).size + 10) {

                paint.setColor(Color.WHITE);
                paint.setTextSize(points.get(i).size * 3);

                drawSnow(mCanvas, (int) points.get(i).x, (int) points.get(i).y, points.get(i).size);

//            canvas.drawBitmap();

            }

            //雪花移动到屏幕下方
            if (points.get(i).y >= mHeight + PixelUtil.dp2Px(points.get(i).size + 10)) {
                points.get(i).y = 0;
                int r = random.nextInt(100);

                //雪花减少
                if (points.size() <= snowflakeNumber) {
                    Snow snow = getStartSnow();
                    points.set(i, snow);
                } else {
                    if (r < 40) {
                        removeList.add(i);
                    } else {
                        Snow snow = getStartSnow();
                        points.set(i, snow);
                    }

                }

                //雪花增加
                if (points.size() < snowflakeNumber) {
                    for (int j = 0; j < snowflakeNumber - points.size(); j++) {

                        if (r < 10) {
                            Snow randomPoint = getStartSnow();
                            points.add(randomPoint);
                        }

                    }
                }


            }
        }

        Collections.sort(removeList);
        for (int p = 0; p < removeList.size(); p++) {
            points.remove(removeList.get(p) - p);
        }


    }

    private Snow getRandomSnow() {
        int x = random.nextInt(mWidth + PixelUtil.dp2Px(100)) - PixelUtil.dp2Px(100);
        int y = random.nextInt(mHeight);
        int pos = random.nextInt(snowflake.length);
        int size = random.nextInt(snowflakeSizeMax - snowflakeSizeMin) + snowflakeSizeMin;
//        float offset = -random.nextInt(100) / 100f;
        float offset = 1f;

        return new Snow(x, y, pos, size, offset);
    }

    private Snow getStartSnow() {//获取起始点的雪花，y=0
        int x = random.nextInt(mWidth + PixelUtil.dp2Px(100)) - PixelUtil.dp2Px(100);
        int y = -PixelUtil.dp2Px(100);
        int pos = random.nextInt(snowflake.length);
        int size = random.nextInt(snowflakeSizeMax - snowflakeSizeMin) + snowflakeSizeMin;
//        float offset = -random.nextInt(100) / 100f;
        float offset = 1f;
        return new Snow(x, y, pos, size, offset);
    }

    //绘图逻辑
    private void drawSomething() {
        try {
            L.d("sb2123", "drawSomething");
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            //绘制背景
//            mCanvas.drawColor(Color.WHITE);
            //绘图

//            mCanvas.restore();
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

//            mCanvas.drawColor(Color.RED, PorterDuff.Mode.DST);
//            mCanvas.drawColor(Color.TRANSPARENT);
//            mCanvas.drawColor(Color.RED, PorterDuff.Mode.CLEAR);

//            Paint paint = new Paint();
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//            mCanvas.drawPaint(paint);
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

//            setZOrderMediaOverlay(true);
//            getHolder().setFormat(PixelFormat.TRANSLUCENT);

            if (points.size() <= 0) {
                for (int i = 0; i < snowflakeNumber; i++) {
                    Snow randomPoint = getRandomSnow();
                    points.add(randomPoint);
                }
            }
            drawSnows(mCanvas);
            startAnim();
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);

        random = new Random();
        paint = new Paint();

        for (int p = 0; p < snowflake.length; p++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), snowflake[p]);
            bitmaps[p] = bitmap;
        }

    }


    private static class Snow {

        //坐标点
        public float x;
        public float y;
        public int   snowflakePos;
        public int   size;//雪花大小
        public float rightOffset;//向右偏移量

        public Snow(float x, float y, int snowflakePos, int size, float rightOffset) {
            this.x = x;
            this.y = y;
            this.snowflakePos = snowflakePos;
            this.size = size;
            this.rightOffset = rightOffset;
        }
    }

}
