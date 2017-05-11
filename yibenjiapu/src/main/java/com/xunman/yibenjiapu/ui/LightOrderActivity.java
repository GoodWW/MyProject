package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;

/**
 * 点亮活动，预约服务界面
 */

public class LightOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvLightOrderBack;
    private TextView tvServiceCity;
    private TextView tvServiceCommunity;
    //服务项
    private TextView tvBaojie;
    private TextView tvLlbj;
    private TextView tvKfxl;
    private TextView tvYytc;
    private TextView tvLnhd;
    private LinearLayout llRjzl;
    //已选择项
    private TextView tvSelectServiceItem1;
    private TextView tvSelectServiceItem2;
    private TextView tvSelectServiceItem3;
    private TextView tvSelectServiceItem4;
    private TextView tvSelectServiceItem5;
    private TextView tvSelectServiceItem6;
    private TextView tvSelectServiceItem7;
    private TextView tvSelectServiceItem8;
    //底部统计栏
    private RelativeLayout rlBottomStatistics;
    private TextView tvServicePrice;      //总价格
    //定义服务价格
    private int baojie = 100;
    private int llbj = 199;
    private int kfxl = 320;
    private int yytc = 35;
    private int rjzl = 2999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_light);
        intent = getIntent();
        bundle = intent.getExtras();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightOrderBack = (TextView) findViewById(R.id.tv_light_order_back);
        tvLightOrderBack.setOnClickListener(this);
        tvServiceCity = (TextView) findViewById(R.id.tv_service_city);
        tvServiceCommunity = (TextView) findViewById(R.id.tv_service_community);

        if(bundle != null){
            tvServiceCity.setText(bundle.getString("myCity"));
            tvServiceCommunity.setText(bundle.getString("myCommunity"));
            tvServiceCommunity.setFocusable(false);
            tvServiceCommunity.setEnabled(false);
            tvServiceCommunity.setFocusableInTouchMode(false);
        }

        //服务项
        tvBaojie = (TextView) findViewById(R.id.tv_baojie);
        tvLlbj = (TextView) findViewById(R.id.tv_llbj);
        tvKfxl = (TextView) findViewById(R.id.tv_kfxl);
        tvYytc = (TextView) findViewById(R.id.tv_yytc);
        tvLnhd = (TextView) findViewById(R.id.tv_lnhd);
        llRjzl = (LinearLayout) findViewById(R.id.ll_rjzl);
        //已选择项
        tvSelectServiceItem1 = (TextView) findViewById(R.id.tv_select_service_item1);
        tvSelectServiceItem2 = (TextView) findViewById(R.id.tv_select_service_item2);
        tvSelectServiceItem3 = (TextView) findViewById(R.id.tv_select_service_item3);
        tvSelectServiceItem4 = (TextView) findViewById(R.id.tv_select_service_item4);
        tvSelectServiceItem5 = (TextView) findViewById(R.id.tv_select_service_item5);
        tvSelectServiceItem6 = (TextView) findViewById(R.id.tv_select_service_item6);
        tvSelectServiceItem7 = (TextView) findViewById(R.id.tv_select_service_item7);
        tvSelectServiceItem8 = (TextView) findViewById(R.id.tv_select_service_item8);
        //底部统计栏
        rlBottomStatistics = (RelativeLayout) findViewById(R.id.rl_bottom_statistics);
        tvServicePrice = (TextView) findViewById(R.id.tv_service_price);        //总价格
        tvServicePrice.setText("0");

        tvBaojie.setOnClickListener(this);
        tvLlbj.setOnClickListener(this);
        tvKfxl.setOnClickListener(this);
        tvYytc.setOnClickListener(this);
        tvLnhd.setOnClickListener(this);
        llRjzl.setOnClickListener(this);
        tvSelectServiceItem1.setOnClickListener(this);
        tvSelectServiceItem2.setOnClickListener(this);
        tvSelectServiceItem3.setOnClickListener(this);
        tvSelectServiceItem4.setOnClickListener(this);
        tvSelectServiceItem5.setOnClickListener(this);
        tvSelectServiceItem6.setOnClickListener(this);
        tvSelectServiceItem7.setOnClickListener(this);
        tvSelectServiceItem8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int price = 0;
        switch (view.getId()) {
            case R.id.tv_light_order_back:
                finish();
                break;
            case R.id.tv_baojie:
                if(rlBottomStatistics.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.VISIBLE);
                }
                if(tvSelectServiceItem1.getVisibility() == View.GONE){
                    tvSelectServiceItem1.setVisibility(View.VISIBLE);
                    tvSelectServiceItem1.setText(tvBaojie.getText());
                    price = Integer.parseInt(tvServicePrice.getText().toString())+baojie;
                    tvServicePrice.setText(Integer.toString(price));
                }
                break;
            case R.id.tv_llbj:
                if(rlBottomStatistics.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.VISIBLE);
                }
                if(tvSelectServiceItem2.getVisibility() == View.GONE){
                    tvSelectServiceItem2.setVisibility(View.VISIBLE);
                    tvSelectServiceItem2.setText(tvLlbj.getText());
                    price = Integer.parseInt(tvServicePrice.getText().toString())+llbj;
                    tvServicePrice.setText(Integer.toString(price));
                }
                break;
            case R.id.tv_kfxl:
                if(rlBottomStatistics.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.VISIBLE);
                }
                if(tvSelectServiceItem3.getVisibility() == View.GONE){
                    tvSelectServiceItem3.setVisibility(View.VISIBLE);
                    tvSelectServiceItem3.setText(tvKfxl.getText());
                    price = Integer.parseInt(tvServicePrice.getText().toString())+kfxl;
                    tvServicePrice.setText(Integer.toString(price));
                }
                break;
            case R.id.tv_yytc:
                if(rlBottomStatistics.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.VISIBLE);
                }
                if(tvSelectServiceItem4.getVisibility() == View.GONE){
                    tvSelectServiceItem4.setVisibility(View.VISIBLE);
                    tvSelectServiceItem4.setText(tvYytc.getText());
                    price = Integer.parseInt(tvServicePrice.getText().toString())+yytc;
                    tvServicePrice.setText(Integer.toString(price));
                }
                break;
            case R.id.tv_lnhd:
                if(rlBottomStatistics.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.VISIBLE);
                }
                if(tvSelectServiceItem5.getVisibility() == View.GONE){
                    tvSelectServiceItem5.setVisibility(View.VISIBLE);
                    tvSelectServiceItem5.setText(tvLnhd.getText());
                }
                break;
            case R.id.ll_rjzl:
                if(rlBottomStatistics.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.VISIBLE);
                }
                if(tvSelectServiceItem6.getVisibility() == View.GONE){
                    tvSelectServiceItem6.setVisibility(View.VISIBLE);
                    tvSelectServiceItem6.setText("日间照料");
                    price = Integer.parseInt(tvServicePrice.getText().toString())+rjzl;
                    tvServicePrice.setText(Integer.toString(price));
                }
                break;
            case R.id.tv_select_service_item1:
                tvSelectServiceItem1.setVisibility(View.GONE);
                price = Integer.parseInt(tvServicePrice.getText().toString())-baojie;
                LogUtils.e("测试========","price====="+price+"-------------"+"baojie======="+baojie);
                tvServicePrice.setText(Integer.toString(price));
                if(tvServicePrice.getText().toString().equals("0") && tvSelectServiceItem5.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_select_service_item2:
                tvSelectServiceItem2.setVisibility(View.GONE);
                price = Integer.parseInt(tvServicePrice.getText().toString())-llbj;
                LogUtils.e("测试========","price====="+price+"-------------"+"llbj======="+llbj);
                tvServicePrice.setText(Integer.toString(price));
                if(tvServicePrice.getText().toString().equals("0") && tvSelectServiceItem5.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_select_service_item3:
                tvSelectServiceItem3.setVisibility(View.GONE);
                price = Integer.parseInt(tvServicePrice.getText().toString())-kfxl;
                LogUtils.e("测试========","price====="+price+"-------------"+"kfxl======="+kfxl);
                tvServicePrice.setText(Integer.toString(price));
                if(tvServicePrice.getText().toString().equals("0") && tvSelectServiceItem5.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_select_service_item4:
                tvSelectServiceItem4.setVisibility(View.GONE);
                price = Integer.parseInt(tvServicePrice.getText().toString())-yytc;
                LogUtils.e("测试========","price====="+price+"-------------"+"yytc======="+yytc);
                tvServicePrice.setText(Integer.toString(price));
                if(tvServicePrice.getText().toString().equals("0") && tvSelectServiceItem5.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_select_service_item5:
                tvSelectServiceItem5.setVisibility(View.GONE);
                if(tvServicePrice.getText().toString().equals("0")){
                    rlBottomStatistics.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_select_service_item6:
                tvSelectServiceItem6.setVisibility(View.GONE);
                price = Integer.parseInt(tvServicePrice.getText().toString())-rjzl;
                LogUtils.e("测试========","price====="+price+"-------------"+"rjzl======="+rjzl);
                tvServicePrice.setText(Integer.toString(price));
                if(tvServicePrice.getText().toString().equals("0") && tvSelectServiceItem5.getVisibility() == View.GONE){
                    rlBottomStatistics.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_select_service_item7:
                tvSelectServiceItem7.setVisibility(View.GONE);
                break;
            case R.id.tv_select_service_item8:
                tvSelectServiceItem8.setVisibility(View.GONE);
                break;
        }
    }
}
