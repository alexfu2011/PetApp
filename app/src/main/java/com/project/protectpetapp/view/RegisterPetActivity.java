package com.project.protectpetapp.view;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityRegisterPetBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterPetActivity extends BaseActivity<ActivityRegisterPetBinding> implements View.OnClickListener {

    private final SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    DatePickerDialog datePickerDialog;
    private int mYear = 0, mMonth = 0, mDay = 0;


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
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    int age = (calendar.get(Calendar.YEAR)) - (year+1);
                    mBinder.tvPetBirth.setText(year+"-"+(month+1)+"-"+dayOfMonth+"("+age+"살)");
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}