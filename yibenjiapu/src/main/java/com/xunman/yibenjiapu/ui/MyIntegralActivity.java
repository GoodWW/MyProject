package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.Tip;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/23
 * 包名：com.xunman.yibenjiapu.ui
 * 描述：  积分商城页面
 */

public class MyIntegralActivity extends AppCompatActivity implements View.OnClickListener {
    //返回按钮
    private TextView activity_my_integral_back;
    //商品1-6
    private LinearLayout llCommodity1;
    private LinearLayout llCommodity2;
    private LinearLayout llCommodity3;
    private LinearLayout llCommodity4;
    private LinearLayout llCommodity5;
    private LinearLayout llCommodity6;
    //查看商品兑换记录按钮
    private TextView tvConvertRecord;
    //更多商品按钮
    private TextView tvMore;
    //抽奖按钮
    private TextView tvCj;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_my_integral);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        activity_my_integral_back = (TextView) findViewById(R.id.activity_my_integral_back);
        llCommodity1 = (LinearLayout) findViewById(R.id.ll_commodity1);
        llCommodity2 = (LinearLayout) findViewById(R.id.ll_commodity2);
        llCommodity3 = (LinearLayout) findViewById(R.id.ll_commodity3);
        llCommodity4 = (LinearLayout) findViewById(R.id.ll_commodity4);
        llCommodity5 = (LinearLayout) findViewById(R.id.ll_commodity5);
        llCommodity6 = (LinearLayout) findViewById(R.id.ll_commodity6);
        tvConvertRecord = (TextView) findViewById(R.id.tv_convert_record);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvCj = (TextView) findViewById(R.id.tv_cj);

        tvCj.setOnClickListener(this);
        activity_my_integral_back.setOnClickListener(this);
        llCommodity1.setOnClickListener(this);
        llCommodity2.setOnClickListener(this);
        llCommodity3.setOnClickListener(this);
        llCommodity4.setOnClickListener(this);
        llCommodity5.setOnClickListener(this);
        llCommodity6.setOnClickListener(this);
        tvConvertRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_my_integral_back:
                finish();
                break;
            case R.id.ll_commodity1:
                LogUtils.e("!!!!!!!!!!!!!", "点了");
                intent.setClass(this, CommodityDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_commodity2:
                intent.setClass(this, CommodityDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_commodity3:
                intent.setClass(this, CommodityDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_commodity4:
                intent.setClass(this, CommodityDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_commodity5:
                intent.setClass(this, CommodityDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_commodity6:
                intent.setClass(this, CommodityDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_convert_record:
                intent.setClass(this, ConvertRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_cj:
                new Tip(MyIntegralActivity.this).show();
                break;
        }
    }
}
