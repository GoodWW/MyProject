package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 帮助中心   暂时没使用
 */
public class HelpCenterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView activity_help_center_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        IntiView();
        BaseApplication.list.add(this);
    }

    private void IntiView() {
        activity_help_center_back = (TextView) findViewById(R.id.activity_help_center_back);
        activity_help_center_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_help_center_back:
                finish();
                break;
        }

    }
}
