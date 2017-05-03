package com.xunman.yibenjiapu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/20 0020 17:16
 * 包名：com.xunman.yibenjiapu.utils
 * 文件名： ${name}
 * 描述：    静态属性  全局使用
 */

public class StaticClass {
    //闪屏页的跳转延时时间 
    public static final int HANDLE_SPLASH = 1001;
    //判断程序是否第第一次运行
    public static final String SHARE_IS_FIRST = "isFirst";
    //用户登陆长连接
    //链接服务器的根链接
    //public static final String PATH_URL = HttpImplStringTest.Http_Url+"Surname/Image/";
    //public static final String VIDEO_PATH_URL = HttpImplStringTest.Http_Url+"Surname/Video/";

    //buglyAPPID
    public static final String BUGLY_APP_ID = "f1e6f21361";
    //判断是否有网络链接
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    //长连接广播的action
    public static final String LONG_CONNECTION_ACTION = "keykey";

}
