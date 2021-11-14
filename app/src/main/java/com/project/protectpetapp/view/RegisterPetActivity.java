package com.project.protectpetapp.view;

import android.os.Bundle;
import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityRegisterPetBinding;

public class RegisterPetActivity extends BaseActivity<ActivityRegisterPetBinding> {


    public RegisterPetActivity() {
        super(R.layout.activity_register_pet);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        String mode = getIntent().getStringExtra("mode");
        if (mode == "dog"){
            setToolbar("강아지 등록");
        }else if(mode == "cat"){
            setToolbar("고양이 등록");
        }
    }
}