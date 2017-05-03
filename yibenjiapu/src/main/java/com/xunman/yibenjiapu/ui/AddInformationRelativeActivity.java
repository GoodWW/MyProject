package com.xunman.yibenjiapu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.ReturnAddInfo;
import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.StringUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述   添加亲戚基本信息
 */
public class AddInformationRelativeActivity extends AppCompatActivity implements View.OnClickListener{

    private DeletableEditText add_firstname_add, add_lastname_add, add_idcard_add, add_age_add;
    private DeletableEditText add_palce_add, add_phone_add, add_email_add, add_birthday_add;
    private TextView add_native_add;//籍贯
    private int requestCode=101;//请求码
    /**
     * 返回键
     */
    private TextView tvAddRelativeBack;
    /**
     * 性别
     */
    private RadioGroup add_ll_sex;
    /**
     * 发送按钮
     */
    private Button add_send;
    /**
     * 性别宣向框
     */
    private RadioButton check;
    /**
     * 性别得出的值
     */
    private String s;
    /**
     * 保存的用户服务器返回的用户id
     */
    private boolean id_id;
    private boolean send_b = true;
    /**
     * 关系下拉框
     */
    private Spinner spRelation;

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
    private String birthday;//生日
    private String relation;//关系
    private String repeatingDate;//重复的数据
    private ReturnAddInfo returnAddInfo;//返回的重复信息实体类
    private String strSex;//用户性别
    private int id;//用户信息id
    private int branch_id;//用户分支id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information_relative);
        intiview();
        add_ll_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                check = (RadioButton) findViewById(add_ll_sex.getCheckedRadioButtonId());
                s = check.getText().toString();
            }
        });
        add_native_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(AddInformationRelativeActivity.this,ShowRegionActivity.class);
                intent.putExtra("address","address");
                startActivityForResult(intent,requestCode);
            }
        });
        BaseApplication.list.add(this);
    }
    /**
     * 将返回数据给tv
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode==102){
            add_native_add.setText(data.getStringExtra("address"));
        }
    }

    /**
     * 找到控件
     */
    private void intiview() {
        intent = getIntent();
        bundle = intent.getExtras();

        //获取传递过来的用户信息
        strSex = bundle.getString("sex");
        id = bundle.getInt("id");
        branch_id = bundle.getInt("branch_id");

        /**姓*/
        add_firstname_add = (DeletableEditText) findViewById(R.id.add_firstname_add);
        /**名*/add_lastname_add = (DeletableEditText) findViewById(R.id.add_lastname_add);
        /**身份证*/add_idcard_add = (DeletableEditText) findViewById(R.id.add_idcard_add);
        /**年龄*/add_age_add = (DeletableEditText) findViewById(R.id.add_age_add);
        /**籍贯*/add_native_add = (TextView) findViewById(R.id.add_native_add);
        /**常住地*/add_palce_add = (DeletableEditText) findViewById(R.id.add_palce_add);
        /**电话*/add_phone_add = (DeletableEditText) findViewById(R.id.add_phone_add);
        /**邮箱*/add_email_add = (DeletableEditText) findViewById(R.id.add_email_add);
        /**性别*/add_ll_sex = (RadioGroup) findViewById(R.id.add_ll_sex);
        /**生日*/add_birthday_add = (DeletableEditText) findViewById(R.id.add_birthday_add);
        add_send = (Button) findViewById(R.id.add_send);
        /**关系*/spRelation = (Spinner) findViewById(R.id.sp_relation);
        spRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                relation = AddInformationRelativeActivity.this.getResources().getStringArray(R.array.relation)[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        tvAddRelativeBack = (TextView) findViewById(R.id.activity_add_relative_back);
        tvAddRelativeBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_add_relative_back:
                finish();
                break;
        }
    }

