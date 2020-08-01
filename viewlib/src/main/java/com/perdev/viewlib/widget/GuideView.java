package com.perdev.viewlib.widget;

import static android.graphics.Canvas.ALL_SAVE_FLAG;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2020/06/07 - 17:26
 * Author     Payne.
 * About      类描述：
 */
public class GuideView extends BaseView {

    public GuideView(Context context) {
        super(context);
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Point              point    = new Point(300, 300);
    private int                r        = 100;
    private Paint              paint;
    private @ColorInt
            int                bgColor  = Color.parseColor("#99999999");
    private @ColorInt
            int                white    = Color.parseColor("#ffffff");
    private PorterDuffXfermode xfermode = new PorterDuffXfermode(Mode.DST_OUT);


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(bgColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int count;
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            count = canvas.saveLayer(getLeft(), getTop(), getRight(), getBottom(), paint);
        } else {
            count = canvas
                    .saveLayer(getLeft(), getTop(), getRight(), getBottom(), paint, ALL_SAVE_FLAG);
        }

        canvas.drawColor(bgColor);
        paint.setXfermode(xfermode);
        paint.setColor(white);
        canvas.drawCircle(point.x, point.y, r, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(count);
    }

    public void drawHollow() {

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
