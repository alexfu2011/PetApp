package com.project.protectpetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.protectpetapp.databinding.ActivitySignUpBinding;

import java.util.HashMap;


public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {
    FirebaseAuth firebaseAuth;
    String text_password;

    public SignUpActivity() {
        super(R.layout.activity_sign_up);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //파이어베이스 접근 설정
        firebaseAuth = FirebaseAuth.getInstance();


        mBinder.pwState.setVisibility(View.INVISIBLE);


        //텍스트 변화
         mBinder.signupPwck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_password = mBinder.signupPwck.getText().toString();
                if (text_password.length() == 0){
                    mBinder.signup.setEnabled(false);
                    mBinder.signup.setBackgroundResource(R.drawable.btn_enabled_bg);
                    mBinder.signup.setTextColor(Color.WHITE);
                }else{
                    mBinder.signup.setEnabled(true);
                    mBinder.signup.setBackgroundResource(R.drawable.btn_abled_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        //회원가입
        mBinder.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //입력 정보 가져오기
                final String email = mBinder.signupEmail.getText().toString();
                final String password = mBinder.signupPw.getText().toString();
                String pwcheck = mBinder.signupPwck.getText().toString();

                if (password.equals(pwcheck)) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                    mDialog.setMessage("가입중입니다...");
                    mDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                            //가입 성공시
                            if (task.isSuccessful()) {
                                mDialog.dismiss();

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String email = user.getEmail();
                                String uid = user.getUid();
                                String name = mBinder.signupName.getText().toString().trim();
                                String pw = mBinder.signupPw.getText().toString().trim();


                                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                HashMap<Object,String> hashMap = new HashMap<>();

                                hashMap.put("uid",uid);
                                hashMap.put("email",email);
                                hashMap.put("name",name);
                                hashMap.put("password", pw);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users").child(uid).child("Userinfo");
                                reference.setValue(hashMap);


                                //가입이 이루어져을시 가입 화면을 빠져나감.
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                return;  //해당 메소드 진행을 멈추고 빠져나감.

                            }

                        }
                    });

                    //비밀번호 오류시
                }else{
                    Toast.makeText(SignUpActivity.this, "비밀번호가 틀렸습니다.\n 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;


                }
            }
        });




        //뒤로가기 눌렀을 때 이벤트
        mBinder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


