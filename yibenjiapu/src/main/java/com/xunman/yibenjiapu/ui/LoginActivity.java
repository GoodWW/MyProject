package com.xunman.yibenjiapu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.Account_Info;
import com.xunman.yibenjiapu.bean.Surname_Info;
import com.xunman.yibenjiapu.bean.User_Info;
import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.utils.BitmapUtils;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * QQ登陆
     */
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1106011644";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private Account_Info loginContent;
    // QQ登陆唯一标识openID
    public static String openidString;
    // QQ头像
    private String qqAvatarUrl;
    private Bitmap qqAvatar;
    private String qqAvatarByte;
    // QQ昵称
    private String qqUsername;

    private ImageView acc_login_login, acc_login_qq, acc_login_weixin, acc_login_weibo;
    private DeletableEditText acc_login_account, acc_login_password;
    private TextView acc_login_rigist, acc_login_forgetpassword, acc_login_back;
    private Intent intent;
    private Bundle bundle;
    private String phone;//保存电话
    private String password;//保存密码
    //    private LoginBack loginBack ;//登陆返回的数据包装类 把本姓氏的数据传过去
//    private SurnameList1.ContentsBean contentsBean = null;
    private Surname_Info sum;
    private User_Info info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiView();
        if (phone != null && password != null) {
            acc_login_account.setText(phone);
            acc_login_password.setText(password);
        }
        BaseApplication.list.add(this);
        BaseApplication.list1.add(this);
    }

    //获得是否添加基本信息状态
    public Boolean getAdd() {
        Boolean isAdd = ShareUtils.getBoolean(this, "isAdd", false);
        return isAdd;
    }

    //获得是否是登录状态
    public Boolean getLogin() {
        boolean isLogin = ShareUtils.getBoolean(this, "login", false);
        return isLogin;
    }

    private void intiView() {
        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            phone = bundle.getString("telephone");
            password = bundle.getString("password");
        }
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
//        acc_login_im = (ImageView) findViewById(R.id.acc_login_im);
        acc_login_login = (ImageView) findViewById(R.id.acc_login_login);
        acc_login_qq = (ImageView) findViewById(R.id.acc_login_qq);
        acc_login_weixin = (ImageView) findViewById(R.id.acc_login_weixin);
        acc_login_weibo = (ImageView) findViewById(R.id.acc_login_weibo);

        acc_login_account = (DeletableEditText) findViewById(R.id.acc_login_account);
        acc_login_password = (DeletableEditText) findViewById(R.id.acc_login_password);
        //新用户注册按钮
        acc_login_rigist = (TextView) findViewById(R.id.acc_login_rigist);
        acc_login_back = (TextView) findViewById(R.id.acc_login_back);
        acc_login_forgetpassword = (TextView) findViewById(R.id.acc_login_forget_password);

//        acc_login_im.setOnClickListener(this);
        acc_login_login.setOnClickListener(this);
        acc_login_weixin.setOnClickListener(this);
        acc_login_qq.setOnClickListener(this);
        acc_login_weibo.setOnClickListener(this);
        acc_login_back.setOnClickListener(this);

        acc_login_rigist.setOnClickListener(this);
        acc_login_forgetpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acc_login_back:
                finish();
                break;
