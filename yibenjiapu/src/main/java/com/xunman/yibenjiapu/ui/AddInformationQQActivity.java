package com.xunman.yibenjiapu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.ReturnAddInfo;
import com.xunman.yibenjiapu.bean.Surname_Info;
import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.IDCardValidate;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.StringUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述   添加QQ登陆用户基本信息
 */
public class AddInformationQQActivity extends AppCompatActivity implements View.OnClickListener {

    private DeletableEditText add_firstname_add, add_lastname_add, add_idcard_add, add_age_add;
    private DeletableEditText add_native_add, add_palce_add, regist_phone, add_email_add, password, passwordAgain, regist_code;
    /**
     * 性别
     */
    private RadioGroup add_ll_sex;
    /**
     * 发送按钮
     */
    private Button add_send;
    /**
     * 获取验证码按钮
     */
    private TextView regist_get;
    /**
     * 性别选项框
     */
    private RadioButton check;
    /**
     * 性别得出的值
     */
    private String s;
    /**
     * 获取验证码按钮倒计时
     */
    int time = 10;
    /**
     * 保存的用户服务器返回的用户id
     */
    private boolean id_id;
    private boolean send_b = true;
    private Intent intent;
    //接收传送过来的数据  电话号码的下标是 telephone
    private Bundle bundle;
    private String sur;//姓氏
    private String name;//名字
    private String iDcard;//身份证号码
    private int age;//年龄
    private String origin;//籍贯
    private String place;//常住地
    private String telephone;//电话号码
    private String email;//邮箱
    private String password1;//密码
    private String repeatingDate;//重复的数据
    ReturnAddInfo returnAddInfo;//返回的重复信息实体类

    //QQ登陆唯一标识
    private String openID;

    private String _class;
    private Bundle extras;

    private final static String LENGTH_ERROR="身份证长度必须为15或者18位！";
    private final static String NUMBER_ERROR="15位身份证都应该为数字，18位身份证都应该前17位应该都为数字！";
    private final static String DATE_ERROR="身份证日期验证无效！";
    private final static String AREA_ERROR="身份证地区编码错误!";
    private final static String CHECKCODE_ERROR="身份证最后一位校验码有误！";

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

