package com.project.protectpetapp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivitySignUpBinding;
import com.project.protectpetapp.model.Owner;


public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> implements View.OnClickListener {
    String text_password, text_password_ck;

    public SignUpActivity() {
        super(R.layout.activity_sign_up);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        setToolbar("회원가입");

        passwordTextChangedCheck();

        mBinder.txtSignPwState.setVisibility(View.INVISIBLE);
        mBinder.btnSignUp.setOnClickListener(this);
        mBinder.btnPhoneAuthStart.setOnClickListener(this);

    }

    private void passwordTextChangedCheck() {
        //비밀번호 체크
        mBinder.txtSignPasswordCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //todo : 비밀번호 일치/비일치 여부 갱신 느림
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_password = mBinder.txtSignPassword.getText().toString();
                text_password_ck = mBinder.txtSignPasswordCheck.getText().toString();

                if (text_password_ck.length() == 0 || text_password.length() == 0)
                    mBinder.txtSignPwState.setVisibility(View.INVISIBLE);

                if (text_password.equals(text_password_ck)) {
                    mBinder.txtSignPwState.setText("비밀번호가 일치합니다");
                    mBinder.txtSignPwState.setVisibility(View.VISIBLE);
                } else {
                    mBinder.txtSignPwState.setText("비밀번호가 일치하지 않습니다.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_sign_up) {
            signUp();
        } else if (viewId == R.id.btn_phone_auth_start) {
            phoneAuthNumber();
        }
    }

    private void signUp() {
        //입력 정보 가져오기
        String email = mBinder.txtSignEmail.getText().toString();
        String password = mBinder.txtSignPassword.getText().toString();
        String passwordCheck = mBinder.txtSignPasswordCheck.getText().toString();
        String uid = mFirebaseAuth.getCurrentUser().getUid();
        String name = mBinder.txtSignName.getText().toString();
        String phone = mBinder.txtSignPhone.getText().toString();


        //todo : 다이얼로그로 변경, 튕기는 현상 수정 필요

        if (email.equals(" ")) {
            Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

        if (phone.equals(" ")) {
            Toast.makeText(SignUpActivity.this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

        if (password.equals(" ")) {
            Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

        if (passwordCheck.equals(" ")) {
            Toast.makeText(SignUpActivity.this, "비밀번호 확인을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

        if (!mBinder.checkboxSignAgree.isChecked()) {
            Toast.makeText(SignUpActivity.this, "약관 동의가 필요합니다.", Toast.LENGTH_SHORT).show();
        }

        if (!password.equals(passwordCheck)) {
            //비밀번호 오류시
            Toast.makeText(SignUpActivity.this, "비밀번호가 틀렸습니다.\n 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }

        final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
        mDialog.setMessage("가입중입니다...");
        mDialog.show();

        //todo : 전화번호 일치 여부 확인
        //todo : 비밀번호 6자리 이상 입력 필요
        //todo : dialog 정의 필요
        //todo : 이메일 @없어서인지, 존재해서인지 구분 필요
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, task -> {
                    if (task.isSuccessful()) {
                        mDialog.dismiss();
                        Owner owner = Owner.builder()
                                .oid(uid)
                                .email(email)
                                .name(name)
                                .password(password)
                                .phone(phone)
                                .agree(mBinder.checkboxSignAgree.isChecked())
                                .build();

                        mFirebaseStore.collection("ProtectPetApp").document().collection("OwnerInfo").document(uid).set(owner);

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        mDialog.dismiss();
                        if (!email.contains("@")) {
                            mDialog.setMessage("잘못된 이메일 형식입니다.");
                            mDialog.show();
                        } else if (password.length() < 6) {
                            mDialog.setMessage("잘못된 비밀번호 형식입니다.");
                            mDialog.show();
                        } else {
                            mDialog.setMessage("이미 존재하는 이메일입니다.");
                            mDialog.show();
                        }
//                        CustomDialog.makeDialog(this, "이미 존재하는 이메일입니다.").show();
                    }

                });

    }

    private void phoneAuthNumber() {
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber(phone)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}


