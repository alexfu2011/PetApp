package com.project.protectpetapp.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityMainBinding;
import com.project.protectpetapp.view.fragment.HomeFragment;
import com.project.protectpetapp.view.fragment.ProfileFragment;
import com.project.protectpetapp.view.fragment.VeccineCheckFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        setToolbar("메인");

        Fragment fragment_home = new HomeFragment();
        Fragment fragment_profile = new ProfileFragment();
        Fragment fragment_veccine_check = new VeccineCheckFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, fragment_home).commit();
        mBinder.mainBottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_home).commit();
                    setToolbar("홈");
                    return true;
                case R.id.action_veccine_check:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_veccine_check).commit();
                    setToolbar("예방접종 체크");
                    return true;
                case R.id.action_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_profile).commit();
                    setToolbar("댕댕이 프로필");
                    return true;
            }
            return false;
        });

    }
}
