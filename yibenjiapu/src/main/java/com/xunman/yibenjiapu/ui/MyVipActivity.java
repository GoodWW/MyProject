package com.xunman.yibenjiapu.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/4/21
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  我的会员
 */

public class MyVipActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView activity_my_vip_back,tv_line0,tv_line1;

    private RelativeLayout rlMyVip;

    private Intent intent;
    private Bundle bundle;
    private RelativeLayout rl_my_vip_current;

    private RelativeLayout rl_my_vip_0;

    private HorizontalScrollView hs_my_vip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vip);
        initView();
        BaseApplication.list.add(this);
        LogUtils.e("onCreate()", "onCreate");
        LogUtils.e("width:", rl_my_vip_0.getWidth() + "");
        LogUtils.e("---->height:", rl_my_vip_0.getHeight() + "");
    }

    private void initView() {
        rl_my_vip_current = (RelativeLayout) findViewById(R.id.rl_my_vip_current);
        hs_my_vip = (HorizontalScrollView) findViewById(R.id.hs_my_vip);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(rl_my_vip_current, "translationX", 0f, 135f);

        rl_my_vip_0 = (RelativeLayout) findViewById(R.id.rl_my_vip_0);

        activity_my_vip_back = (TextView) findViewById(R.id.my_vip_back);
        tv_line1 = (TextView) findViewById(R.id.tv_line1);
        tv_line0 = (TextView) findViewById(R.id.tv_line0);
        activity_my_vip_back.setOnClickListener(this);
        //设置焦点，确认进入界面显示到我当前VIP等级
        rlMyVip = (RelativeLayout) findViewById(R.id.rl_my_vip);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_vip_back:
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("onstart()", "onstart");
        LogUtils.e("width:", rl_my_vip_0.getWidth() + "");
        LogUtils.e("---->height:", rl_my_vip_0.getHeight() + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("onResume()", "onResume");
        LogUtils.e("width:", rl_my_vip_0.getWidth() + "");
        LogUtils.e("---->height:", rl_my_vip_0.getHeight() + "");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtils.e("onAttachedToWindow()", "onAttachedToWindow");
        LogUtils.e("width:", rl_my_vip_0.getWidth() + "");
        LogUtils.e("---->height:", rl_my_vip_0.getHeight() + "");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LogUtils.e("onWindowFocusChanged()", "onWindowFocusChanged");
        LogUtils.e("width:", rl_my_vip_0.getWidth() + "");
        LogUtils.e("---->height:", rl_my_vip_0.getHeight() + "");// 此时打印出了控件宽高，其他地方的打印为0
        float line_width0 = tv_line0.getWidth();
        float line_width1 = tv_line1.getWidth();
        float vip_width = rl_my_vip_0.getWidth();
        float run_num = line_width0+line_width1+vip_width+line_width1/500*452;

        ObjectAnimator animator = ObjectAnimator.ofFloat(rl_my_vip_current, "translationX", 0f, run_num);
        animator.setDuration(5000).setInterpolator(new OvershootInterpolator());
        animator.start();

        hs_my_vip.scrollTo(0, 5000);
    }
}
