package com.xunman.yibenjiapu.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.view.LuckPanLayout;
import com.xunman.yibenjiapu.view.RotatePan;

/**
 * Created by lwk on 2017/4/19.
 */

public class Tip implements RotatePan.AnimationEndListener{
    private TextView tvDismiss;
    private Dialog mDialog;

    private RotatePan rotatePan;
    private LuckPanLayout luckPanLayout;
    private ImageView goBtn;
    private String[] strs = {"一本家谱1","谢谢惠顾","一本家谱2","一本家谱3","一本家谱4","一本家谱5"};
    private Activity context;

    public Tip(final Activity context) {
        this.context = context;
        mDialog = new Dialog(context, R.style.dialog);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
//        wl.x = -30;
//        wl.y = 20;
        window.setAttributes(wl);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(R.layout.tip);
        mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mDialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 0);

        tvDismiss = (TextView) mDialog.findViewById(R.id.tv_dismiss);
        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        luckPanLayout = (LuckPanLayout) mDialog.findViewById(R.id.luckpan_layout);
        luckPanLayout.startLuckLight();
        rotatePan = (RotatePan) mDialog.findViewById(R.id.rotatePan);
        rotatePan.setAnimationEndListener(this);
        goBtn = (ImageView)mDialog.findViewById(R.id.go);
        luckPanLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = context.getWindow().getDecorView().getWidth();
                int height = context.getWindow().getDecorView().getHeight();
                int backHeight = 0;
                int MinValue = Math.min(width,height);
                MinValue -= Util.dip2px(context,10)*2;
                backHeight = MinValue/2;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) luckPanLayout.getLayoutParams();
                lp.width = MinValue;
                lp.height = MinValue;
                luckPanLayout.setLayoutParams(lp);
                MinValue -= Util.dip2px(context,28)*2;
                lp = (RelativeLayout.LayoutParams) rotatePan.getLayoutParams();
                lp.height = MinValue;
                lp.width = MinValue;
                rotatePan.setLayoutParams(lp);
                lp = (RelativeLayout.LayoutParams) goBtn.getLayoutParams();
                lp.topMargin += backHeight;
                lp.topMargin -= (goBtn.getHeight()/2);
                goBtn.setLayoutParams(lp);
                context.getWindow().getDecorView().requestLayout();
            }
        });
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotatePan.startRotate(1);
                luckPanLayout.setDelayTime(100);
                goBtn.setEnabled(false);
            }
        });
    }

    public void show() {
        mDialog.show();
    }

    @Override
    public void endAnimation(int position) {
        goBtn.setEnabled(true);
        luckPanLayout.setDelayTime(500);
        Toast.makeText(context,"Position = "+position+","+strs[position], Toast.LENGTH_SHORT).show();
    }
}
