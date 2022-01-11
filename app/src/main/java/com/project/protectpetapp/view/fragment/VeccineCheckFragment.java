package com.project.protectpetapp.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentVeccineCheckBinding;
import com.project.protectpetapp.model.Pet;

import java.util.ArrayList;

public class VeccineCheckFragment extends BaseFragment<FragmentVeccineCheckBinding> implements View.OnClickListener {
    ArrayList<Pet> petArrayList = new ArrayList<>();

    public VeccineCheckFragment() {
        super(R.layout.fragment_veccine_check);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //todo : 반려동물 등록 여부 체크 후 등록 시 예방접종 체크리스트 확인 가능, 미등록시 등록 화면으로 이동
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore.collection("ProtectPetApp").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Pet")
                .addSnapshotListener(((value, error) -> {
                    petArrayList.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        petArrayList.add(doc.toObject(Pet.class));
                    }
                }));
        if (petArrayList.size() == 0) {
            mBinder.fragmentVeccineCheckLayoutNoPet.setVisibility(View.VISIBLE);
            mBinder.fragmentVeccineCheckList.setVisibility(View.GONE);
        } else if (petArrayList.size() > 0) {
            mBinder.fragmentVeccineCheckLayoutNoPet.setVisibility(View.GONE);
            mBinder.fragmentVeccineCheckList.setVisibility(View.VISIBLE);
        }

        mBinder.fragmentVeccineCheckLayoutBinderPets.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
//        if (viewId == R.id.fragment_veccine_check_layout_binder_pets) {
//            mBinder.layoutTransparent.setVisibility(View.VISIBLE);
//            mBinder.fragmentVeccineCheckLayoutPetSelect.setVisibility(View.VISIBLE);
//        } else if (viewId == R.id.layout_transparent) {
//            mBinder.layoutTransparent.setVisibility(View.GONE);
//            mBinder.fragmentVeccineCheckLayoutPetSelect.setVisibility(View.GONE);
//        }

    }
}