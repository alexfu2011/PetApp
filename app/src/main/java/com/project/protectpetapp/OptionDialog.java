package com.project.protectpetapp;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

//https://hello-bryan.tistory.com/132
//https://greedy0110.tistory.com/70
public class OptionDialog extends android.app.Dialog {
    private Context context;
    private DialogClickListener dialogClickListener;
    private TextView title, message, negative, positive;

    public OptionDialog(@NonNull Context context, DialogClickListener dialogClickListener) {
        super(context);
        this.context = context;
        this.dialogClickListener = dialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        title = findViewById(R.id.custom_dialog_title);
        message = findViewById(R.id.custom_dialog_content_text);
        negative = findViewById(R.id.custom_dialog_no);
        positive = findViewById(R.id.custom_dialog_yes);

        positive.setOnClickListener(v -> {
            this.dialogClickListener.onPositiveClick();
            dismiss();
        });

        negative.setOnClickListener(v -> {
            this.dialogClickListener.onNegativeClick();
            dismiss();
        });
    }
}
