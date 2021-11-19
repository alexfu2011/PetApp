package com.project.protectpetapp.view.fragment;

import android.os.Bundle;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinder.getRoot();
    }
}