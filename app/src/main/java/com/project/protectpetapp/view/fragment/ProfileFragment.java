package com.project.protectpetapp.view.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentProfileBinding;
import com.project.protectpetapp.model.Pet;
import com.project.protectpetapp.view.RegisterPetActivity;

import java.util.ArrayList;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements View.OnClickListener {
    ArrayList<Pet> petArrayList = new ArrayList<>();


    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinder.fragmentProfileBtnRegisterPet.setOnClickListener(this);
        mBinder.getRoot();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("ProtectPetApp").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Pet")
                .addSnapshotListener(((value, error) -> {
                    petArrayList.clear();

                    for (QueryDocumentSnapshot doc : value) {
                        petArrayList.add(doc.toObject(Pet.class));
                    }
                }));
        if (petArrayList.size() == 0) {
            mBinder.fragmentProfileBtnRegisterPet.setVisibility(View.VISIBLE);
        } else {
            mBinder.fragmentProfileBtnRegisterPet.setVisibility(View.GONE);
        }
    }

    public void notRegisterIsPet() {
        View view = getLayoutInflater().inflate(R.layout.item_pet_select_dialog, null);

        LinearLayout btnSelectDog = view.findViewById(R.id.item_pet_select_btn_select_dog);
        LinearLayout btnSelectCat = view.findViewById(R.id.item_pet_select_btn_select_cat);

        AlertDialog.Builder petSelectDialog = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = petSelectDialog.create();
        dialog.setView(view);
        dialog.show();

        String dog = "dog";
        String cat = "cat";

        btnSelectDog.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), RegisterPetActivity.class);
            intent.putExtra("mode", dog);
            startActivity(intent);
            dialog.dismiss();
        });

        btnSelectCat.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), RegisterPetActivity.class);
            intent.putExtra("mode", cat);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fragment_profile_btn_register_pet) {
            notRegisterIsPet();
        }
    }
}