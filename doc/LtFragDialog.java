package com.lion.appfwk.drive.navi.view.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lion.component.widget.dialog.BaseFragDialog;
import com.lion.drive.R;

public class LtFragDialog extends BaseFragDialog {
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.lt_navi_left_bottom_setting, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化view
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
        doRegister();
        updateView();
    }

    @Override
    public void onStop() {
        super.onStop();
        doUnregister();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        doOnDismiss();
    }

    private void initDialog() {
        if (getDialog() == null || null == getDialog().getWindow()) {
            return;
        }
        getDialog().getWindow().setDimAmount(0);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.theme_dialog_back_color);
    }

    /**
     * 注册监听
     */
    private void doRegister() {

    }

    /**
     * 注销监听
     */
    private void doUnregister() {

    }

    /**
     * dialog每次显示时更新view
     */
    private void updateView() {

    }

    /**
     * dialog消失时需要做的事情
     */
    private void doOnDismiss() {

    }
}
