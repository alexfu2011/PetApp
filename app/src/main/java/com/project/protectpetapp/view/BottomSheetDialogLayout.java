package com.project.protectpetapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.project.protectpetapp.R;

public class BottomSheetDialogLayout extends BottomSheetDialogFragment {
    private Context context;
    private View view;
    private String message;
    private TextView bottomSheetDialogTvMessage, bottomSheetDialogBtnConfirm;

    public BottomSheetDialogLayout(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_bottom_sheet_dialog, container, false);
        bottomSheetDialogTvMessage = view.findViewById(R.id.layout_bottom_sheet_dialog_tv_message);
        bottomSheetDialogTvMessage.setText(this.message);

        bottomSheetDialogBtnConfirm = view.findViewById(R.id.layout_bottom_sheet_dialog_btn_confirm);
        bottomSheetDialogBtnConfirm.setOnClickListener(v -> {
            dismiss();
        });

        return view;
    }
}
