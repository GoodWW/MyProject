package com.xunman.yibenjiapu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xunman.yibenjiapu.utils.LogUtils;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/28 0028 15:00
 * 包名：com.xunman.yibenjiapu.service
 * 文件名： ${name}
 * 描述：  TODO
 */

public class WebSocketService extends Service {

    //WebSocket的访问地址
    private static final String wsurl = "ws://jp.xun-m.com:8081/Genealogy/websocket";
    //静态变量   WebSocket对象   默认为空
    private static WebSocketConnection mConnection = null;

    //静态方法得到WebSocket的方法
    public static WebSocketConnection getmConnection() {
        return mConnection;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //打开WebSocket
        mConnection = new WebSocketConnection();
        connect();
    }


    /**
     * 建立websocket连接，接收服务器消息
     */
    private void connect() {
        LogUtils.e("返回", "getWeb");
        try {
            mConnection.connect(wsurl, new WebSocketHandler() {
                //打开时调用
                @Override
                public void onOpen() {
                    LogUtils.e("WebSocket", "onOpen");
                }

                //返回的数据
                //利用广播为用户提醒消息
                @Override
                public void onTextMessage(String payload) {
                    LogUtils.e("返回", "返回的数据" + payload);
                    //1、把收到的消息存入固定文件
                    //2、用广播通知用户有新消息或通知聊天界面刷新消息

                    if(SaveChat(payload)){
                        //启动广播
                    }
//                    Intent intent = new Intent();
//                    intent.setAction(StaticClass.LONG_CONNECTION_ACTION);
//                    intent.putExtra("key", payload);
//                    sendBroadcast(intent);
//                    LogUtils.e("返回", "onCreate");
                }

                //关闭时调用
                @Override
                public void onClose(int code, String reason) {
                    LogUtils.e("返回", "onClose" + code + "_____" + reason);
                }
            });
        } catch (WebSocketException e) {
            LogUtils.e("返回", "WebSocketException");
            e.printStackTrace();
        }
    }
    private boolean SaveChat(String contents){

        return true;
    }

}