        setContentView(R.layout.activity_add_information_qq);
        intiview();
        add_ll_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                check = (RadioButton) findViewById(add_ll_sex.getCheckedRadioButtonId());
                s = check.getText().toString();
            }
        });
        BaseApplication.list.add(this);
        intent = getIntent();
        extras = intent.getExtras();
        openID = extras.getString("openID");
        _class = extras.getString("_class");
    }

    /**
     * 找到控件
     */
    private void intiview() {
        intent = getIntent();
        bundle = intent.getExtras();
        //密码
        password = (DeletableEditText) findViewById(R.id.add_password_add);
        //验证密码
        passwordAgain = (DeletableEditText) findViewById(R.id.add_password_add_again);
        //姓
        add_firstname_add = (DeletableEditText) findViewById(R.id.add_firstname_add);
        //名
        add_lastname_add = (DeletableEditText) findViewById(R.id.add_lastname_add);
        //身份证
        add_idcard_add = (DeletableEditText) findViewById(R.id.add_idcard_add);
        //年龄
        add_age_add = (DeletableEditText) findViewById(R.id.add_age_add);
        //籍贯
        add_native_add = (DeletableEditText) findViewById(R.id.add_native_add);
        //常住地
        add_palce_add = (DeletableEditText) findViewById(R.id.add_palce_add);
        //邮箱
        add_email_add = (DeletableEditText) findViewById(R.id.add_email_add);
        //性别
        add_ll_sex = (RadioGroup) findViewById(R.id.add_ll_sex);
        //提交
        add_send = (Button) findViewById(R.id.add_send);
        add_send.setOnClickListener(this);
        //电话
        regist_phone = (DeletableEditText) findViewById(R.id.regist_phone);
        //只能输入电话
        regist_phone.setInputType(InputType.TYPE_CLASS_PHONE);
        //验证码
        regist_code = (DeletableEditText) findViewById(R.id.regist_code);
        //获取验证码按钮
        regist_get = (TextView) findViewById(R.id.regist_get);
        regist_get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //获取输入的电话和验证码
        final String phone = regist_phone.getText().toString();
        final String code = regist_code.getText().toString();
        switch (v.getId()) {
            case R.id.regist_get://获取验证码按钮
                // 获取验证码
                LogUtils.e("AddInformationQQActivity", "获取验证码");
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
                                Log.e("返回信息", msg.obj.toString());
                                int result = JSON.parseObject(msg.obj.toString()).getInteger("result");
                                if (result == 11) {
                                    Log.e("返回信息", "发送验证码成功");
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
                                } else if (result == 127) {
                                    Log.e("返回信息", "发送验证码失败，重新获取");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    Log.e("电话号码", phone.trim());
                    map.put("telephone", phone.trim());
                    new HttpImplStringTest(map, "GetSignUpCode.xml", h3, "POST").start();
                }
                break;
            case R.id.add_send:
                 /* 只有用户名、密码不为空，并且用户名为11位手机号码才允许注册 */
                if (TextUtils.isEmpty(regist_phone.getText())) {
                    regist_phone.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(regist_code.getText())) {
                    regist_code.setShakeAnimation();
                    Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
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
                                    //补全信息
                                    send_send(add_send);
                                   // AddInfo addInfo=new AddInfo();
                                   // new AddInfoDao(addInfo).HttpOnline();
                                    LogUtils.e("返回信息2", "提交成功");
                                    ToastUtil.t(AddInformationQQActivity.this, "恭喜你，提交成功！");
                                    intent.setClass(AddInformationQQActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else if (result == 127)
                                    ToastUtil.t(AddInformationQQActivity.this, "电话被注册了");
                                else if (result == 12)
                                    ToastUtil.t(AddInformationQQActivity.this, "网络链接失败！");
                                else if (result == 126) {
                                    ToastUtil.t(AddInformationQQActivity.this, "验证码错误");
                                }
                            }
                        }
                    };
                    Map<String, Object> map = new HashMap<>();
                    map.put("telephone", phone.trim());
                    map.put("code", code);
                    map.put("personal", openID);
                    map.put("_class", "qq");
                    LogUtils.e("map", map.toString());
                    new HttpImplStringTest(map, "SignUp.xml", h4, "POST").start();
                }
                break;
        }
    }

    private Surname_Info sum;

    /**
     * 打包用户输入的数据传送给服务端
     */
    public void send_send(View v){
        ToastUtil.t(this, "该功能正在完善中，尽请期待..");
    }
