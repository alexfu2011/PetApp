package com.project.protectpetapp.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.protectpetapp.R;
import com.project.protectpetapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements View.OnClickListener {


    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    protected View initView(Bundle savedInstanceState) {
        fBinder.btnRegisterPet.setOnClickListener(this);
        return fBinder.getRoot();
    }

    @Override
    public void onClick(View v) {
        View view = getLayoutInflater().inflate(R.layout.item_pet_select_dialog, null);

        TextView btnSelectDog = view.findViewById(R.id.btn_select_dog);
        TextView btnSelectCat = view.findViewById(R.id.btn_select_cat);

        //수정 필요 - 다이얼로그 안뜨는 오류
        AlertDialog.Builder petSelectDialog = new AlertDialog.Builder(getActivity()).setView(view);
        AlertDialog dialog = petSelectDialog.create();
        dialog.show();

        String dog = "dog";
        String cat = "cat";

        btnSelectDog.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), RegisterPetActivity.class);
            intent.putExtra("mode", dog);
            startActivity(intent);
        });

        btnSelectCat.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), RegisterPetActivity.class);
            intent.putExtra("mode", cat);
            startActivity(intent);
        });
    }
}