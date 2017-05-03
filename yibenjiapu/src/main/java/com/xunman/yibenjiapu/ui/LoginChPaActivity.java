package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/4/10 0010 17:49
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  TODO
 */

public class LoginChPaActivity extends AppCompatActivity {
    private DeletableEditText ch_login_old_psw, ch_login_new_psd, ch_login_new_psd_again;
    private TextView ch_login_back;
    private Button ch_login_send;
    private String old_passWord_str;
    private String new_password_str;
    private String new_password_again_str;
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ch_pa);
        intiView();
        click();
    }

    /**
     * 点击事件
     */
    private void click() {
        getDate();
        ch_login_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ch_login_old_psw.getText())) {
                    ch_login_old_psw.setShakeAnimation();
                    ToastUtil.t(LoginChPaActivity.this, "请输入原密码");
                } else if (TextUtils.isEmpty(ch_login_new_psd.getText())) {
                    ch_login_new_psd.setShakeAnimation();
                    ToastUtil.t(LoginChPaActivity.this, "请输入您的新密码");
                } else if (!new_password_str.equals(new_password_again_str)) {
                    LogUtils.e("返回返回","new_password_str==="+new_password_str+"------"+"new_password_again_str==="+new_password_again_str);
                    ch_login_new_psd.setShakeAnimation();
                    ch_login_new_psd_again.setShakeAnimation();
                    ToastUtil.t(LoginChPaActivity.this, "两次输入不一致");
                }else{
                    connectInternet();
                }
            }
        });
    }

    /**
     * 链接网络拿回返回结果
     */
    private void connectInternet() {
        Handler h  = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String str = msg.obj.toString();
                    LogUtils.e("返回返回",str);
//                    int result = JSONobj.getInteger("result");
//                    if (result == 11) {
//                    }
                }
            }
        };
        Map<String, Object> map = new HashMap<>();
        map.put("newpassword", new_password_str);
        map.put("oldpassword", old_passWord_str);
        map.put("id", id);
        new HttpImplStringTest(map, "ChangePassword.xml", h, "POST").start();
    }

    private void intiView() {
        ch_login_back = (TextView) findViewById(R.id.ch_login_back);
        ch_login_old_psw = (DeletableEditText) findViewById(R.id.ch_login_old_psw);
        ch_login_new_psd = (DeletableEditText) findViewById(R.id.ch_login_new_psd);
        ch_login_new_psd_again = (DeletableEditText) findViewById(R.id.ch_login_new_psd_again);
        ch_login_send = (Button) findViewById(R.id.ch_login_send);
    }

    /**
     * 获取需要的值
     */
    public void getDate() {
        old_passWord_str = ch_login_old_psw.getText().toString();
        new_password_str = ch_login_new_psd.getText().toString();
        new_password_again_str = ch_login_new_psd_again.getText().toString();
        id = ShareUtils.getInt(LoginChPaActivity.this, "id", 0);
        LogUtils.e("返回返回","id====="+id);
    }


}
