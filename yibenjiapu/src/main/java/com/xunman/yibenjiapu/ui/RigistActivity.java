package com.xunman.yibenjiapu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

public class RigistActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 电话号码
     */
    private DeletableEditText regist_phone;
    /**
     * 验证码
     */
    private DeletableEditText regist_code;
    /**
     * 立即注册按钮
     */
    private TextView regist_button_regist;
    /**
     * 返回按钮
     */
    private TextView regist_back;

    /**
     * 发送验证码按钮
     */
    private TextView regist_get;
    String tag = "RegistActivity";
    //获取验证码按钮倒计时
    int time = 10;

    private Object ob;

    private ShareUtils sfUtils;
    private static int idCode = 1;
    private Intent intent;
    private Bundle bundle;

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
                        regist_get.setText("免费获取");
                        regist_get.setClickable(true);
                        time = 10;
                    } else
                        regist_get.setText("(" + (time--) + "s)");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigist);
        intView();
        inti();
        BaseApplication.list.add(this);
    }


    private void intView() {

        intent = getIntent();
        bundle = intent.getExtras();
//        sfUtils = new ShareUtils(this);
        regist_phone = (DeletableEditText) findViewById(R.id.regist_phone);
        // 只能输入电话
        regist_phone.setInputType(InputType.TYPE_CLASS_PHONE);
        regist_code = (DeletableEditText) findViewById(R.id.regist_code);
        regist_get = (TextView) findViewById(R.id.regist_get);
        regist_button_regist = (TextView) findViewById(R.id.regist_button_regist);
        regist_back = (TextView) findViewById(R.id.regist_back);
    }

    private void inti() {
        regist_get.setOnClickListener(this);
        regist_button_regist.setOnClickListener(this);
        regist_back.setOnClickListener(this);
    }
    private ProgressDialog dialog;
    @Override
    public void onClick(View view) {

        final String phone = regist_phone.getText().toString();
        final String code = regist_code.getText().toString();
        switch (view.getId()) {
            case R.id.regist_get://获取验证码
                boolean isTel = true; // 标记位：true-是手机号码；false-不是手机号码
                if (regist_phone.getText().toString().length() == 11) {
                    for (int i = 0; i < regist_phone.getText().toString().length(); i++) {
                        char c = regist_phone.getText().toString().charAt(i);
                        if (!Character.isDigit(c)) {
                            isTel = false;
                            break; // 只要有一位不符合要求退出循环
                        }
                    }
                } else {
                    isTel = false;
                }
            /* 只有用户名、密码不为空，并且用户名为11位手机号码才允许登陆 */
                if (TextUtils.isEmpty(regist_phone.getText())) {
                    regist_phone.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                } else if (!isTel) {
                    Toast.makeText(getApplicationContext(), "请输入11位手机号码！", Toast.LENGTH_SHORT).show();
                } else {
                    //获取验证码返回的信息，并通知更新UI
                    Handler h3 = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 0x123) {
                                int result = JSON.parseObject(msg.obj.toString()).getInteger("result");
                                if (result == 11) {
                                    regist_get.setClickable(false);
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
                                } else if (result == 13) {
                                    ToastUtil.t(getApplicationContext(), "电话号码不能为空！");
                                } else if (result == 10) {
                                    ToastUtil.t(getApplicationContext(), "电话号码已经被注册！");
                                } else if(result == 14){
                                    ToastUtil.t(getApplicationContext(), "请输入正确电话号码！");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    map.put("telephone", phone.trim());
                    new HttpImplStringTest(map, "GetSignUpCode.xml", h3, "POST").start();
                }
                break;
            case R.id.regist_button_regist:
                /* 只有用户名、密码不为空，并且用户名为11位手机号码才允许注册 */
                if (TextUtils.isEmpty(regist_phone.getText())) {
                    regist_phone.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regist_code.getText())) {
                    regist_code.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                } else {
                    //设置一个progressdialog的弹窗
                    dialog = ProgressDialog.show(RigistActivity.this, null, "正在注册，请稍候...", true, false);
                    Handler h4 = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 0x123) {
                                //11成功 10电话呗注册了  12注册失败没有具体原因  126 验证码错误
                                int result = JSON.parseObject(msg.obj.toString()).getInteger("result");
                                if (result == 11) {
                                    //LogUtils.e("返回信息2", "注册成功");
                                    int setupid = JSON.parseObject(msg.obj.toString()).getInteger("setupid");
                                    ToastUtil.t(RigistActivity.this, "恭喜你，注册成功！");
                                    //传送电话号码和用户ID到下一个界面
                                    Bundle bundle = intent.getExtras();
                                    bundle.putString("telephone", phone.trim());
                                    bundle.putInt("setupid", setupid);
                                    intent.putExtras(bundle);
                                    intent.setClass(RigistActivity.this, AddInformationActivity.class);
                                    startActivity(intent);
                                    if (dialog.isShowing()){
                                        dialog.dismiss();
                                    }
                                    finish();
                                } else if (result == 127) {
                                    if (dialog.isShowing()){
                                        dialog.dismiss();
                                    }
                                    ToastUtil.t(RigistActivity.this, "电话被注册了！");
                                } else if (result == 12) {
                                    if (dialog.isShowing()){
                                        dialog.dismiss();
                                    }
                                    ToastUtil.t(RigistActivity.this, "网络链接失败！");
                                } else if (result == 126) {
                                    if (dialog.isShowing()){
                                        dialog.dismiss();
                                    }
                                    ToastUtil.t(RigistActivity.this, "验证码错误！");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    map.put("telephone", phone.trim());
                    map.put("code", code);
                    //以下两key值占位效果，不能缺少，
                    map.put("personal", "");
                    map.put("_class", "");
                    new HttpImplStringTest(map, "SignUp.xml", h4, "POST").start();
                }
                break;
            case R.id.regist_back:
                // 返回按钮
                finish();
                break;
        }
    }

    // -------------------------------------隐藏输入法-----------------------------------------------------
    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
