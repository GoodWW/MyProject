package com.xunman.yibenjiapu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.ActionLightBean;
import com.xunman.yibenjiapu.bean.ActionLightListBean;
import com.xunman.yibenjiapu.dao.Login;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/2 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  活动页面
 */
public class ActionLightActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvLightBack;
    //点亮按钮
    private ImageView ivLight;
    //服务器返回信息映射类
    private ActionLightBean actionLightBean;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        bundle = intent.getExtras();
        setContentView(R.layout.activity_action_light);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightBack = (TextView) findViewById(R.id.tv_light_back);
        ivLight = (ImageView) findViewById(R.id.iv_light);

        tvLightBack.setOnClickListener(this);
        ivLight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_light_back:
                finish();
                break;
            case R.id.iv_light:
                getLightAction();
                break;
        }
    }

    /**
     * 获取用户对于点亮活动的状态：
     *   一、 未点亮
     *   二、 已点亮  获取用户省市和小区信息
     *        1、小区点亮人数不够   （需要小区人数和点亮人数比例）
     *        2、小区点亮人数够   （组建剩余天数剩余天数）
     *             （1）显示剩余天数
     *             （2）剩余天数为0，显示预约服务页面
     * 跳转相应页面
     */
    private void getLightAction() {
        //设置一个progressdialog的弹窗
        dialog = ProgressDialog.show(ActionLightActivity.this, null, "跳转活动中，请稍候...", true, false);
        Handler ActionLightH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String info = msg.obj.toString();
                    LogUtils.e("返回信息__点亮活动", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){
                        actionLightBean=JSON.toJavaObject(JSON.parseObject(info), ActionLightBean.class);
                        if(actionLightBean.getUserLight()){   //已点亮
                            //获取用户省份和小区，并传递到下一个界面
                            String myCity = actionLightBean.getCity();
                            String myCommunity = actionLightBean.getCommunity();
                            bundle.putString("myCity", myCity);
                            bundle.putString("myCommunity", myCommunity);
                            intent.putExtras(bundle);
                            float lightProportion = actionLightBean.getLightNum()/actionLightBean.getNum(); //计算点亮人数和该小区总人数的比例
                            if(lightProportion >= 0.5){
                                //组建界面
                                if(actionLightBean.getDays() == 0){
                                    //预约服务
                                    intent.setClass(ActionLightActivity.this,LightSubscribeServiceActivity.class);
                                    startActivity(intent);
                                }else {
                                    //组建界面倒计时
                                    bundle.putInt("buildDays",actionLightBean.getDays());
                                    intent.putExtras(bundle);
                                    intent.setClass(ActionLightActivity.this,LightBuildActivity.class);
                                    startActivity(intent);
                                }
                            }else {
                                // 点亮界面（点亮按钮文字显示已点亮，下面图片显示动画比例）
                                bundle.putFloat("lightProportion", lightProportion);
                                intent.putExtras(bundle);
                                intent.setClass(ActionLightActivity.this,LightServiceActivity.class);
                                startActivity(intent);
                            }
                        }else {     //点亮界面（点亮按钮文字显示未点亮）
                            bundle.putFloat("lightProportion", -0.001f);
                            intent.putExtras(bundle);
                            intent.setClass(ActionLightActivity.this,LightServiceActivity.class);
                            startActivity(intent);
                        }
                    }else if(result == 12){
                        ToastUtil.t(ActionLightActivity.this, "活动加载失败");
                    }
                }
            }
        };
        Map<String, Object> mapLightAction = new HashMap<>();
        mapLightAction.put("userid",Integer.valueOf(Login.getLoginInfo("id").toString()));
        new HttpImpl(mapLightAction, "", "POST",ActionLightH).start();
    }
}
