package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/2/23 8:44
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  祠堂    暂时没使用
 */

public class FeteActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnIncense;
    private Button btnFlower;
    private Button btnTribute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fete);
        initView();
        BaseApplication.list.add(this);
    }

    private void initView() {
        btnIncense = (Button) findViewById(R.id.btn_incense);
        btnFlower = (Button) findViewById(R.id.btn_flower);
        btnTribute = (Button) findViewById(R.id.btn_tribute);

        btnIncense.setOnClickListener(this);
        btnFlower.setOnClickListener(this);
        btnTribute.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    }
}
