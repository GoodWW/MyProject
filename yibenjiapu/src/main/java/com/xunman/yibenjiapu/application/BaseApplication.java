package com.xunman.yibenjiapu.application;

import android.app.Activity;
import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.xunman.yibenjiapu.utils.StaticClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/20 0020 17:27
 * 包名：com.xunman.yibenjiapu.application
 * 文件名： ${name}
 * 描述：  application 在里面做一些初始化工作
 */

public class BaseApplication extends Application {
    public static List<Activity> list;//退出登录的activity集合
    public static List<Activity> list1;//登录的activity集合
    @Override
    public void onCreate() {
        super.onCreate();

        //bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        list = new ArrayList<>();
        list1 = new ArrayList<>();
    }
}
