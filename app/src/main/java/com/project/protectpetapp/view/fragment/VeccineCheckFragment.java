package com.project.protectpetapp.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentVeccineCheckBinding;

public class VeccineCheckFragment extends BaseFragment<FragmentVeccineCheckBinding> implements View.OnClickListener {

    public VeccineCheckFragment() {
        super(R.layout.fragment_veccine_check);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //todo : 반려동물 등록 여부 체크 후 등록 시 예방접종 체크리스트 확인 가능, 미등록시 등록 화면으로 이동
        mBinder.fragmentVeccineCheckLayoutBinderPets.setOnClickListener(this);
        mBinder.layoutTransparent.setOnClickListener(this);
    }

    public void petRegisterCheck() {

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.fragment_veccine_check_layout_binder_pets) {
            mBinder.layoutTransparent.setVisibility(View.VISIBLE);
            mBinder.fragmentVeccineCheckLayoutPetSelect.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.layout_transparent) {
            mBinder.layoutTransparent.setVisibility(View.GONE);
            mBinder.fragmentVeccineCheckLayoutPetSelect.setVisibility(View.GONE);
        }

    }
}