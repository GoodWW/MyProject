package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/2 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  点亮活动页面
 */
public class ActionLightActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private TextView tvLightBack;
    //点亮按钮
    private ImageView ivLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_action_light);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightBack = (TextView) findViewById(R.id.tv_light_back);
        ivLight = (ImageView) findViewById(R.id.iv_light);

        tvLightBack.setOnClickListener(this);
        ivLight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_light_back:
                finish();
                break;
            case R.id.iv_light:
                intent.setClass(this, ServiceLightActivity.class);
                startActivity(intent);
                break;
        }
    }
}
