package com.perdev.viewlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.perdev.viewlib.utils.L;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2019/09/19 - 11:45
 * Author     Payne.
 * About      类描述：
 */
public abstract class BaseRelativeLayout extends RelativeLayout {

    protected Context mContext;

    protected int     mWidth    = 0;
    protected int     mHeight   = 0;
    protected int     minWH     = 0;
    protected boolean mAttached = false;

    protected View mRootView = null;


    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null && styleable() != null) {
            if (styleable().length == 1 && styleable()[0] == 0) {//默认没有传值

            } else {
                TypedArray ta = null;
                try {
                    ta = mContext.obtainStyledAttributes(attrs, styleable());
                    typed(ta);
//                ta.recycle();
                } catch (Exception e) {
                    L.e(e);
                } finally {
                    if (ta != null) {
                        ta.recycle();
                    }
                }
            }
        }
        if (rootViewRes() != 0) {
            mRootView = LayoutInflater.from(mContext).inflate(rootViewRes(), this, true);
        }
        post(this::onViewFinished);
    }

    protected abstract int[] styleable();

    protected abstract int rootViewRes();

    protected abstract void typed(TypedArray ta);

    /**
     * view绘制完成
     */
    protected abstract void onViewFinished();

    protected void d(String s) {
        String simpleName = this.getClass().getSimpleName() + "   ";
        L.d(simpleName + s);
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
        d("onAttachedToWindow ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttached = false;
        d("onDetachedFromWindow ");

    }
}
