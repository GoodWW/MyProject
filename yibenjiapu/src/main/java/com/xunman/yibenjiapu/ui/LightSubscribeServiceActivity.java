package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/10 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  点亮预约服务页面
 */
public class LightSubscribeServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvLightServiceBack;
    private TextView tvSubServiceCity;
    private TextView tvSubServiceCommunity;
    //预约服务按钮
    private TextView tvSubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_service3);
        intent = getIntent();
        bundle = intent.getExtras();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightServiceBack = (TextView) findViewById(R.id.tv_light_service_back);
        tvLightServiceBack.setOnClickListener(this);

        tvSubServiceCity = (TextView) findViewById(R.id.tv_sub_service_city);
        tvSubServiceCommunity = (TextView) findViewById(R.id.tv_sub_service_community);

        tvSubService = (TextView) findViewById(R.id.tv_sub_service);
        tvSubService.setOnClickListener(this);

        tvSubServiceCity.setText(bundle.getString("myCity"));
        tvSubServiceCommunity.setText(bundle.getString("myCommunity"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_light_service_back:
                finish();
                break;
            case R.id.tv_sub_service:
                intent.setClass(LightSubscribeServiceActivity.this, LightOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
