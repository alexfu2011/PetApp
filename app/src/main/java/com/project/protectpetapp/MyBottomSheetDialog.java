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
    ArrayList<Breeds> breeds = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        breeds.add(new Breeds("요크셔테리어"));
        breeds.add(new Breeds("말티즈"));
        breeds.add(new Breeds("프렌치 불독"));
        breeds.add(new Breeds("토이푸들"));
        breeds.add(new Breeds("달마시안"));
        breeds.add(new Breeds("포메라니안"));
        breeds.add(new Breeds("비글"));
        breeds.add(new Breeds("프렌치 불독"));
        breeds.add(new Breeds("치와와"));
        breeds.add(new Breeds("퍼그"));
        breeds.add(new Breeds("닥스훈트"));
        breeds.add(new Breeds("시츄"));
        breeds.add(new Breeds("테리어견"));
        breeds.add(new Breeds("스피츠"));
        breeds.add(new Breeds("일본 스피츠"));
        breeds.add(new Breeds("독일 스피츠"));
        breeds.add(new Breeds("시바견"));
        breeds.add(new Breeds("아키타견"));
        breeds.add(new Breeds("스키퍼키"));
        breeds.add(new Breeds("잉글리시 코커 스패니얼"));
        breeds.add(new Breeds("라사압소"));
        breeds.add(new Breeds("복서"));
        breeds.add(new Breeds("미니어처 핀셔"));
        breeds.add(new Breeds("브뤼셀 그리펀"));
        breeds.add(new Breeds("레케노아"));
        breeds.add(new Breeds("테뷰런"));
        breeds.add(new Breeds("풍산개"));
        breeds.add(new Breeds("삽살개"));
        breeds.add(new Breeds("보스턴 테리어"));
        breeds.add(new Breeds("티베탄 테리어"));
        breeds.add(new Breeds("미니어처 슈나우저"));
        breeds.add(new Breeds("비숑프리제"));
        breeds.add(new Breeds("보더콜리"));
        breeds.add(new Breeds("사모예드"));
        breeds.add(new Breeds("웰시코기"));

        // todo : 드래그 시 창 닫히는거 막기
//        BottomSheetBehavior behavior = BottomSheetBehavior.from(view);
//        behavior.setHideable(false);
//        behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
//        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                    behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });

        listView = view.findViewById(R.id.bottom_sheet_breeds_list);
        ListViewAdapter adapter = new ListViewAdapter(getLayoutInflater(), breeds);
        listView.setAdapter(adapter);

        return view;
    }
}
