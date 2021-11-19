package com.project.protectpetapp.view.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentProfileBinding;
import com.project.protectpetapp.view.RegisterPetActivity;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements View.OnClickListener {


    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinder.btnRegisterPet.setOnClickListener(this);
        mBinder.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register_pet) {
            View view = getLayoutInflater().inflate(R.layout.item_pet_select_dialog, null);

            LinearLayout btnSelectDog = view.findViewById(R.id.btn_select_dog);
            LinearLayout btnSelectCat = view.findViewById(R.id.btn_select_cat);

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

            //dialog.dismiss();
        }
    }
}