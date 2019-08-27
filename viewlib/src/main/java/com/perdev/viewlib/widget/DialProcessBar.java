package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import com.perdev.viewlib.R;
import com.perdev.viewlib.utils.PixelUtil;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/08/27 - 14:19
 * Author     Payne.
 * About      类描述：
 */
public class DialProcessBar extends BaseView {

    /**
     * 表盘内线画笔
     */
    private Paint linePaint;

    /**
     * 指针画笔
     */
    private Paint pointerPaint;


    private int max     = 100;
    private int process = 0;

    private static final int     DEF_COLOR_LIGHT  = Color.parseColor("#7bd6eb");
    private static final int     DEF_COLOR_DARK   = Color.parseColor("#333333");
    private static final int     DEF_SCALE_NUM    = 60;
    private static final boolean DEF_SHOW_POINTER = false;

    private int     colorLight;
    private int     colorDark;
    private int     scaleNum;
    private boolean showPointer;


    public DialProcessBar(Context context) {
        super(context);
    }

    public DialProcessBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialProcessBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onViewFinished() {

    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {

        colorLight = DEF_COLOR_LIGHT;
        colorDark = DEF_COLOR_DARK;
        scaleNum = DEF_SCALE_NUM;
        showPointer = DEF_SHOW_POINTER;

        super.init(context, attrs, defStyleAttr);

        d("init");

        linePaint = getAvailablePaint(colorDark, 3, Style.STROKE);
        pointerPaint = getAvailablePaint(colorLight, 3, Style.FILL);

    }

    @Override
    protected int[] styleable() {
        return R.styleable.DialProcessBar;
    }

    @Override
    protected void typed(TypedArray ta) {
        d("typed  colorLight = " + colorLight);
        d("typed  scaleNum = " + scaleNum);
        colorLight = ta.getColor(R.styleable.DialProcessBar_light_color, DEF_COLOR_LIGHT);
        colorDark = ta.getColor(R.styleable.DialProcessBar_dark_color, DEF_COLOR_DARK);
        scaleNum = ta.getInteger(R.styleable.DialProcessBar_scale_num, DEF_SCALE_NUM);
        showPointer = ta.getBoolean(R.styleable.DialProcessBar_show_pointer, DEF_SHOW_POINTER);
        d("222typed  colorLight = " + colorLight);
        d("222typed  scaleNum = " + scaleNum);
    }

    /**
     * get a paint by ur request.
     *
     * @param color
     * @param strokeWidth
     * @param style
     * @return
     */
    private Paint getAvailablePaint(int color, int strokeWidth, Paint.Style style) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        paint.setAntiAlias(true);
        paint.setAlpha(1);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);//设置图像抖动处理
        paint.setStrokeJoin(Paint.Join.ROUND);//画笔线等连接处的轮廓样式
        paint.setSubpixelText(true);

        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScaleLine(canvas, linePaint, minWH, minWH);

        if (showPointer) {
            drawTriangle(canvas, pointerPaint, minWH, minWH);
        }

    }


    /**
     * 画刻度线
     *
     * @param canvas
     * @param paint
     * @param width
     * @param height
     */
    private void drawScaleLine(Canvas canvas, Paint paint, int width, int height) {

//        d("drawScaleLine   scaleNum = " + scaleNum);

        //一共分出来多少条线，要求可以被360整除，不然会有问题
        int lineNum = scaleNum;
        for (int i = 1; i <= lineNum; i++) {

            int num = (max - process) * lineNum / max;
            if (i >= num) {
                paint.setColor(colorLight);
            }
            if (i == lineNum) {
                paint.setColor(colorLight);
            }

            int len = PixelUtil.dp2Px(7);
            if (i % 15 == 0) {
                len += PixelUtil.dp2Px(6);
            }
            canvas.save(); //save current state of canvas.
            canvas.rotate(360 - 360f / lineNum * i, width / 2f, height / 2f);
            //绘制表盘
            canvas.drawLine(width / 2f, height / 2f - (width / 2f - PixelUtil.dp2Px(20)),
                    width / 2f,
                    height / 2f - (width / 2f - PixelUtil.dp2Px(20)) + len, paint);

            paint.setColor(colorDark);
            //恢复开始位置
            canvas.restore();

        }

    }


    /**
     * 绘制三角形指针
     */
    private void drawTriangle(Canvas canvas, Paint paint, int width, int height) {

        int rotation = (max - process) * 360 / max;

        canvas.save(); //save current state of canvas.
        canvas.rotate(360 - rotation, width / 2f, height / 2f);

        //实例化路径
        Path path = new Path();
        path.moveTo(width / 2f, PixelUtil.dp2Px(14));// 此点为多边形的起点
        path.lineTo(width / 2f + PixelUtil.dp2Px(7), PixelUtil.dp2Px(2));
        path.lineTo(width / 2f - PixelUtil.dp2Px(7), PixelUtil.dp2Px(2));
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);
    }


    public void setMax(int max) {
        this.max = max;
    }


    public void setProcess(int process) {
        this.process = process;
        postInvalidate();
    }

}
