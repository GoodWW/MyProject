package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/22 0022 11:30
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  功能介绍界面
 *         未使用
 */

public class FunctionIntroductionActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView function_introduction_back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_introduction);
        intiView();
        inti();
        BaseApplication.list.add(this);
    }

    private void inti() {
        function_introduction_back.setOnClickListener(this);
    }

    private void intiView() {
        function_introduction_back = (TextView) findViewById(R.id.function_introduction_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.function_introduction_back://返回
                finish();
                break;
        }
    }
}
