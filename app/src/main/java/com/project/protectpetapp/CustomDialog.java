package com.project.protectpetapp;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.protectpetapp.databinding.DialogCustomBinding;

import java.util.HashMap;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private WindowManager.LayoutParams mLayoutParams;
    private HashMap<String, Object> extra;
    DialogCustomBinding mBinder;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public static class Builder {
        CustomDialog dialog;
        
        public Builder(Context context){
            dialog  = new CustomDialog(context);
        }

        public Builder setType(int type){
            dialog.setType(type);
            return this;
        }

        public Builder setTitle(String title){
            dialog.setTitle(title);
            return this;
        }

        public Builder setMessage(String message){
            dialog.setMessage(message);
            return this;
        }

        public void show(){
            dialog.show();
        }

        public CustomDialog create(){
            return dialog;
        }
    }

    public static CustomDialog makeDialog(Context context, String message) {
        return new Builder(context).setMessage(message).create();
    }

    @Override
    public void show() {
        super.show();
        if (getWindow() != null && mLayoutParams != null)
            getWindow().setAttributes(mLayoutParams);
    }

    public static final int TEXT_TYPE = 0;
    public static final int EDIT_TEXT_TYPE = 1;
    public static final int IMAGE_TYPE = 2;

    public static final int OK_VIEW = 10;
    public static final int CANCEL_VIEW = 11;


    private void setType(Integer type) {
        switch (type) {
            case TEXT_TYPE:
                mBinder.customDialogContentText.setVisibility(View.VISIBLE);
                mBinder.customDialogContentEditText.setVisibility(View.GONE);
                mBinder.customDialogContentImageView.setVisibility(View.GONE);
                break;
            case EDIT_TEXT_TYPE:
                mBinder.customDialogContentText.setVisibility(View.GONE);
                mBinder.customDialogContentEditText.setVisibility(View.VISIBLE);
                mBinder.customDialogContentImageView.setVisibility(View.GONE);
                break;
            case IMAGE_TYPE:
                mBinder.customDialogContentText.setVisibility(View.GONE);
                mBinder.customDialogContentEditText.setVisibility(View.GONE);
                mBinder.customDialogContentImageView.setVisibility(View.VISIBLE);
                break;
            default:
                mBinder.customDialogContentText.setVisibility(View.GONE);
                mBinder.customDialogContentEditText.setVisibility(View.GONE);
                mBinder.customDialogContentImageView.setVisibility(View.GONE);
                break;
        }
    }

    private void setMessage(@Nullable CharSequence message) {
        mBinder.customDialogContentText.setText(message);
    }

    @Override
    public void onClick(View v) {
        
    }
}
