package com.project.protectpetapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public abstract class BaseFragment <T extends ViewDataBinding> extends Fragment {

    T mBinder;
    int fragmentId;
    protected static final ArrayList<Fragment> FragmentList = new ArrayList<>();

    public BaseFragment(int fragmentId){
        this.fragmentId = fragmentId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinder = (T) DataBindingUtil.inflate(inflater, fragmentId, container, false);
        FragmentList.add(this);
        initView(savedInstanceState);
        return mBinder.getRoot();
    }

    protected abstract View initView(Bundle savedInstanceState);


}
