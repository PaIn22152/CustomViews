package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import android.util.AttributeSet;
import com.perdev.viewlib.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Project    CustomViews
 * Path       com.perdev.viewlib.widget
 * Date       2019/08/27 - 10:14
 * Author     Payne.
 * About      类描述：圆形分格统计表
 */
public class PieChartView extends BaseView {

    private RectF sector;

    private Paint piePaint;//圆饼
    private Paint progressPaint;//进度扇形


    private int           max = 360;
    private List<Integer> colorList;
    private List<Integer> progressList;

    private static final int     DEF_PIE_COLOR   = Color.parseColor("#999999");
    private static final int     DEF_PRO_COLOR   = Color.parseColor("#666666");
    private static final int     DEF_START_ANGLE = -90;//默认在最上方开始，-90度
    private static final boolean DEF_CLOCKWISE   = true;//顺时针


    //    不能在这里设置默认值，否则在xml里面配置的值不生效
    //    private int     startAngle = DEF_START_ANGLE;
    private int     startAngle;
    private int     pieColor;
    private boolean clockwise;

    private int proColor = DEF_PRO_COLOR;


    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        d("init  attrs = " + attrs);

        colorList = new ArrayList<>();
        progressList = new ArrayList<>();

        piePaint = getAvailablePaint(pieColor);
        progressPaint = getAvailablePaint(proColor);
    }

    @Override
    protected int[] styleable() {
        return R.styleable.PieChartView;
    }

    @Override
    protected void typed(TypedArray ta) {
        pieColor = ta.getColor(R.styleable.PieChartView_pie_color, DEF_PIE_COLOR);
        clockwise = ta.getBoolean(R.styleable.PieChartView_clockwise, DEF_CLOCKWISE);
        startAngle = ta.getInteger(R.styleable.PieChartView_start_angle, DEF_START_ANGLE);
    }

    @Override
    protected void onViewFinished() {

    }

    private Paint getAvailablePaint(int color) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        paint.setAntiAlias(true);
        paint.setAlpha(1);
        paint.setColor(color);
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

        d("onDraw   startAngle = " + startAngle);
        canvas.drawCircle(minWH / 2f, minWH / 2f, minWH / 2f, piePaint);

        int pos = startAngle;
        for (int i = 0; i < colorList.size(); i++) {
            if (i < progressList.size()) {
                progressPaint.setColor(colorList.get(i));
                int p = progressList.get(i) * 360 / max;
                p = clockwise ? p : -p;
                canvas.drawArc(sector, pos, p, true, progressPaint);
                pos += p;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        sector = new RectF(0, 0, minWH, minWH);
//        d("onMeasure  startAngle = " + startAngle);
    }


    public void addProgress(int progress, @ColorInt int color) {
        colorList.add(color);
        progressList.add(progress);
        postInvalidate();
//        invalidate();
    }

    public void setMax(int max) {
        this.max = max;
    }
}
