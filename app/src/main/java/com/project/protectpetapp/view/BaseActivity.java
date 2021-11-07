package com.project.protectpetapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.project.protectpetapp.R;

import java.util.ArrayList;

public abstract class BaseActivity <T extends ViewDataBinding> extends AppCompatActivity {

    protected final String TAG;
    T mBinder;
    int layoutId;

    protected static final ArrayList<Activity> ActivityList = new ArrayList<>();

    public BaseActivity(int layoutId){
        this.layoutId = layoutId;
        TAG = getClass().getSimpleName();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = (T) DataBindingUtil.setContentView(this, layoutId);
        ActivityList.add(this);
        initView(savedInstanceState);
    }

    protected void initToolbar(){
        Object object = findViewById(R.id.toolbar);
        if (object == null) return;

        View view = findViewById(R.id.btn_back);
        if (view != null) view.setOnClickListener(v -> onBackPressed());
    }

    protected abstract void initView(Bundle savedInstanceState);
}
