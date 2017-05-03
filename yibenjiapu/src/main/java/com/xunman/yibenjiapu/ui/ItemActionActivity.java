package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 活动详情界面
 *
 */

public class ItemActionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView activity_item_action_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_action);
        intView();
        BaseApplication.list.add(this);
    }

    private void intView() {

        activity_item_action_back = (TextView) findViewById(R.id.activity_item_action_back);
        activity_item_action_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.activity_item_action_back:
                finish();

                break;
        }
    }
}
