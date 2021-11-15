package com.project.protectpetapp.view;

import android.os.Bundle;
import android.view.View;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }


    @Override
    protected View initView(Bundle savedInstanceState) {
        return mBinder.getRoot();
    }
}