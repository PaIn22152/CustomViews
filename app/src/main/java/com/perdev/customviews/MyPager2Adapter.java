package com.perdev.customviews;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Project    CustomViews-git
 * Path       com.perdev.customviews
 * Date       2020/07/22 - 17:57
 * Author     Payne.
 * About      类描述：
 */
public class MyPager2Adapter extends FragmentStateAdapter {


    public MyPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new EmptyFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
