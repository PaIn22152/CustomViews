package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.perdev.viewlib.R;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/09/19 - 10:26
 * Author     Payne.
 * About      类描述：角落遮盖，现支持圆角、三角形
 */
public class CornerCoverView extends BaseView {

    public CornerCoverView(Context context) {
        super(context);
    }

    public CornerCoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CornerCoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private static final int DIR_TL = 1;
    private static final int DIR_TR = 2;
    private static final int DIR_BL = 3;
    private static final int DIR_BR = 4;

    private static final String TYPE_CIC = "cir";// 遮盖类型  circular圆角
    private static final String TYPE_TRI = "tri";// 遮盖类型  triangle三角形

    private static final String DEF_TYPE  = TYPE_CIC;
    private static final int    DEF_COLOR = Color.parseColor("#555588");
    private static final int    DEF_DIR   = DIR_TL;


    String type;//遮盖类型  circular圆角    triangle三角形
    int    color;//遮盖颜色
    int    dir;//遮盖方向 1topleft   2topright   3bottomleft   4bottomright


    private Paint mPaint;

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {

        type = DEF_TYPE;
        color = DEF_COLOR;
        dir = DEF_DIR;

        super.init(context, attrs, defStyleAttr);

        mPaint = getAvailablePaint(color, 1, Style.FILL);

    }

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
    protected int[] styleable() {
        return R.styleable.CornerCoverView;
    }

    @Override
    protected void typed(TypedArray ta) {
        d(" typed start ");
        color = ta.getColor(R.styleable.CornerCoverView_corner_color, DEF_COLOR);
        dir = ta.getInteger(R.styleable.CornerCoverView_corner_dir, DEF_DIR);
        type = ta.getString(R.styleable.CornerCoverView_corner_type);
        if (TextUtils.isEmpty(type)) {
            type = DEF_TYPE;
        }
        d(" typed end   type = " + type);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TYPE_CIC.equals(type)) {
            drawCicCorner(canvas);
        } else if (TYPE_TRI.equals(type)) {
            drawTriCorner(canvas);
        }
    }

    private void drawTriCorner(Canvas canvas) {//绘制三角形遮盖
        mPaint.setColor(color);
        Path path = new Path();

        switch (dir) {
            case DIR_TL:
                path.moveTo(0, 0);
                path.lineTo(0, minWH);
                path.lineTo(minWH, 0);
                break;
            case DIR_TR:
                path.moveTo(minWH, 0);
                path.lineTo(minWH, minWH);
                path.lineTo(0, 0);
                break;
            case DIR_BL:
                path.moveTo(0, 0);
                path.lineTo(0, minWH);
                path.lineTo(minWH, minWH);
                break;
            case DIR_BR:
                path.moveTo(minWH, minWH);
                path.lineTo(0, minWH);
                path.lineTo(minWH, 0);
                break;
        }
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private void drawCicCorner(Canvas canvas) {//绘制圆角遮盖
        mPaint.setColor(color);
        RectF sector = new RectF(0, 0, minWH, minWH);
        Path path = new Path();
        switch (dir) {
            case DIR_TL:
                d("  11   drawCicCorner  radius = " + minWH);
                path.addArc(sector, 180, 90);
                path.lineTo(0, 0);
                path.lineTo(0, minWH / 2f);
                break;
            case DIR_TR:
                d("  222   drawCicCorner  radius = " + minWH);
                path.addArc(sector, 270, 90);
                path.lineTo(minWH, 0);
                path.lineTo(minWH / 2f, 0);
                break;
            case DIR_BL:
                d("  3   drawCicCorner  radius = " + minWH);
                path.addArc(sector, 90, 90);
                path.lineTo(0, minWH);
                path.lineTo(minWH / 2f, minWH);
                break;
            case DIR_BR:
                d("  4444   drawCicCorner  radius = " + minWH);
                path.addArc(sector, 0, 90);
                path.lineTo(minWH, minWH);
                path.lineTo(minWH, minWH / 2f);
                break;
        }
        canvas.drawPath(path, mPaint);
    }

    @Override
    protected void onViewFinished() {

    }
}
