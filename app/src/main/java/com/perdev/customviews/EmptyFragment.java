package com.perdev.customviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Project    CustomViews-git
 * Path       com.perdev.customviews
 * Date       2020/07/22 - 17:22
 * Author     Payne.
 * About      类描述：
 */
public class EmptyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root=LayoutInflater.from(getContext()).inflate(R.layout.fragment_empty,null);
        return root;
    }
}