//            case R.id.acc_login_im://头像图片
//                Toast.makeText(this, "请先登陆", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.acc_login_login://登陆按钮

                LogUtils.e(TAG, "点击了登陆按钮");

                boolean isTel = true; // 标记位：true-是手机号码；false-不是手机号码
                /**
                 * 判断输入的用户名是否是电话号码
                 */
                if (acc_login_account.getText().toString().length() == 11) {
                    for (int i = 0; i < acc_login_account.getText().toString().length(); i++) {
                        char c = acc_login_account.getText().toString().charAt(i);
                        if (!Character.isDigit(c)) {
                            isTel = false;
                            break; // 只要有一位不符合要求退出循环
                        }
                    }
                } else {
                    isTel = false;
                }

                if (TextUtils.isEmpty(acc_login_account.getText())) {
                    acc_login_account.setShakeAnimation();
                    ToastUtil.t(LoginActivity.this, "请输入您的账号");
                } else if (!isTel) {
                    ToastUtil.t(LoginActivity.this, "请输入11位手机号码");
                } else if (TextUtils.isEmpty(acc_login_password.getText())) {
                    acc_login_password.setShakeAnimation();
                    ToastUtil.t(LoginActivity.this, "请输入密码");
                } else {
                    //链接服务器并获得返回信息
                    linkServerAndBack();
                }
                break;
            case R.id.acc_login_rigist://注册账号
                intent.setClass(this, RigistActivity.class);
                startActivity(intent);
                break;
            case R.id.acc_login_forget_password://忘记密码
                intent.setClass(this, ForgetPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.acc_login_qq:
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this, "all", mIUiListener);
                break;
            case R.id.acc_login_weixin:
                break;
            case R.id.acc_login_weibo:
                break;
        }
    }

    String sur;
    String name;
    String telephone;
    String gender;
    String familyAddress;
    private ProgressDialog dialog;

    //登陆   链接服务器并获得返回信息
    private void linkServerAndBack() {
        //设置一个progressdialog的弹窗
        dialog = ProgressDialog.show(LoginActivity.this, null, "正在登陆，请稍候...", true, false);
        Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String info1 = msg.obj.toString();
                    ShareUtils.putString(LoginActivity.this, "info", info1);
                    LogUtils.e("返回信息___登陆", info1);
                    JSONObject JSONobj = JSON.parseObject(info1);
//                    String surname_info = JSONobj.getString("surname_info");
//                    LogUtils.e("返回信息___登陆", surname_info);
//                    ShareUtils.putString(LoginActivity.this, "surname_info", surname_info);
                    int result = JSONobj.getInteger("result");
                    if (result == 11) {
                        LogUtils.e("返回信息___登陆", "成功");
                        sum = JSON.toJavaObject(JSONobj.getJSONObject("surname_info"), Surname_Info.class);
                        info = JSON.toJavaObject(JSONobj.getJSONObject("user_info"), User_Info.class);
                        loginContent = JSON.toJavaObject(JSONobj.getJSONObject("account_info"), Account_Info.class);
                        if(sum.getId() == 0){
                            int setupid = loginContent.getId();
                            Bundle bundle = intent.getExtras();
                            bundle.putString("telephone", acc_login_account.getText().toString().trim());
                            bundle.putInt("setupid", setupid);
                            intent.putExtras(bundle);
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                            ToastUtil.t(LoginActivity.this, "请先添加基本信息！");
                            intent.setClass(LoginActivity.this, AddInformationActivity.class);
                            startActivity(intent);
                        }else{
                            String headUrl = loginContent.getHead_url();
                            LogUtils.e("555555555555555555",headUrl);
                            //保存电话、性别、家族地址，我的信息页面需要用
                            telephone = info.getTelephone();
                            gender = info.getSex();
                            familyAddress = info.getPlace();
                            ShareUtils.putString(LoginActivity.this, "telephone", telephone);
                            ShareUtils.putString(LoginActivity.this, "gender", gender);
                            ShareUtils.putString(LoginActivity.this, "familyAddress", familyAddress);
                            ShareUtils.putString(LoginActivity.this, "headUrl", headUrl);
                            sur = info.getSur();
                            name = info.getName();
                            int id = loginContent.getId();
                            ShareUtils.setId(id);
                            ShareUtils.putInt(LoginActivity.this, "id", id);
                            ShareUtils.putString(LoginActivity.this, "sur", sur);
                            ShareUtils.putString(LoginActivity.this, "name", name);
                            LogUtils.e("sun", "sun1111" + sum);
                            bundle.putSerializable("sum", sum);
//                        WebSocketService.getmConnection().sendTextMessage("{\"start\":\"one\"}");//发送首次验证
                            ShareUtils.putBoolean(LoginActivity.this, "login", true);//设置登陆成功标志
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                            for (int i = 0; i < BaseApplication.list1.size(); i++) {
                                BaseApplication.list1.get(i).finish();
                            }
                            ToastUtil.t(LoginActivity.this, "登陆成功！");
                        }
                    } else if (result == 12) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                        ToastUtil.t(LoginActivity.this, "网络繁忙,稍后再试！");
                        LogUtils.e("返回信息___登陆", "失败");
                    } else if (result == 122) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                        ToastUtil.t(LoginActivity.this, "账号不存在");
                        LogUtils.e("返回信息___登陆", "账号不存在");
                    } else if (result == 121) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                        ToastUtil.t(LoginActivity.this, "密码错误！");
                        LogUtils.e("返回信息___登陆", "密码错误");
                    }
                }
            }
        };
        Map<String, Object> map = new HashMap<>();
        map.put("user_account", acc_login_account.getText().toString().trim());
        map.put("password", acc_login_password.getText().toString().trim());
        new HttpImplStringTest(map, "SignIn.xml", h, "POST").start();
    }


    org.json.JSONObject jsonObject;
    // QQ登陆返回的唯一标识
    String openID;
    String userName;

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            LogUtils.e(TAG, "response:" + response);
            org.json.JSONObject obj = (org.json.JSONObject) response;
            try {
                openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        LogUtils.e(TAG, "登录成功" + response.toString());
                        jsonObject = (org.json.JSONObject) response;
                        userName = null;
                        try {
                            userName = jsonObject.getString("nickname");
                            LogUtils.e(TAG, "用户名:" + userName);
                            qqUsername = userName;
                            addToShare();
                            /**由于图片需要下载所以这里使用了线程，如果是想获得其他文字信息直接
                             * 在mHandler里进行操作
                             */
                            new Thread() {
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    //JSONObject json = jsonObject;
                                    try {
                                        qqAvatarUrl = jsonObject.getString("figureurl_qq_2");
                                        qqAvatar = BitmapUtils.getbitmap(qqAvatarUrl);
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        qqAvatar.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                        qqAvatarByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                        //添加信息到本地share存储
                                        addToShare();
                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            Log.e(TAG, "用户名:" + userName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //需要向服务器请求与qq绑定的姓氏信息，然后传送到主页去
                        Handler hqq = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                if (msg.what == 0x123) {
                                    JSONObject JSONobj = JSON.parseObject(msg.obj.toString());
                                    int result = JSONobj.getInteger("result");
                                    if (result == 11) {
                                        LogUtils.e("返回信息___登陆", "成功");
                                        //JSONObject isonObj = JSON.parseObject(msg.obj.toString());
                                        sum = JSON.toJavaObject(JSONobj.getJSONObject("surname_info"), Surname_Info.class);
                                        LogUtils.e("sun", "sun1111" + sum);
                                        bundle.putSerializable("sum", sum);
                                        bundle.putString("userName", userName);
                                        intent.setClass(LoginActivity.this, MainActivity.class);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        ShareUtils.putBoolean(LoginActivity.this, "login", true);//设置登陆成功标志
                                        ToastUtil.t(LoginActivity.this, "登陆成功！");
                                    } else if (result == 122) {
                                        //该三方登陆为注册
                                        //intent.setClass(LoginActivity.this, RigistActivity.class);
                                        intent.setClass(LoginActivity.this, AddInformationQQActivity.class);
                                        bundle.putString("openID", openID);
                                        bundle.putString("_class", "qq");
                                        intent.putExtras(bundle);
                                        ShareUtils.putBoolean(LoginActivity.this, "login", false);//设置登陆不成功标志
                                        startActivity(intent);
                                        ToastUtil.t(LoginActivity.this, "账号不存在");
                                        LogUtils.e("返回信息___登陆", "账号不存在");
                                    } else if (result == 121) {
                                        ToastUtil.t(LoginActivity.this, "登陆错误！");
                                        LogUtils.e("返回信息___登陆", "登陆错误");
                                    }
                                }
                            }
                        };
                        Map<String, Object> map = new HashMap<>();
                        map.put("user_account", openID);
                        //密码占位
                        map.put("password", "");
                        new HttpImplStringTest(map, "SignIn.xml", hqq, "POST").start();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        LogUtils.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }
    }

    //添加信息到本地share存储
    private void addToShare() {
        ShareUtils.putString(this, "nickName", qqUsername);
        ShareUtils.putString(this, "headPic", qqAvatarByte);
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