//    /**
//     * 打包用户输入的数据传送给服务端
//     */
//    public void send_send(View v){
//        ToastUtil.t(this, "该功能正在完善中，尽请期待..");
//    }

    /**
     * 打包用户输入的数据传送给服务端
     */
    public void send_send(View v) {
        add_send.setClickable(false);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    add_send.setClickable(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        /* 只有用户填写的所有输入框不为空时才允许提交 */
        if (TextUtils.isEmpty(add_firstname_add.getText())) {
            add_firstname_add.setShakeAnimation();
            Toast.makeText(getApplicationContext(), "请输入姓", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add_lastname_add.getText())) {
            add_lastname_add.setShakeAnimation();
            Toast.makeText(getApplicationContext(), "请输入名", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add_idcard_add.getText())) {
            add_idcard_add.setShakeAnimation();
            Toast.makeText(getApplicationContext(), "请输入身份证号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add_age_add.getText())) {
            add_age_add.setShakeAnimation();
            Toast.makeText(getApplicationContext(), "请输入年龄", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add_native_add.getText())) {
            Toast.makeText(getApplicationContext(), "请选择籍贯", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add_palce_add.getText())) {
            add_palce_add.setShakeAnimation();
            Toast.makeText(getApplicationContext(), "请输入常住地", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add_email_add.getText())) {
            add_email_add.setShakeAnimation();
            Toast.makeText(getApplicationContext(), "请输入邮箱", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(s)) {
            Toast.makeText(getApplicationContext(), "请选择性别", Toast.LENGTH_SHORT).show();
        } else {
            Handler h = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        add_send.setClickable(true);
                        LogUtils.e("添加亲戚信息返回信息", msg.obj.toString());
                        JSONObject JSONobj = JSON.parseObject(msg.obj.toString());
                        int result = JSONobj.getInteger("result");
                        if (result == 11) {
                            LogUtils.e("返回信息", "发送成功，数据已经存储");
                            ToastUtil.t(AddInformationRelativeActivity.this, "发送成功，数据已经存储");
                        } else if (result == 127) {
                            LogUtils.e("返回信息", "提交失败");
                            ToastUtil.t(AddInformationRelativeActivity.this, "提交失败");
                        } else if (result == 111) {
                            returnAddInfo = JSON.toJavaObject(JSONobj.getJSONObject("info"), ReturnAddInfo.class);
                            LogUtils.e("返回信息", "数据已经存在,请确认并提交");
                            ToastUtil.t(AddInformationRelativeActivity.this, "数据已经存在,请确认并修改后再次提交");
                            repeatingDate = msg.obj.toString();
                            LogUtils.e("返回信息+++msg", repeatingDate);
                            //检查从服务器返回的的重复数据
                            checkRepeat();
                            //确认修改设置按钮
                            send_b = false;
                            ToastUtil.t(AddInformationRelativeActivity.this, "修改成功");
                        }
                    }
                }
            };
            if (send_b) {
                email = add_email_add.getText().toString().trim();
                telephone = add_phone_add.getText().toString().trim();

                if (!StringUtils.isPhoneNumberValid(telephone)) {       //判断电话号码是否有效
                    add_phone_add.setShakeAnimation();
                    Toast.makeText(this, "电话号码无效", Toast.LENGTH_SHORT).show();
                } else if (!StringUtils.isValidEmail(email)) {     //验证邮箱格式
                    add_email_add.setShakeAnimation();
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> map = contentMap();
                    map.put("id", id);
                    map.put("branch_id", branch_id);
                    map.put("relation", relation);
                    map.put("usersex", strSex);
                    Log.e("map", "map====" + JSON.toJSONString(map));
                    new HttpImplStringTest(map, "AddFamilyInfo.xml", h, "POST ").start();
                }
            } else {
                Map<String, Object> map = contentMap();
                map.put("ID", returnAddInfo.getID());
                LogUtils.e("返回再次连接", "UpdateFamilyInfo.xml");
                new HttpImplStringTest(map, "UpdateFamilyInfo.xml", h, "GET").start();
            }
        }
    }

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
        //年龄、、
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
            add_phone_add.setText(telephone);
        else {
            add_phone_add.setFocusable(false);
            add_phone_add.setEnabled(false);
        }
        //邮箱
        if (!email.equals(email = returnAddInfo.getEmail()))
            add_email_add.setText(sur);
        else {
            add_email_add.setFocusable(false);
            add_email_add.setEnabled(false);
        }
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
        telephone = add_phone_add.getText().toString().trim();
        map.put("telephone", telephone);
        email = add_email_add.getText().toString().trim();
        map.put("email", email);
        birthday = add_birthday_add.getText().toString().trim();
        map.put("birthday", birthday);
        return map;
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
            int[] l = { 0, 0 };
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
