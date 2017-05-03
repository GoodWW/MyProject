package com.xunman.yibenjiapu.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import java.util.Map;

/**
 * 完全没用！
 *
 * Created by lwk on 2017/4/11.
 */

public class JiapushuReturn {
    public static String strInfo;
    /**
     * 传入用户id，获得服务器返回的家谱树数据
     *
     * @param id 用户信息id
     * @return strInfo 服务器返回家谱树数据
     */
    public static String getJiapushuReturn(Context context,int id, Bundle bundle, Intent intent){
        Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    //家谱树返回信息
                    strInfo = msg.obj.toString();
                    LogUtils.e("家谱树返回信息", strInfo);
                }
            }
        };
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        LogUtils.e("id===========>>", "id========>" + map.toString());
        new HttpImplStringTest(map, "SelectFamily.xml", h, "GET").start();
        return strInfo;
    }
}
