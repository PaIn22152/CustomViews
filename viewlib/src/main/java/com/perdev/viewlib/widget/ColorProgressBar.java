package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.perdev.viewlib.R;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/09/19 - 11:43
 * Author     Payne.
 * About      类描述：彩色进度条
 */
public class ColorProgressBar extends BaseRelativeLayout {

    public ColorProgressBar(Context context) {
        super(context);
    }

    public ColorProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final int DEF_MAX      = 100;
    private static final int DEF_PROGRESS = 0;

    private int max;
    private int progress;

    private ImageView iv_vcpb_bg;
    private ImageView iv_vcpb_progress;

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        max = DEF_MAX;
        progress = DEF_PROGRESS;
        super.init(context, attrs, defStyleAttr);

        iv_vcpb_bg = mRootView.findViewById(R.id.iv_vcpb_bg);
        iv_vcpb_progress = mRootView.findViewById(R.id.iv_vcpb_progress);
    }

    @Override
    protected int[] styleable() {
        return R.styleable.ColorProgressBar;
    }

    @Override
    protected int rootViewRes() {
        return R.layout.view_color_progress_bar;
    }

    @Override
    protected void typed(TypedArray ta) {
        max = ta.getInteger(R.styleable.ColorProgressBar_max, DEF_MAX);
        progress = ta.getInteger(R.styleable.ColorProgressBar_progress, DEF_PROGRESS);
    }

    @Override
    protected void onViewFinished() {
        updateProgressUI();
    }


    public void setMax(int max) {
        this.max = max;
    }

    public void setProgress(int p) {
        this.progress = p;
        updateProgressUI();
    }

    private void updateProgressUI() {
        if (iv_vcpb_progress != null && iv_vcpb_bg != null) {
            if (progress <= 0) {
                progress = 0;
            }
            if (progress > max) {
                progress = max;
            }
//            d(" updateProgressUI  max = " + max + "   p = " + progress);
            int maxWidth = iv_vcpb_bg.getMeasuredWidth();
//            d(" maxWidth = " + maxWidth);
            int rightMargin = (max - progress)  * maxWidth / max;
//            d(" rightMargin = " + rightMargin);
//            d(" 111 x = " + iv_vcpb_progress.getX());
            iv_vcpb_progress.setX(-rightMargin);
//            d(" 222 x = " + iv_vcpb_progress.getX());
        }
    }
}
