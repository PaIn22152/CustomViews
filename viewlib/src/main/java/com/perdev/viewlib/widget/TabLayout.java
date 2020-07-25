package com.perdev.viewlib.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.perdev.viewlib.R;
import com.perdev.viewlib.utils.PixelUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget
 * Date       2020/07/22 - 15:45
 * Author     Payne.
 * About      类描述：和ViewPager或者ViewPager2配套使用
 * tab不会超过屏幕宽度
 */
public class TabLayout extends BaseRelativeLayout {

    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] styleable() {
        return R.styleable.TabLayout;
    }

    @Override
    protected int rootViewRes() {
        return R.layout.view_tab_layout;
    }

    @Override
    protected void typed(TypedArray ta) {

        d("");
        tabRes = ta.getDrawable(R.styleable.TabLayout_tabRes);
        d("");
    }


    private LinearLayout                ll_vtl_container;
    private TextView                    tv_vtl_tab;
    private TextView                    tv_vtl_title;
    private LinearLayout.LayoutParams   titleLP;
    private int                         measuredWidth = 0;
    private List<TextView>              titleViews    = new ArrayList<>();
    private RelativeLayout.LayoutParams tabLP;


    private Drawable     tabRes             = null;
    private int          titleSize          = PixelUtil.sp2px(mContext, 20);
    private int          titleSelectColor   = Color.parseColor("#dd0000");
    private int          titleUnselectColor = Color.parseColor("#dddd55");
    private List<String> titleList          = new ArrayList<>();
    private int          selectedPosition   = 0;


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        ll_vtl_container = mRootView.findViewById(R.id.ll_vtl_container);
        tv_vtl_tab = mRootView.findViewById(R.id.tv_vtl_tab);
        tv_vtl_title = mRootView.findViewById(R.id.tv_vtl_title);

        if (tabRes == null) {
            tabRes = mContext.getResources().getDrawable(R.drawable.shape_tab);
        }
        tabLP = (LayoutParams) tv_vtl_tab.getLayoutParams();
        titleLP = (LinearLayout.LayoutParams) tv_vtl_title.getLayoutParams();


    }

    @Override
    protected void onViewFinished() {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            tv_vtl_tab.setBackground(tabRes);
        } else {
            tv_vtl_tab.setBackgroundDrawable(tabRes);
        }
        tv_vtl_tab.setBackgroundResource(R.drawable.shape_tab);
        measuredWidth = ll_vtl_container.getMeasuredWidth();
    }

    public void setTitle(List<String> title) {
        titleList.clear();
        titleList.addAll(title);
        post(this::updateUI);

    }

    private TextView getTextView(String content) {
        TextView view = new TextView(mContext);
        view.setText(content);
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        view.setGravity(Gravity.CENTER);
        view.setLayoutParams(titleLP);
        return view;
    }

    private void updateUI() {
        ll_vtl_container.removeAllViews();
        titleViews.clear();
        for (int i = 0; i < titleList.size(); i++) {
            TextView view = getTextView(titleList.get(i));
            view.setTextColor(i == selectedPosition ? titleSelectColor : titleUnselectColor);
            ll_vtl_container.addView(view);
            titleViews.add(view);
        }

        int tabWidth = measuredWidth / titleList.size();
        RelativeLayout.LayoutParams lp = (LayoutParams) tv_vtl_tab.getLayoutParams();
        lp.width = tabWidth;
        tv_vtl_tab.setLayoutParams(lp);

    }

    public interface OnPageChangeCallback {

        void onPageSelected(int position, TextView selectView, List<TextView> othersView);
    }


    /**
     * title数量要和viewpager的item对应
     */
    public void bindViewPager(ViewPager viewPager, OnPageChangeCallback callback) {

        post(() -> {

            viewPager.addOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset,
                        int positionOffsetPixels) {

                    pageScrolled(position, positionOffset, positionOffsetPixels);

                }

                @Override
                public void onPageSelected(int position) {
                    pageSelected(position, callback);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            for (int i = 0; i < titleViews.size(); i++) {
                int finalI = i;
                titleViews.get(i).setOnClickListener(v -> {
                    viewPager.setCurrentItem(finalI);
                });
            }
        });
    }

    public void bindViewPager2(ViewPager2 viewPager2, OnPageChangeCallback callback) {

        post(() -> {

            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset,
                        int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    pageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    pageSelected(position, callback);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });

            for (int i = 0; i < titleViews.size(); i++) {
                int finalI = i;
                titleViews.get(i).setOnClickListener(v -> {
                    viewPager2.setCurrentItem(finalI);
                });
            }
        });
    }

    public void pageSelected(int position, OnPageChangeCallback callback) {
        if (callback != null) {
            List<TextView> others = new ArrayList<>();
            TextView select = null;
            for (int i = 0; i < titleViews.size(); i++) {
                if (i == position) {
                    select = titleViews.get(i);
                } else {
                    others.add(titleViews.get(i));
                }
            }
            callback.onPageSelected(position, select, others);
        }
    }

    private void pageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
        tabLP.leftMargin = (int) (tv_vtl_tab.getMeasuredWidth() * (position
                + positionOffset));
        tv_vtl_tab.setLayoutParams(tabLP);

        ArgbEvaluator evaluator = new ArgbEvaluator();
        int left = (int) evaluator
                .evaluate(positionOffset, titleSelectColor, titleUnselectColor);
        int right = (int) evaluator
                .evaluate(positionOffset, titleUnselectColor, titleSelectColor);
        for (int i = 0; i < titleViews.size(); i++) {
            titleViews.get(i).setTextColor(titleUnselectColor);
            if (position < titleViews.size() - 1) {
                titleViews.get(position).setTextColor(left);
                titleViews.get(position + 1).setTextColor(right);
            } else {
                titleViews.get(position).setTextColor(titleSelectColor);
            }
        }
    }
}
