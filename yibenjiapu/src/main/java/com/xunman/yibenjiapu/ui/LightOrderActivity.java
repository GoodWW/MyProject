package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

public class LightOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLightOrderBack;
    //选择省市
    private TextView tvSelect;
    private int requestCode = 101;//请求码

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_light);
        intent = getIntent();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightOrderBack = (TextView) findViewById(R.id.tv_light_order_back);
        tvLightOrderBack.setOnClickListener(this);
        tvSelect = (TextView) findViewById(R.id.tv_select);
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(LightOrderActivity.this, ShowRegionActivity.class);
                intent.putExtra("address", "address");
                startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_light_order_back:
                finish();
                break;
        }
    }

    /**
     * 将返回数据给tv
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102) {
            tvSelect.setText(data.getStringExtra("address"));
        }
    }
}
