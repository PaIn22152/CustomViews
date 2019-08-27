package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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


    private int max     = 100;
    private int process = 0;

    private static final int DEF_COLOR_LIGHT = Color.parseColor("#7bd6eb");
    private static final int DEF_COLOR_DARK  = Color.parseColor("#333333");

    private int colorLight;
    private int colorDark;


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
        super.init(context, attrs, defStyleAttr);

        colorLight = DEF_COLOR_LIGHT;
        colorDark = DEF_COLOR_DARK;
        linePaint = getAvailablePaint(colorDark, 45, 3, Paint.Style.FILL);

    }

    @Override
    protected int[] styleable() {
        return R.styleable.DialProcessBar;
    }

    @Override
    protected void typed(TypedArray ta) {
        d("typed  colorLight = "+colorLight);
        colorLight = ta.getColor(R.styleable.DialProcessBar_light, DEF_COLOR_LIGHT);
        colorDark = ta.getColor(R.styleable.DialProcessBar_dark, DEF_COLOR_DARK);
        d("222typed  colorLight = "+colorLight);
    }

    /**
     * get a paint by ur request.
     *
     * @param color
     * @param textSize
     * @param strockWidth
     * @param style
     * @return
     */
    private Paint getAvailablePaint(int color, int textSize, int strockWidth, Paint.Style style) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setAlpha(1);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strockWidth);
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

        //一共分出来多少条线，要求可以被360整除，不然会有问题
        int lineNum = 60;
        for (int i = 1; i <= lineNum; i++) {

            int num = (max - process) * lineNum / max;
            if (i >= num) {
                paint.setColor(colorLight);
            }
            if (i == lineNum) {
                paint.setColor(colorLight);
            }

            int len = PixelUtil.dp2Px(8);
//            if (i % 15 == 0) {
//                len += PixelUtil.dp2Px(5);
//            }
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


    public void setMax(int max) {
        this.max = max;
    }


    public void setProcess(int process) {
        this.process = process;
        postInvalidate();
    }

}
