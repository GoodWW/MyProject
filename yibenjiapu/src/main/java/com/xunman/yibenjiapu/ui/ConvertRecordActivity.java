package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xunman.yibenjiapu.adapter.ConvertRecordAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/23
 * 包名：com.xunman.yibenjiapu.ui
 * 描述：  积分兑换记录列表
 */

public class ConvertRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView activity_my_integral_back;
    private ListView lvConvertRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_record);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        activity_my_integral_back = (TextView) findViewById(R.id.activity_my_integral_back);
        activity_my_integral_back.setOnClickListener(this);
        lvConvertRecord = (ListView) findViewById(R.id.lv_convert_record);
        ConvertRecordAdapter convertRecordAdapter = new ConvertRecordAdapter(this);
        lvConvertRecord.setAdapter(convertRecordAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_my_integral_back:
                finish();
                break;
        }
    }
}
