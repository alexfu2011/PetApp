package com.project.protectpetapp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.ActivityLoginBinding;

import java.util.List;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private int count = 0; //클릭횟수

    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    FirebaseUser user;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //파이어베이스 접근 설정
        firebaseAuth = FirebaseAuth.getInstance();

        final String email = mBinder.editEmail.getText().toString();
        final String password = mBinder.editPassword.getText().toString();

        mBinder.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count < 3) {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                                //자동로그인 체크여부
                                if (mBinder.autoLogin.isChecked()) {
                                }

                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "잘못된 이메일 또는 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else if (count == 3) {//카운트 3일 때
                    final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("연속해서 틀렸습니다. \n비밀번호 찾기를 이용해주세요.\n")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    count = 0;
                }

            }
        });


        //회원가입 버튼
        mBinder.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        //비밀번호 찾기
        mBinder.btnSearchPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //비밀번호 찾기 창 띄우기
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_pw_search, null);
                final EditText sEmail = (EditText) mView.findViewById(R.id.email);
                Button mSearch = (Button) mView.findViewById(R.id.btn_confirm);
                Button mCancel = (Button) mView.findViewById(R.id.btn_cancel);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                //이메일 입력 후 비밀번호 찾기 버튼을 눌렀을 때
                mSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String email = sEmail.getText().toString();

                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        firebaseAuth.fetchSignInMethodsForEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                        //입력한 이메일 정보가 가입된 정보와 일치할 때
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = firebaseAuth.getCurrentUser(); //현재사용자 지정

                                            SignInMethodQueryResult result = task.getResult();
                                            List<String> signInMethods = result.getSignInMethods();
                                            if (signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                                builder.setTitle("비밀번호만 가져와서 넣으면 됩니당")
                                                        .setPositiveButton("로그인 하러 가기", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog1, int which) {
                                                        dialog1.cancel();
                                                        dialog.dismiss();
                                                    }
                                                });
                                                AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                            }

                                            //입력한 이메일 정보가 가입된 정보와 일치하지 않을 때
                                            else {
                                                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                                builder.setTitle("가입된 정보가 없습니다.")
                                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });
                                                AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                            }
                                        }
                                    }
                                });
                    }
                });
                //비밀번호 찾기 취소버튼 눌렀을 때
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
}