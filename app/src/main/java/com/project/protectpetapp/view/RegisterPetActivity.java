package com.project.protectpetapp.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityRegisterPetBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RegisterPetActivity extends BaseActivity<ActivityRegisterPetBinding> implements View.OnClickListener {

    private final SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private long mDate;


    public RegisterPetActivity() {
        super(R.layout.activity_register_pet);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("dog")) {
            setToolbar("강아지 등록");
        } else if (mode.equals("cat")) {
            setToolbar("고양이 등록");
        }

        mBinder.tvPetBirth.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_pet_birth) {
        }
    }
}