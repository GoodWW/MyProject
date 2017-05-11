package com.xunman.yibenjiapu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.LightReturnBean;
import com.xunman.yibenjiapu.dao.Login;
import com.xunman.yibenjiapu.utils.Bimp;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/2 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  点亮服务页面
 */
public class LightServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvLightServiceBack;
    //选择省市
    private TextView tvSelect;
    //输入小区
    private EditText etCommunity;
    private int requestCode = 101;//请求码
    //底部图片
    private ImageView ivLightProportion;
    //点亮按钮
    private TextView tvLight;
    private LightReturnBean lightReturnBean;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_service1);
        intent = getIntent();
        bundle = intent.getExtras();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightServiceBack = (TextView) findViewById(R.id.tv_light_service_back);
        tvLightServiceBack.setOnClickListener(this);

        tvSelect = (TextView) findViewById(R.id.tv_select);
        etCommunity = (EditText) findViewById(R.id.et_community);
        tvLight = (TextView) findViewById(R.id.tv_light);
        tvLight.setOnClickListener(this);
        ivLightProportion = (ImageView) findViewById(R.id.iv_light_proportion);

        Float lightProportion = bundle.getFloat("lightProportion");
//        if(lightProportion == -0.001f){      //用户还未选择过小区
//            ivLightProportion.setImageResource(R.drawable.light_proportion_0);
//        }else
        if (lightProportion >= 0.4) {
            ivLightProportion.setImageResource(R.drawable.light_proportion_90);
        } else if (lightProportion >= 0.3) {
            ivLightProportion.setImageResource(R.drawable.light_proportion_80);
        } else if (lightProportion >= 0.2) {
            ivLightProportion.setImageResource(R.drawable.light_proportion_60);
        } else if (lightProportion >= 0.1) {
            ivLightProportion.setImageResource(R.drawable.light_proportion_40);
        } else if (lightProportion > 0) {
            ivLightProportion.setImageResource(R.drawable.light_proportion_20);
        } else if (lightProportion == -0.001f) {
            ivLightProportion.setImageResource(R.drawable.light_proportion_0);     //用户未选择过小区点亮
        }

        if (bundle.getString("myCity") != null && bundle.getString("myCommunity") != null) {
            tvSelect.setText(bundle.getString("myCity"));
            etCommunity.setText(bundle.getString("myCommunity"));
            etCommunity.setFocusable(false);
            etCommunity.setEnabled(false);
            etCommunity.setFocusableInTouchMode(false);
            tvLight.setText("已点亮");
        } else {
            tvSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LightServiceActivity.this, ShowRegionActivity.class);
                    intent.putExtra("address", "address");
                    startActivityForResult(intent, requestCode);
                }
            });
            tvLight.setText("点亮");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_light_service_back:
                finish();
                break;
            case R.id.tv_light:
                if (tvLight.getText().equals("点亮")) {
                    //把用户填写和选择的省市小区信息发送给服务器
                    light();
                } else if (tvLight.getText().equals("已点亮")) {
                    ToastUtil.t(LightServiceActivity.this, "您已点亮服务，该小区点亮住户达到50%即可组建服务点");
                }
                break;
        }
    }

    /**
     * 把用户填写和选择的省市小区信息发送给服务器
     */
    private void light() {
        final String myCity = tvSelect.getText().toString();
        final String myCommunity = etCommunity.getText().toString();
        if (myCity == null || myCity.equals("")) {
            ToastUtil.t(LightServiceActivity.this, "请选择省市信息");
        } else if (myCommunity == null || myCommunity.equals("")) {
            ToastUtil.t(LightServiceActivity.this, "请选填写小区信息");
        } else {
            //设置一个progressdialog的弹窗
            dialog = ProgressDialog.show(LightServiceActivity.this, null, "正在点亮中，请稍候...", true, false);
            Handler LightH = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    String lightInfo = msg.obj.toString();
                    int result = JSON.parseObject(lightInfo).getInteger("result");
                    if (result == 11) {
                        lightReturnBean = JSON.toJavaObject(JSON.parseObject(lightInfo), LightReturnBean.class);
                        float proportion = lightReturnBean.getLightNum() / lightReturnBean.getNum(); //计算点亮人数和该小区总人数的比例
                        if (proportion >= 0.5) {
                            bundle.putString("myCity", myCity);
                            bundle.putString("myCommunity", myCommunity);
                            intent.putExtras(bundle);
                            ToastUtil.t(LightServiceActivity.this, "该小区已完成点亮，进入组建阶段");
                            if (lightReturnBean.getDays() == 0) {
                                //预约服务
                                intent.setClass(LightServiceActivity.this, LightSubscribeServiceActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //组建界面倒计时
                                bundle.putInt("buildDays", lightReturnBean.getDays());
                                intent.putExtras(bundle);
                                intent.setClass(LightServiceActivity.this, LightBuildActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else if (proportion >= 0.4) {
                            ivLightProportion.setImageResource(R.drawable.light_proportion_90);
                        } else if (proportion >= 0.3) {
                            ivLightProportion.setImageResource(R.drawable.light_proportion_80);
                        } else if (proportion >= 0.2) {
                            ivLightProportion.setImageResource(R.drawable.light_proportion_60);
                        } else if (proportion >= 0.1) {
                            ivLightProportion.setImageResource(R.drawable.light_proportion_40);
                        } else if (proportion > 0) {
                            ivLightProportion.setImageResource(R.drawable.light_proportion_20);
                        }
                        tvLight.setText("已点亮");
                        if (dialog.isShowing())dialog.dismiss();
                    } else if (result == 12) {
                        //提交失败
                        ToastUtil.t(LightServiceActivity.this, "点亮失败，请稍后再试");
                        if (dialog.isShowing())dialog.dismiss();
                    }
                }
            };
            //发送信息给服务器
            Map<String, Object> LightMap = new HashMap<>();
            LightMap.put("myCity", myCity);
            LightMap.put("myCommunity", myCommunity);
            //需要服务器修改用户活动状态为已点亮,传入用户id
            LightMap.put("userid", Integer.valueOf(Login.getLoginInfo("id").toString()));
            new HttpImpl(LightMap, "", HttpImpl.POSTMethd, LightH).start();
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
