package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/22 0022 14:42
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  新建家谱界面　　　详情
 */

public class NewBuildActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView build_jia_pu_back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_build);
        intiView();
        inti();
        BaseApplication.list.add(this);
    }

    private void inti() {
        build_jia_pu_back.setOnClickListener(this);
    }

    private void intiView() {
        build_jia_pu_back = (TextView) findViewById(R.id.build_jia_pu_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.build_jia_pu_back:
                finish();
                break;
        }
    }
}
