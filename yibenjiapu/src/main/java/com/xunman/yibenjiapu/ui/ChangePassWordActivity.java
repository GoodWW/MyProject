package com.xunman.yibenjiapu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class ChangePassWordActivity extends Activity implements View.OnClickListener {

    /**
     * 输入的邮箱账号
     */
    private DeletableEditText email_change_number;
    /**
     * 返回键
     */
    private TextView email_change_back;
    /**
     * 输入获取的验证码
     */
    private DeletableEditText email_change_code;
    /**
     * 第一次输入的密码
     */
    private DeletableEditText email_change_newpassword;
    /**
     * 第二次输入的密码
     */
    private DeletableEditText email_change_againnewpassword;
    /**
     * 获取验证码按钮
     */
    private Button email_change_btn;
    /**
     * 确认提交按钮
     */
    private Button email_change_sendchange;
    private int time = 10;
    private Intent intent;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            switch (msg.what) {
                case 0x234:
                    LogUtils.e("RegistActivity  data", data.toString());
                    break;
                case 0x123:
                    LogUtils.e("RegistActivity  0x123", data.toString());
                    if (time == 0) {
                        email_change_btn.setText("免费获取");
                        email_change_btn.setClickable(true);
                        time = 10;
                    } else
                        email_change_btn.setText("(" + (time--) + "s)");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_pass_word);
        setContentView(R.layout.activity_change_pass_word);
        intiview();
        BaseApplication.list.add(this);
    }

    private void intiview() {
        intent = getIntent();
        email_change_number = (DeletableEditText) findViewById(R.id.change_pass_word_number);
        email_change_code = (DeletableEditText) findViewById(R.id.change_pass_word_code);
        email_change_newpassword = (DeletableEditText) findViewById(R.id.change_pass_word_again_pass_word);
        email_change_againnewpassword = (DeletableEditText) findViewById(R.id.change_pass_word_again_new_pass_word);
        email_change_btn = (Button) findViewById(R.id.change_pass_word_get_code_bt);
        email_change_sendchange = (Button) findViewById(R.id.change_pass_word_send_change);
        email_change_back = (TextView) findViewById(R.id.forget_pass_word_back);

        email_change_back.setOnClickListener(this);
        email_change_btn.setOnClickListener(this);
        email_change_sendchange.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        final String email = email_change_number.getText().toString();
        final String code = email_change_code.getText().toString();
        final String new_password = email_change_newpassword.getText().toString();
        final String again_new_password = email_change_againnewpassword.getText().toString();

        switch (view.getId()) {
            case R.id.change_pass_word_get_code_bt:// 获取验证码按钮
                boolean isTel = true; // 标记位：true-是手机号码；false-不是手机号码
                if (email_change_number.getText().toString().length() == 11) {
                    for (int i = 0; i < email_change_number.getText().toString().length(); i++) {
                        char c = email_change_number.getText().toString().charAt(i);
                        if (!Character.isDigit(c)) {
                            isTel = false;
                            break; // 只要有一位不符合要求退出循环
                        }
                    }
                } else {
                    isTel = false;
                }
            /* 只有用户名、密码不为空，并且用户名为11位手机号码才允许登陆 */
                if (TextUtils.isEmpty(email_change_number.getText())) {
                    email_change_number.setShakeAnimation();
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
                                    email_change_btn.setClickable(false);
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
                                    ToastUtil.t(getApplicationContext(), "电话号码已经被注册！！");
                                    Log.e("返回信息", "电话号码已被注册");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    Log.e("电话号码", email.trim());
                    map.put("user_account", email.trim());
                    new HttpImplStringTest(map, "GetChangePasswordCode.xml", h3, "POST").start();
                }
                break;

            case R.id.change_pass_word_send_change:// 发送按钮
                Log.e("按钮", "fanhui ");
                /* 只有用户名、密码不为空，并且用户名为11位手机号码才允许登陆 */
                if (TextUtils.isEmpty(email_change_number.getText())) {
                    email_change_number.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email_change_code.getText())) {
                    email_change_code.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email_change_newpassword.getText())) {
                    email_change_newpassword.setShakeAnimation();
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (!new_password.equals(again_new_password)) {
                    email_change_newpassword.setShakeAnimation();
                    email_change_againnewpassword.setShakeAnimation();
                    Toast.makeText(this, "两次输入不一致", Toast.LENGTH_SHORT).show();
                } else {
                    // System.out.println("——————————输入验证码和密码，完成注册——————————");
                    Handler h4 = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 0x123) {
                                //11成功 10电话呗注册了  12注册失败没有具体原因  126 验证码错误
                                int result = JSON.parseObject(msg.obj.toString()).getInteger("result");
                                Log.e("返回信息2", msg.obj.toString());
                                if (result == 11) {
                                    ToastUtil.t(ChangePassWordActivity.this, "恭喜你，注册成功！");
                                    //传送电话号码和用户ID到下一个界面
                                    Bundle bundle = intent.getExtras();
                                    bundle.putString("telephone", email.trim());
                                    bundle.putString("password", new_password.trim());
                                    intent.putExtras(bundle);
                                    intent.setClass(ChangePassWordActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else if (result == 10) {
                                    ToastUtil.t(ChangePassWordActivity.this, "电话被注册了");
                                } else if (result == 12) {
                                    ToastUtil.t(ChangePassWordActivity.this, "网络链接失败！");
                                } else if (result == 126) {
                                    ToastUtil.t(ChangePassWordActivity.this, "验证码错误");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_account", email.trim());
                    map.put("newpassword", new_password);
                    map.put("code", code);
                    LogUtils.e("map", map.toString());
                    new HttpImplStringTest(map, "ChangePad_T_E.xml", h4, "POST").start();
                }

                break;
            case R.id.forget_pass_word_back:
                finish();
                break;

        }

    }
}
