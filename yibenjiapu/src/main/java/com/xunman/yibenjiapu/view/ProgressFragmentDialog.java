package com.xunman.yibenjiapu.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.xunman.yibenjiapu.ui.R;

/**
 * Created by lwk on 2017/5/6.
 */

public class ProgressFragmentDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉Dialog的标题部分
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//dialog背景色变为透明
        //getDialog().setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        View v = inflater.inflate(R.layout.dialog_progress_fragment, null);
        return v;
    }
}
