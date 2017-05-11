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
 * 描述：  点亮组建中页面
 */
public class LightBuildActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvLightServiceBack;

    private TextView tvBuildCity;
    private TextView tvBuildCommunity;
    private TextView tvBuildTimeTensDigit;
    private TextView tvBuildTimeUnitsDigit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_service2);
        intent = getIntent();
        bundle = intent.getExtras();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightServiceBack = (TextView) findViewById(R.id.tv_light_service_back);
        tvLightServiceBack.setOnClickListener(this);

        tvBuildCity = (TextView) findViewById(R.id.tv_build_city);
        tvBuildCommunity = (TextView) findViewById(R.id.tv_build_community);
        tvBuildTimeTensDigit = (TextView) findViewById(R.id.tv_build_time_tens_digit);
        tvBuildTimeUnitsDigit = (TextView) findViewById(R.id.tv_build_time_units_digit);

        tvBuildCity.setText(bundle.getString("myCity"));
        tvBuildCommunity.setText(bundle.getString("myCommunity"));
        //倒计时
        int residueTime = bundle.getInt("buildDays");
        //个位：
        int units = residueTime%10;
        //十位
        int tens = residueTime/10%10;
        tvBuildTimeTensDigit.setText(tens);
        tvBuildTimeUnitsDigit.setText(units);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_light_service_back:
                finish();
                break;
        }
    }
}