//    /**
//     * 打包用户输入的数据传送给服务端
//     */
//    public void send_send(View v) {
//        add_send.setClickable(false);
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(5000);
//                    add_send.setClickable(true);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        /* 只有用户填写的所有输入框不为空时才允许提交 */
//        if (TextUtils.isEmpty(password.getText())) {
//            password.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(passwordAgain.getText())) {
//            passwordAgain.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请再次输入密码", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_firstname_add.getText())) {
//            add_firstname_add.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入姓", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_lastname_add.getText())) {
//            add_lastname_add.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入名", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_idcard_add.getText())) {
//            add_idcard_add.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入身份证号", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_age_add.getText())) {
//            add_age_add.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入年龄", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_native_add.getText())) {
//            Toast.makeText(getApplicationContext(), "请选择籍贯", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_palce_add.getText())) {
//            add_palce_add.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入常住地", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(add_email_add.getText())) {
//            add_email_add.setShakeAnimation();
//            Toast.makeText(getApplicationContext(), "请输入邮箱", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(s)) {
//            Toast.makeText(getApplicationContext(), "请选择性别", Toast.LENGTH_SHORT).show();
//        } else {
//            Handler h = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    if (msg.what == 0x123) {
//                        add_send.setClickable(true);
//                        LogUtils.e("返回信息", msg.obj.toString());
//                        JSONObject JSONobj = JSON.parseObject(msg.obj.toString());
//                        int result = JSONobj.getInteger("result");
//                        if (result == 11) {
//                            LogUtils.e("返回信息", "发送成功，数据已经存储");
//                            ToastUtil.t(AddInformationQQActivity.this, "发送成功，数据已经存储");
//
//                            sum = JSON.toJavaObject(JSONobj.getJSONObject("surname_info"), Surname_Info.class);
//                            bundle.putSerializable("sum", sum);
//                            intent.setClass(AddInformationQQActivity.this, MainActivity.class);
//                            intent.putExtras(bundle);
//                            ShareUtils.putBoolean(AddInformationQQActivity.this, "login", true);//设置登陆成功标志
//                            ToastUtil.t(AddInformationQQActivity.this, "登陆成功！");
//                            //添加信息到本地share存储
//                            addToShare();
//                            startActivity(intent);
//                        } else if (result == 127) {
//                            LogUtils.e("返回信息", "提交失败");
//                            ToastUtil.t(AddInformationQQActivity.this, "提交失败");
//                        } else if (result == 111) {
//                            returnAddInfo = JSON.toJavaObject(JSONobj.getJSONObject("info"), ReturnAddInfo.class);
//                            LogUtils.e("返回信息", "数据已经存在,请确认并提交");
//                            ToastUtil.t(AddInformationQQActivity.this, "数据已经存在,请确认并修改后再次提交");
//                            repeatingDate = msg.obj.toString();
//                            LogUtils.e("返回信息+++msg", repeatingDate);
//                            //检查从服务器返回的的重复数据
//                            checkRepeat();
//                            //确认修改设置按钮
//                            send_b = false;
//                            ToastUtil.t(AddInformationQQActivity.this, "修改成功");
//                        }
//                    }
//                }
//            };
//            if (send_b) {
//                password1 = passwordAgain.getText().toString().trim();
//                iDcard = add_idcard_add.getText().toString().trim();
//                telephone = regist_phone.getText().toString().trim();
//                email = add_email_add.getText().toString().trim();
//                //身份证号验证返回信息
//                String iDCardValidateReturn = IDCardValidate.validate_effective(iDcard, true);
//                if (!StringUtils.isValidEmail(email)) {     //验证邮箱格式
//                    add_email_add.setShakeAnimation();
//                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
//                } else if (iDCardValidateReturn.equals(LENGTH_ERROR)) {     //验证身份证号
//                    add_idcard_add.setShakeAnimation();
//                    Toast.makeText(this, "身份证长度必须为15或者18位！", Toast.LENGTH_SHORT).show();
//                } else if (iDCardValidateReturn.equals(NUMBER_ERROR)) {
//                    add_idcard_add.setShakeAnimation();
//                    Toast.makeText(this, "15位身份证都应该为数字，18位身份证都应该前17位应该都为数字！", Toast.LENGTH_SHORT).show();
//                } else if (iDCardValidateReturn.equals(DATE_ERROR)) {
//                    add_idcard_add.setShakeAnimation();
//                    Toast.makeText(this, "身份证日期验证无效！", Toast.LENGTH_SHORT).show();
//                } else if (iDCardValidateReturn.equals(AREA_ERROR)) {
//                    add_idcard_add.setShakeAnimation();
//                    Toast.makeText(this, "身份证地区编码错误!", Toast.LENGTH_SHORT).show();
//                } else if (iDCardValidateReturn.equals(CHECKCODE_ERROR)) {
//                    add_idcard_add.setShakeAnimation();
//                    Toast.makeText(this, "身份证最后一位校验码有误！", Toast.LENGTH_SHORT).show();
//                } else if (!password1.equals(password.getText().toString().trim())) {
//                    passwordAgain.setShakeAnimation();
//                    Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
//                } else {
//                    Map<String, Object> map = contentMap();
//
//                    map.put("password", password1);
//                    map.put("telephone", telephone);
//                    //传用户账号ID
//                    map.put("setupid", bundle.getInt("setupid"));
//                    Log.e("map", "map====" + JSON.toJSONString(map));
//                    new HttpImplStringTest(map, "AddInfo.xml", h, "GET").start();
//                }
//            } else {
//                Map<String, Object> map = contentMap();
//                map.put("ID", returnAddInfo.getID());
//                LogUtils.e("返回再次连接", "UpdateFamilyInfo.xml");
//                new HttpImplStringTest(map, "UpdateFamilyInfo.xml", h, "GET").start();
//            }
//        }
//    }

    //检查从服务器返回的的重复数据
    private void checkRepeat() {
        //姓
        if (!sur.equals(sur = returnAddInfo.getSur()))
            add_firstname_add.setText(sur);
        else {
            add_firstname_add.setFocusable(false);
            add_firstname_add.setEnabled(false);
        }
        //名
        if (!name.equals(name = returnAddInfo.getName()))
            add_lastname_add.setText(sur);
        else {
            add_lastname_add.setFocusable(false);
            add_lastname_add.setEnabled(false);
        }
        //身份证
        if (!iDcard.equals(iDcard = returnAddInfo.getCard()))
            add_idcard_add.setText(iDcard);
        else {
            add_idcard_add.setFocusable(false);
            add_idcard_add.setEnabled(false);
        }
        //年龄
        if (age != (age = returnAddInfo.getAge()))
            add_age_add.setText(age);
        else {
            add_age_add.setFocusable(false);
            add_age_add.setEnabled(false);
        }
        //籍贯
        if (!origin.equals(origin = returnAddInfo.getOrigin()))
            add_native_add.setText(origin);
        else {
            add_native_add.setFocusable(false);
            add_native_add.setEnabled(false);
        }
        //籍贯
        if (!place.equals(place = returnAddInfo.getPlace()))
            add_palce_add.setText(place);
        else {
            add_palce_add.setFocusable(false);
            add_palce_add.setEnabled(false);
        }
        //电话
        if (!telephone.equals(telephone = returnAddInfo.getTelephone()))
            regist_phone.setText(telephone);
        else {
            regist_phone.setFocusable(false);
            regist_phone.setEnabled(false);
        }
        //邮箱
        if (!email.equals(email = returnAddInfo.getEmail()))
            add_email_add.setText(sur);
        else {
            add_email_add.setFocusable(false);
            add_email_add.setEnabled(false);
        }
        //隐藏密码输入框
        passwordAgain.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
//        //改变按钮文字内容
//        add_p1_add.setVisibility(View.GONE);
//        add_p_add.setVisibility(View.GONE);
    }

    //提交的map集合
    private Map<String, Object> contentMap() {
        Map<String, Object> map = new HashMap<>();
        sur = add_firstname_add.getText().toString().trim();
        map.put("sur", sur);
        name = add_lastname_add.getText().toString().trim();
        map.put("name", name);
        iDcard = add_idcard_add.getText().toString().trim();
        map.put("card", iDcard);
        map.put("sex", s);
        age = Integer.valueOf(add_age_add.getText().toString().trim());
        map.put("age", age);
        origin = add_native_add.getText().toString().trim();
        map.put("origin", origin);
        place = add_palce_add.getText().toString().trim();
        map.put("place", place);
        telephone = regist_phone.getText().toString().trim();
        map.put("telephone", telephone);
        email = add_email_add.getText().toString().trim();
        map.put("email", email);
        return map;
    }

    //添加信息到本地share存储
    private void addToShare() {
        ShareUtils.putString(this, "sur", sur);
        ShareUtils.putString(this, "name", name);
        ShareUtils.putString(this, "iDcard", iDcard);
        ShareUtils.putInt(this, "age", age);
        ShareUtils.putString(this, "origin", origin);
        ShareUtils.putString(this, "place", place);
        ShareUtils.putString(this, "telephone", telephone);
        ShareUtils.putString(this, "email", email);
        ShareUtils.putString(this, "password", password1);
        ShareUtils.putBoolean(this, "isAdd", true);
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
