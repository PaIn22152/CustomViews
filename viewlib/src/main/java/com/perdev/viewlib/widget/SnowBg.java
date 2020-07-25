package com.perdev.viewlib.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.perdev.viewlib.R;
import com.perdev.viewlib.utils.L;
import com.perdev.viewlib.utils.PixelUtil;
import com.perdev.viewlib.widget.helper.PointEvaluator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/11/15 - 15:32
 * Author     Payne.
 * About      类描述：下雪背景动画
 */
public class SnowBg extends BaseView {

    //风向
    public interface WindType {

        int WIND_RANDOM = 0;
        int WIND_LEFT   = 1;
        int WIND_RIGHT  = 2;
    }

    private int windType = WindType.WIND_RANDOM;//风向，默认随机


    private int snowflakeNumber  = 100;//雪花数量
    private int snowflakeSizeMin = 8;//雪花大小范围
    private int snowflakeSizeMax = 15;//雪花大小范围

    private Random random;
    private Paint  paint;

    private static int[] snowflake = {
            R.mipmap.snow1,
            R.mipmap.snow2,
            R.mipmap.snow3,
            R.mipmap.snow4
    };

    private List<Snow> points = new ArrayList<>();

//    private void create() {
//        random = new Random();
//        paint = new Paint();
//
//
//    }


    private static class Snow {

        public Point point;//坐标点
        public int   snowflakePos;
        public int   size;//雪花大小
        public int   rightOffset;//向右偏移量

        public Snow(Point point, int snowflakePos, int size, int rightOffset) {
            this.point = point;
            this.snowflakePos = snowflakePos;
            this.size = size;
            this.rightOffset = rightOffset;
        }
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


    private boolean first = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (points.size() <= 0) {
            for (int i = 0; i < snowflakeNumber; i++) {
                Snow randomPoint = getRandomSnow();
                points.add(randomPoint);
            }
        }

//        if (points.size() < snowflakeNumber) {
//            for (int i = 0; i < snowflakeNumber - points.size(); i++) {
//                int r = random.nextInt(100);
//                if (r < 10) {
//                    Snow randomPoint = getStartSnow();
//                    points.add(randomPoint);
//                }
//
//            }
//        }

        drawSnows(canvas);
        startAnim();
    }


    private void startAnim() {

        for (int i = 0; i < points.size(); i++) {
            Snow snow = points.get(i);
            snow.point.y = snow.point.y + snow.size / 4;
            snow.point.x = snow.point.x + snow.rightOffset;
            points.set(i, snow);
        }
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        }, 5);


    }


    public void setSnowflakeNumber(int snowflakeNumber) {
        if (this.snowflakeNumber <= 0) {
            restartSnow();
        }
        this.snowflakeNumber = snowflakeNumber;
        if (snowflakeNumber <= 0) {
            stopSnow();
        }
    }

    private boolean stop = false;

    private void stopSnow() {
        stop = true;
        invalidate();
    }

    private void restartSnow() {
        stop = false;
        for (int i = 0; i < snowflakeNumber; i++) {
            Snow randomPoint = getStartSnow();
            points.add(randomPoint);
        }
        invalidate();
    }


    private void drawSnow(int x,int y,int size,Canvas canvas,Paint paint){

    }

    private void drawSnows(Canvas canvas) {

        L.d("");
        List<Integer> removeList = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
//            canvas.drawBitmap(changeBitmapSize(points.get(i).snowflakePos, points.get(i).size),
//                    points.get(i).point.x,
//                    points.get(i).point.y, paint);

            Point p = points.get(i).point;
            if (p.x > -10 && p.x < mWidth + 10 && p.y > -10 && p.y < mHeight + 10) {

                paint.setColor(Color.WHITE);
                paint.setTextSize(points.get(i).size * 3);
//            canvas.drawPoint(points.get(i).point.x,
//                    points.get(i).point.y, paint);
                canvas.drawText("$", points.get(i).point.x,
                        points.get(i).point.y, paint);

//            canvas.drawBitmap();

            }

            //雪花移动到屏幕下方
            if (p.y >= mHeight + PixelUtil.dp2Px(points.get(i).size + 10)) {
                p.y = 0;
                int r = random.nextInt(100);
                d(" drawSnows  r = " + r);

                //雪花减少
                if (points.size() <= snowflakeNumber) {
                    Snow snow = getStartSnow();
                    points.set(i, snow);
                } else {
                    if (r < 40) {
                        if (!stop) {
                            removeList.add(i);
                        }
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

                if (stop) {
                    removeList.add(i);
                }
            }
        }

        Collections.sort(removeList);
        for (int p = 0; p < removeList.size(); p++) {
            points.remove(removeList.get(p) - p);
        }


    }

    private Snow getRandomSnow() {
        int x = random.nextInt(mWidth + PixelUtil.dp2Px(20)) - PixelUtil.dp2Px(20);
        int y = random.nextInt(mHeight);
        int pos = random.nextInt(snowflake.length);
        int size = random.nextInt(snowflakeSizeMax - snowflakeSizeMin) + snowflakeSizeMin;
        int offset = random.nextInt(2);
        return new Snow(new Point(x, y), pos, size, offset);
    }

    private Snow getStartSnow() {//获取起始点的雪花，y=0
        int x = random.nextInt(mWidth + PixelUtil.dp2Px(20)) - PixelUtil.dp2Px(20);
        int y = 0;
        int pos = random.nextInt(snowflake.length);
        int size = random.nextInt(snowflakeSizeMax - snowflakeSizeMin) + snowflakeSizeMin;
        int offset = random.nextInt(2);
        return new Snow(new Point(x, y), pos, size, offset);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);

        random = new Random();
        paint = new Paint();
        L.d("");

    }

    public SnowBg(Context context) {
        super(context);
    }

    public SnowBg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnowBg(Context context, AttributeSet attrs, int defStyleAttr) {
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
}
