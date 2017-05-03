package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/22 0022 14:16
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  家谱组建界面    暂时没使用
 */

public class BuildJiaPuActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView build_jia_pu_back;
    private Button new_build;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_jiapu);
        intiView();
        init();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        intent = getIntent();
        build_jia_pu_back = (TextView) findViewById(R.id.build_jia_pu_back);
        new_build = (Button) findViewById(R.id.new_build);
    }

    private void init() {
        build_jia_pu_back.setOnClickListener(this);
        new_build.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.build_jia_pu_back:
                finish();
                break;
            case R.id.new_build:
                intent.setClass(this,NewBuildActivity.class);
                startActivity(intent);
                break;
        }
    }
}
