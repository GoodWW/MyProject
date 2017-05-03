package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 意见反馈
 * 暂时没用
 *
 */
public class ViewBackActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView activity_view_back_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_back);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {

        activity_view_back_back = (TextView) findViewById(R.id.activity_view_back_back);
        activity_view_back_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_view_back_back:
                finish();
                break;
        }
    }
}
