package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/22 0022 10:10
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  手机号码变更页面
 */

public class ChangePhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private DeletableEditText old_phone, old_phone_code, new_phone;
    private Button get_new_phone_code, send_new_phone;
    private TextView change_phone_back;
    private String str = "+++++";
    private int time = 10;
    private Intent intent;
    private String phone;
    private String code, newPhone;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            switch (msg.what) {
                case 0x234:
                    LogUtils.e("ChangePhoneActivity  data", data.toString());
                    break;
                case 0x123:
                    LogUtils.e("ChangePhoneActivity  0x123", data.toString());
                    if (time == 0) {
                        get_new_phone_code.setText("免费获取");
                        get_new_phone_code.setClickable(true);
                        time = 10;
                    } else
                        get_new_phone_code.setText("(" + (time--) + "s)");
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        intiView();
        inti();
        BaseApplication.list.add(this);
    }

    private void inti() {
        change_phone_back.setOnClickListener(this);
        send_new_phone.setOnClickListener(this);
        get_new_phone_code.setOnClickListener(this);
    }

    private void intiView() {
        intent = getIntent();
        new_phone = (DeletableEditText) findViewById(R.id.new_phone);
        old_phone = (DeletableEditText) findViewById(R.id.old_phone);
        old_phone_code = (DeletableEditText) findViewById(R.id.old_phone_code);
        get_new_phone_code = (Button) findViewById(R.id.get_old_phone_code);
        send_new_phone = (Button) findViewById(R.id.send_new_phone);
        change_phone_back = (TextView) findViewById(R.id.change_phone_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_phone_back://返回
                finish();
                break;
            case R.id.send_new_phone://确认修改按钮
                code = old_phone_code.getText().toString();
                newPhone = new_phone.getText().toString();
                LogUtils.e(str, "确认修改按钮");
                /* 只有用户名、密码不为空，并且用户名为11位手机号码才允许登陆 */
                if (TextUtils.isEmpty(old_phone.getText())) {
                    old_phone.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(old_phone_code.getText())) {
                    old_phone_code.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                } else {
                    // System.out.println("——————————输入验证码和密码，完成修改——————————");
                    Handler h4 = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 0x123) {
                                LogUtils.e("返回信息", msg.obj.toString());
                                //11成功 10电话呗注册了  12注册失败没有具体原因  126 验证码错误
                                int result = JSON.parseObject(msg.obj.toString()).getInteger("result");
                                Log.e("返回信息2", msg.obj.toString());
                                if (result == 11) {
                                    LogUtils.e("返回信息2", "修改成功");
                                    ToastUtil.t(ChangePhoneActivity.this, "恭喜你，修改成功！");
//                                    //传送电话号码和用户ID到下一个界面
//                                    intent.setClass(ChangePhoneActivity.this, AddInformationActivity.class);
//                                    startActivity(intent);
                                    finish();
                                } else if (result == 10) {
                                    ToastUtil.t(ChangePhoneActivity.this, "电话号码不正确！");
                                } else if (result == 12) {
                                    ToastUtil.t(ChangePhoneActivity.this, "网络链接失败！");
                                } else if (result == 126) {
                                    ToastUtil.t(ChangePhoneActivity.this, "验证码错误");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_account", phone.trim());
                    map.put("code", code.trim());
                    map.put("tele", newPhone.trim());
                    LogUtils.e("map", map.toString());
                    new HttpImplStringTest(map, "UpdateTelephone.xml", h4, "POST").start();
                }
                break;
            case R.id.get_old_phone_code:
                LogUtils.e(str, "获取验证码");
                phone = old_phone.getText().toString();
                // 获取验证码
                boolean isTel = true; // 标记位：true-是手机号码；false-不是手机号码
                if (old_phone.getText().toString().length() == 11) {
                    for (int i = 0; i < old_phone.getText().toString().length(); i++) {
                        char c = old_phone.getText().toString().charAt(i);
                        if (!Character.isDigit(c)) {
                            isTel = false;
                            break; // 只要有一位不符合要求退出循环
                        }
                    }
                } else {
                    isTel = false;
                }
                /** 只有用户名、密码不为空，并且用户名为11位手机号码才允许登陆 */
                if (TextUtils.isEmpty(old_phone.getText())) {
                    old_phone.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                } else if (!isTel) {
                    Toast.makeText(getApplicationContext(), "请输入11位手机号码！", Toast.LENGTH_SHORT).show();
                } else {
                    //获取验证码返回的信息，并通知更新UI
                    Handler h3 = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 0x123) {
                                Log.e("返回信息", msg.obj.toString());
                                int result = JSON.parseObject(msg.obj.toString()).getInteger("result");
                                if (result == 11) {
                                    Log.e("返回信息", "发送验证码成功");
                                    get_new_phone_code.setClickable(false);
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                while (time > 0) {
                                                    Thread.sleep(1000);
                                                    handler.sendEmptyMessage(0x123);
                                                }
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }.start();
                                    ToastUtil.t(getApplicationContext(), "发送验证码成功！");
                                } else if (result == 127) {
                                    ToastUtil.t(getApplicationContext(), "获取验证码失败！");
                                    Log.e("返回信息", "发送验证码失败，重新获取");
                                } else if (result == 10) {
                                    ToastUtil.t(getApplicationContext(), "电话号码没有注册！！");
                                    Log.e("返回信息", "电话号码没有注册！！");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    Log.e("电话号码", phone.trim());
                    map.put("user_account", phone.trim());
                    new HttpImplStringTest(map, "GetUpdateTelepgoneCode.xml", h3, "POST").start();
                }
                break;
        }
    }
}
