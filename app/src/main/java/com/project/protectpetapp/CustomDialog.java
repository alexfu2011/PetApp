package com.project.protectpetapp;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.protectpetapp.databinding.DialogCustomBinding;

public class CustomDialog extends Dialog {

    DialogCustomBinding mBinder;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public static class Builder {
        CustomDialog dialog;

        public Builder(Context context) {
            dialog = new CustomDialog(context);
        }

        public Builder setTitle(String title) {
            dialog.setTitle(title);
            return this;
        }

        public Builder setMessage(String message) {
            dialog.setMessage(message);
            return this;
        }

        public void show() {
            dialog.show();
        }

        public CustomDialog create() {
            return dialog;
        }
    }

    public static CustomDialog makeDialog(Context context, String message) {
        return new Builder(context).setMessage(message).create();
    }

    private void setMessage(@Nullable CharSequence message) {
        mBinder.customDialogContentText.setText(message);
    }
}
