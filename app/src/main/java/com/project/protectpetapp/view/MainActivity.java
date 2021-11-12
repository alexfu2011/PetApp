package com.project.protectpetapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        setToolbar("메인");

        Fragment fragment_home = new HomeFragment();

        mBinder.bottomNaviBar.setSelectedItemId(R.id.action_home);
        mBinder.bottomNaviBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_home :
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_home).commit();
                    return true;
            }
            return false;
        });

    }
}
