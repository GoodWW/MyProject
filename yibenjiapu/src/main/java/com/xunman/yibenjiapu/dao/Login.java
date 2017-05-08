package com.xunman.yibenjiapu.dao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.utils.HttpImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆
 * Created by Administrator on 2017/5/4.
 */

public class Login {
    private static String URL = "http://172.16.1.132:8080/Genealogy/servlet2/user/";
    public static String SignIn = URL + "SignIn.xml";
    private String TELE_NUMBER = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    private Handler handler;
    public static String LoginInfo = null;

    /**
     * 登陆操作
     *
     * @param style
     * @param loginmap
     * @param handler
     */
    public void userLogin(String style, Map<String, Object> loginmap, Handler handler) {
        this.handler = handler;
        switch (style) {
            case "tele":
                Telephone(loginmap);
                break;
            case "email":
                Email(loginmap);
                break;
            case "qq":
                QQ(loginmap);
                break;
            case "wb":
                WB(loginmap);
                break;
            case "wx":
                WX(loginmap);
                break;
        }
    }

    /**
     * 判断是否处于登陆状态
     *
     * @return
     */
    public boolean styleLogin(Intent intent) {
        Bundle bundle = intent.getExtras();
        return bundle.getString("id").equals("") || bundle.getString("id") == null ? false : true;
    }

    private void Telephone(Map<String, Object> loginmap) {
        new HttpImpl(loginmap, SignIn, "POST", handler).start();
    }

    private void Email(Map<String, Object> loginmap) {
        // new HttpImplStringTest().start();
    }

    private void QQ(Map<String, Object> loginmap) {
        // new HttpImplStringTest().start();
    }

    private void WB(Map<String, Object> loginmap) {
        // new HttpImplStringTest().start();
    }

    private void WX(Map<String, Object> loginmap) {
        // new HttpImplStringTest().start();
    }
    public static int getLoginResult(){
        if(LoginInfo==null||LoginInfo.equals("")){
            return 0;
        }else{
            return JSON.parseObject(LoginInfo).getInteger("result");
        }
    }
    public static Object getLoginInfo(String key){
        if(LoginInfo==null||LoginInfo.equals("")){
            return null;
        }else{
            return JSON.parseObject(LoginInfo).getJSONObject("info").get(key);
        }
    }


    /**
     * 判断登陆信息
     * msg.what:0x10输入正常、0x11账号为空、0x12输入的不是电话号码、0x13密码为空、
     *
     * @param map
     * @param style 登陆方式
     */
    public void LoginInfo(Map<String, Object> map, String style, Handler Loginhandler) {
        Message msg = new Message();
        switch (style) {
            case "TeleLogin":
                msg.what = TeleTrue(map);
                Loginhandler.sendMessage(msg);
                break;
        }
    }

    /**
     * 判断电话号码
     *
     * @param map
     * @return
     */
    private int TeleTrue(Map<String, Object> map) {
        if (TextUtils.isEmpty(map.get("account").toString()))
            return 0x11;
        else if (map.get("account").toString().matches(TELE_NUMBER))
            return PassTrue(map);
        else
            return 0x12;
    }

    /**
     * 密码判断
     *
     * @param map
     * @return
     */
    private int PassTrue(Map<String, Object> map) {
        if (TextUtils.isEmpty(map.get("password").toString()))
            return 0x13;
        else
            return 0x10;
    }


}
