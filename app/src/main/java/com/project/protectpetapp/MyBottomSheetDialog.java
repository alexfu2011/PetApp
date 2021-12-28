package com.project.protectpetapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.project.protectpetapp.adapter.ListViewAdapter;
import com.project.protectpetapp.model.Breeds;

import java.util.ArrayList;

public class MyBottomSheetDialog extends BottomSheetDialogFragment {
    private View view;
    ListView listView;
    ArrayList<Breeds> breeds = new ArrayList<Breeds>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        breeds.add(new Breeds("1", "요크셔테리어"));
        breeds.add(new Breeds("2", "말티즈"));
        breeds.add(new Breeds("3", "프렌치 불독"));
        breeds.add(new Breeds("4", "토이푸들"));
        breeds.add(new Breeds("5", "달마시안"));

        listView = view.findViewById(R.id.bottom_sheet_breeds_list);
        ListViewAdapter adapter = new ListViewAdapter(getLayoutInflater(), breeds);
        listView.setAdapter(adapter);

        return view;
    }
}
