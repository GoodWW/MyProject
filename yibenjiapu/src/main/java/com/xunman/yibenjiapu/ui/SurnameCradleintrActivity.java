package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.adapter.SurnameCradleintrAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.SDynamicInfo;
import com.xunman.yibenjiapu.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwk on 2017/3/22.
 * 描述： 姓氏动态列表页面
 */

public class SurnameCradleintrActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvDetailsBack;
    private ListView lvSurnameCradleintr;
    private List<SDynamicInfo.ContentsBean> lists;
    private SDynamicInfo sDynamicInfo;
    private Intent intent;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surname_cradleintr);
        BaseApplication.list.add(this);
        intent = getIntent();
        extras = intent.getExtras();
        String str = extras.getString("strSD");
        LogUtils.e("str", str);
        sDynamicInfo = JSON.parseObject(str, SDynamicInfo.class);
        lists = new ArrayList<>();
        lists.addAll(sDynamicInfo.getContents());
        intView();
    }

    private void intView() {
        tvDetailsBack = (TextView) findViewById(R.id.tv_details_back);
        tvDetailsBack.setOnClickListener(this);
        lvSurnameCradleintr = (ListView) findViewById(R.id.lv_surname_cradleintr);
        SurnameCradleintrAdapter surnameCradleintrAdapter = new SurnameCradleintrAdapter(this, lists);
        lvSurnameCradleintr.setAdapter(surnameCradleintrAdapter);
        lvSurnameCradleintr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(SurnameCradleintrActivity.this, TrendsDetailsActivity.class);
                extras.putString("tvTrendsTitle", lists.get(position).getTrends_name());
                extras.putString("tvTrendsTime", lists.get(position).getPublish_time());
                extras.putString("tvTrendsInfo", lists.get(position).getTrends_contents());
                intent.putExtras(extras);
                //LogUtils.e("bundle", extras.getString("strSD"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_details_back:
                finish();
                break;
        }
    }
}
