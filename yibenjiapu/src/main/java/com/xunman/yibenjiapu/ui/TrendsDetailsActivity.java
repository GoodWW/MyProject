package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * Created by lwk on 2017/3/22.
 * 描述： 姓氏动态详情页面
 */

public class TrendsDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvDetailsBack;
    private TextView tvTrendsTitle;
    private TextView tvTrendsInfo;
    private TextView tvTrendsTime;
    private Intent intent;
    private Bundle extras;
    private String trendsTitle;
    private String trendsTime;
    private String trendsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends_details);
        intent = getIntent();
        extras = intent.getExtras();
        trendsTitle = extras.getString("tvTrendsTitle");
        trendsTime = extras.getString("tvTrendsTime");
        trendsInfo = extras.getString("tvTrendsInfo");
        initView();
        BaseApplication.list.add(this);
    }

    private void initView() {
        tvDetailsBack = (TextView) findViewById(R.id.tv_details_back);
        tvDetailsBack.setOnClickListener(this);
        tvTrendsTitle = (TextView) findViewById(R.id.tv_trends_title);
        tvTrendsInfo = (TextView) findViewById(R.id.tv_trends_info);
        tvTrendsTime = (TextView) findViewById(R.id.tv_trends_time);

        tvTrendsTitle.setText(trendsTitle);
        tvTrendsInfo.setText(trendsInfo);
        tvTrendsTime.setText(trendsTime);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_details_back:
                finish();
                break;
        }
    }
}
