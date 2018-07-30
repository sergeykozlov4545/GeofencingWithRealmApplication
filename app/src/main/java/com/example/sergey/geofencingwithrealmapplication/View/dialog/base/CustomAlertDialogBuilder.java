package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAlertDialogBuilder extends AlertDialog.Builder {

    @BindView(R.id.contentDialog)
    LinearLayout contentDialogView;

    @BindView(R.id.messageView)
    TextView messageView;

    @BindView(R.id.negativeButton)
    Button negativeButton;

    @BindView(R.id.positiveButton)
    Button positiveButton;

    public CustomAlertDialogBuilder(@NonNull Context context) {
        super(context);

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_custom_layout, null);
        super.setView(view);

        ButterKnife.bind(this, view);
    }

    @Override
    public CustomAlertDialogBuilder setTitle(int titleId) {
        return setTitle(getContext().getString(titleId));
    }

    @Override
    public CustomAlertDialogBuilder setTitle(@Nullable CharSequence title) {
        super.setTitle(title);
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setMessage(int messageId) {
        return setMessage(getContext().getString(messageId));
    }

    @Override
    public CustomAlertDialogBuilder setMessage(@Nullable CharSequence message) {
        if (messageView != null) {
            messageView.setText(message);
        }
        return this;
    }

    @Override
    public CustomAlertDialogBuilder setView(int layoutResId) {
        View view = LayoutInflater.from(getContext())
                .inflate(layoutResId, contentDialogView, false);
        return setView(view);
    }

    @Override
    public CustomAlertDialogBuilder setView(View view) {
        contentDialogView.removeAllViews();
        contentDialogView.addView(view);
        return this;
    }

    public CustomAlertDialogBuilder setPositiveButton(int textId, View.OnClickListener listener) {
        return setPositiveButton(getContext().getString(textId), listener);
    }

    public CustomAlertDialogBuilder setPositiveButton(CharSequence text, View.OnClickListener listener) {
        if (listener != null) {
            positiveButton.setText(text);
            positiveButton.setOnClickListener(listener);
            positiveButton.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public CustomAlertDialogBuilder setNegativeButton(int textId, View.OnClickListener listener) {
        return setNegativeButton(getContext().getString(textId), listener);
    }

    public CustomAlertDialogBuilder setNegativeButton(CharSequence text, View.OnClickListener listener) {
        if (listener != null) {
            negativeButton.setText(text);
            negativeButton.setOnClickListener(listener);
            negativeButton.setVisibility(View.VISIBLE);
        }
        return this;
    }
}
