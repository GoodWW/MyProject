package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

public class ItemInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView activity_item_info_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        activity_item_info_back = (TextView) findViewById(R.id.activity_item_info_back);
        activity_item_info_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_item_info_back:
                finish();
                break;
        }
    }
}
