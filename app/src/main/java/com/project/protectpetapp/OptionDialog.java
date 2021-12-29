package com.project.protectpetapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

//https://hello-bryan.tistory.com/132
//https://greedy0110.tistory.com/70

public class OptionDialog extends Dialog {
    private Context context;
    private DialogClickListener dialogClickListener;
    private String mTitle, mMessage;
    private TextView title, message, negative, positive;

//    public OptionDialog(@NonNull Context context, DialogClickListener dialogClickListener, String title, String message) {
//        super(context);
//        this.context = context;
//        this.dialogClickListener = dialogClickListener;
//        this.mTitle = title;
//        this.mMessage = message;
//    }

    public OptionDialog(@NonNull Context context, String title, String message) {
        super(context);
        this.context = context;
//        this.dialogClickListener = dialogClickListener;
        this.mTitle = title;
        this.mMessage = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        title = findViewById(R.id.custom_dialog_title);
        message = findViewById(R.id.custom_dialog_content_text);
        negative = findViewById(R.id.custom_dialog_no);
        positive = findViewById(R.id.custom_dialog_yes);

        title.setText(this.mTitle);
        message.setText(this.mMessage);

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

//
//
//public class CustomDialog extends Dialog {
//    private Button mPositiveButton;
//    private Button mNegativeButton;
//    private View.OnClickListener mPositiveListener;
//    private View.OnClickListener mNegativeListener;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //다이얼로그 밖의 화면은 흐리게 만들어줌
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        layoutParams.dimAmount = 0.8f;
//        getWindow().setAttributes(layoutParams);
//        setContentView(R.layout.custom_dialog);
//        // 셋팅
//        mPositiveButton = (Button) findViewById(R.id.pbutton);
//        mNegativeButton = (Button) findViewById(R.id.nbutton); //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
//        mPositiveButton.setOnClickListener(mPositiveListener);
//        mNegativeButton.setOnClickListener(mNegativeListener);
//    } //생성자 생성
//
//    public CustomDialog(@NonNull Context context, View.OnClickListener positiveListener, View.OnClickListener negativeListener) {
//        super(context);
//        this.mPositiveListener = positiveListener;
//        this.mNegativeListener = negativeListener;
//    }
//}

