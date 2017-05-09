package com.xunman.yibenjiapu.ui;

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
                intent.setClass(this, ServiceLightActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取活动信息
     */
    private void getLightAction() {
        Handler ActionLightH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String info = msg.obj.toString();
                    LogUtils.e("返回信息__点亮活动", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){

                    }else if(result == 12){
                        ToastUtil.t(ActionLightActivity.this, "活动加载失败");
                    }
                }
            }
        };
        Map<String, Object> mapLightAction = new HashMap<>();
        new HttpImpl(mapLightAction, "", "POST",ActionLightH).start();
    }
}
