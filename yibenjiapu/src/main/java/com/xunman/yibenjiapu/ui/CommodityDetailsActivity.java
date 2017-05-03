package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/23
 * 包名：com.xunman.yibenjiapu.ui
 * 描述：  商品详情
 */

public class CommodityDetailsActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    private TextView activity_my_integral_back;
    private CheckBox rbIntegral;
    private CheckBox rbCash;
    private CheckBox rbIntegralCash;
    private RadioGroup rgPayMode;
    //底部结算栏
    private RelativeLayout rlBottom;

    //兑换按钮
    private Button commod_send;

    //选择框积分兑换
    private TextView tv_cb_jifen;
    //选择框现金购买
    private TextView tv_cb_money;
    //选择框积分加现金购买
    private TextView tv_cb_jifen_mone; //积分
    private TextView tv_cb_money_jifen; //现金
    //结算单价积分
    private TextView tv_integral;
    //结算单价现金
    private TextView tv_money;

    private String jifen = "300";
    private String money = "30";
    private String jifen_money = "200";
    private String money_jifen = "10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        activity_my_integral_back = (TextView) findViewById(R.id.activity_my_integral_back);
        activity_my_integral_back.setOnClickListener(this);

        commod_send  = (Button) findViewById(R.id.commod_send);
        rbIntegral = (CheckBox) findViewById(R.id.rb_integral);
        rbCash = (CheckBox) findViewById(R.id.rb_cash);
        rbIntegralCash = (CheckBox) findViewById(R.id.rb_integral_cash);
        rgPayMode = (RadioGroup) findViewById(R.id.rg_pay_mode);
        rlBottom = (RelativeLayout) findViewById(R.id.rl_bottom);

        //选择框积分兑换
        tv_cb_jifen = (TextView) findViewById(R.id.tv_cb_jifen);
        tv_cb_jifen.setText(jifen);
        //选择框现金购买
        tv_cb_money = (TextView) findViewById(R.id.tv_cb_money);
        tv_cb_money.setText(money);
        //选择框积分加现金购买
        tv_cb_jifen_mone = (TextView) findViewById(R.id.tv_cb_jifen_money); //积分
        tv_cb_money_jifen = (TextView) findViewById(R.id.tv_cb_money_jifen); //现金
        tv_cb_jifen_mone.setText(jifen_money);
        tv_cb_money_jifen.setText(money_jifen);
        //结算单价积分
        tv_integral = (TextView) findViewById(R.id.tv_integral);
        //结算单价现金
        tv_money = (TextView) findViewById(R.id.tv_money);

        rbIntegral.setOnClickListener(this);
        rbCash.setOnClickListener(this);
        rbIntegralCash.setOnClickListener(this);
        rgPayMode.setOnCheckedChangeListener(this);
        commod_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_my_integral_back:
                finish();
                break;
            case R.id.commod_send:
                LogUtils.e("点击兑换按钮","点击兑换按钮");
                break;
        }
        if(rbIntegral.isChecked()){
            rbIntegral.setChecked(true);
            rbCash.setChecked(false);
            rbIntegralCash.setChecked(false);
            tv_integral.setText(jifen);
            tv_money.setText("0");
            rlBottom.setVisibility(View.VISIBLE);
        }
        if(rbCash.isChecked()){
            rbCash.setChecked(true);
            rbIntegral.setChecked(false);
            rbIntegralCash.setChecked(false);
            tv_integral.setText("0");
            tv_money.setText(money);
            rlBottom.setVisibility(View.VISIBLE);
        }
        if(rbIntegralCash.isChecked()){
            rbIntegralCash.setChecked(true);
            rbIntegral.setChecked(false);
            rbCash.setChecked(false);
            tv_integral.setText(jifen_money);
            tv_money.setText(money_jifen);
            rlBottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//        if(checkedId==rbIntegral.getId()){
//            Toast.makeText(this,"积分兑换选中", Toast.LENGTH_LONG).show();
//        }
//        if(checkedId==rbCash.getId()){
//            Toast.makeText(this,"现金购买选中", Toast.LENGTH_LONG).show();
//        }
//        if(checkedId==rbIntegralCash.getId()){
//            Toast.makeText(this,"积分加现金购买选中", Toast.LENGTH_LONG).show();
//        }
    }
}
