package com.project.protectpetapp.view;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.protectpetapp.R;

import java.security.MessageDigest;
import java.util.ArrayList;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected final String TAG;
    T mBinder;
    int layoutId;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseStore;

    protected static final ArrayList<Activity> ActivityList = new ArrayList<>();

    public BaseActivity(int layoutId) {
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

    protected void initToolbar() {
        Object object = findViewById(R.id.toolbar);
        if (object == null) return;

        View view = findViewById(R.id.btn_back);
        if (view != null) view.setOnClickListener(v -> onBackPressed());

        View view1 = findViewById(R.id.btn_confirm);
        view1.setVisibility(View.GONE);
    }

    protected void visibleBack() {
        Object object = findViewById(R.id.toolbar);
        if (object == null) return;

        View view = findViewById(R.id.btn_back);
        view.setVisibility(View.GONE);
        View view1 = findViewById(R.id.btn_confirm);
        view1.setVisibility(View.GONE);
    }

    protected void visibleConfirm() {
        Object object = findViewById(R.id.toolbar);
        if (object == null) return;

        View view = findViewById(R.id.btn_back);
        if (view != null) view.setOnClickListener(v -> onBackPressed());

        View view1 = findViewById(R.id.btn_confirm);
    }

    protected void setToolbar(String title) {
        Object object = findViewById(R.id.toolbar);
        if (object == null) return;

        TextView textView = findViewById(R.id.toolbar_title);
        if (textView != null) textView.setText(title);
    }

    protected void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

    protected abstract void initView(Bundle savedInstanceState);
}
