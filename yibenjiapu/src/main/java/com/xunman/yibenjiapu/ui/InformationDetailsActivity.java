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
 * 描述：  论坛详情页面
 */
public class InformationDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private TextView tvInformationDetailsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_information_details);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvInformationDetailsBack = (TextView) findViewById(R.id.tv_information_details_back);

        tvInformationDetailsBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_information_details_back:
                finish();
                break;
        }
    }
}
