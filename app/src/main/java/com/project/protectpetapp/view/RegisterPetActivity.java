package com.project.protectpetapp.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityRegisterPetBinding;
import com.project.protectpetapp.model.Pet;
import com.project.protectpetapp.view.dialog.BottomSheetDialogLayout;
import com.project.protectpetapp.view.dialog.MyBottomSheetDialog;

import java.util.Calendar;

public class RegisterPetActivity extends BaseActivity<ActivityRegisterPetBinding> implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private DatePicker datePicker;
    String mode;
    String radioGender = "여아";

    public RegisterPetActivity() {
        super(R.layout.activity_register_pet);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        mode = getIntent().getStringExtra("mode");
        assert mode != null;
        if (mode.equals("dog")) {
            setToolbar("강아지 등록");
        } else if (mode.equals("cat")) {
            setToolbar("고양이 등록");
        }

        mBinder.registerPetTvPetBirth.setOnClickListener(this);
        mBinder.registerPetTvPetBreeds.setOnClickListener(this);
        mBinder.registerPetBtnConfirm.setOnClickListener(this);

        mBinder.registerPetRbGender.setOnCheckedChangeListener(this);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.register_pet_tv_pet_birth) {

            View view = getLayoutInflater().inflate(R.layout.item_datepiker_spinner, null);
            datePicker = view.findViewById(R.id.date_picker);
            TextView btnSetting = view.findViewById(R.id.btn_setting);
            TextView btnCancel = view.findViewById(R.id.btn_cancel);

            AlertDialog.Builder petSelectDialog = new AlertDialog.Builder(this);
            AlertDialog dialog = petSelectDialog.create();
            dialog.setView(view);
            dialog.show();

            btnSetting.setOnClickListener(v1 -> {
                showDate();
                dialog.dismiss();
            });

            btnCancel.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
        } else if (viewId == R.id.register_pet_tv_pet_breeds) {
            MyBottomSheetDialog myBottomSheetDialog = new MyBottomSheetDialog(this, mode);
            myBottomSheetDialog.show(getSupportFragmentManager(), "myBottomSheetDialog");
            myBottomSheetDialog.setDialogListener(breeds -> mBinder.registerPetTvPetBreeds.setText(breeds));
        } else if (viewId == R.id.register_pet_btn_confirm) {
            String petId = mBinder.registerPetEditPetNumber.getText().toString();
            String name = mBinder.registerPetEditPetName.getText().toString();
            String breeds = mBinder.registerPetTvPetBreeds.getText().toString();
            String birth = mBinder.registerPetTvPetBirth.getText().toString();
            String gender = radioGender;

            Pet pet = Pet.builder()
                    .petId(petId)
                    .name(name)
                    .breeds(breeds)
                    .birth(birth)
                    .gender(gender)
                    .mode(mode)
                    .build();

            mFirebaseStore.collection("ProtectPetApp").document(mFirebaseAuth.getUid()).collection("Pet").document(name).set(pet);
            BottomSheetDialogLayout bottomSheetDialogLayout = new BottomSheetDialogLayout(this, "등록이 완료되었습니다.");
            bottomSheetDialogLayout.show(getSupportFragmentManager(), "bottomSheetDialogLayout");
        }
    }

    @SuppressLint("SetTextI18n")
    private void showDate() {
        Calendar calendar = Calendar.getInstance();
        int year = this.datePicker.getYear();
        int month = this.datePicker.getMonth(); // 0 - 11
        int day = this.datePicker.getDayOfMonth();

        int age = (calendar.get(Calendar.YEAR)) - year + 1;
        mBinder.registerPetTvPetBirth.setText(year + "-" + (month + 1) + "-" + day + " (" + age + "살)");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.register_pet_rb_gender_female) {
            mBinder.registerPetRbGenderFemale.setBackgroundResource(R.drawable.toggle_border_bg_primary);
            mBinder.registerPetRbGenderMale.setBackgroundResource(0);
            radioGender = "여아";
        } else if (checkedId == R.id.register_pet_rb_gender_male) {
            mBinder.registerPetRbGenderFemale.setBackgroundResource(0);
            mBinder.registerPetRbGenderMale.setBackgroundResource(R.drawable.toggle_border_bg_primary);
            radioGender = "남아";
        }
    }
}