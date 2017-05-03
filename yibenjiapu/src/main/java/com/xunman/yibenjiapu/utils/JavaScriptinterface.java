package com.xunman.yibenjiapu.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;

import com.xunman.yibenjiapu.bean.Account_Info;
import com.xunman.yibenjiapu.fragment.InformationFragmentTest;
import com.xunman.yibenjiapu.ui.LoginActivity;
import com.xunman.yibenjiapu.ui.MainActivity;


/**
 * Created by lwk on 2017/4/1
 * <p>
 * 描述    供js调用android方法工具类
 */

public class JavaScriptinterface {
    Context context;
    Intent intent;

    public JavaScriptinterface(Context c, Intent intent) {
        context = c;
        this.intent = intent;
    }

    private MainActivity mainActivity;
    private InformationFragmentTest informationFragmentTest;

    /**
     * 隐藏主页底部导航栏
     * 与js交互时用到的方法，在js里直接调用的
     */
    @JavascriptInterface
    public void GoneNavigationBar() {
        Log.e("!!!!!!!!!!", "js-android隐藏底部");
        mainActivity = new MainActivity();
        mainActivity.llMainBottomNavigation.setVisibility(View.GONE);
    }

    /**
     * 显示主页底部导航栏
     * 与js交互时用到的方法，在js里直接调用的
     */
    @JavascriptInterface
    public void VisibleNavigationBar() {
        mainActivity = new MainActivity();
        mainActivity.llMainBottomNavigation.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏资讯页面标题栏返回按钮
     * 与js交互时用到的方法，在js里直接调用的
     */

    // @JavascriptInterface
    public void GoneInformationBackbtn() {
        Log.e("!!!!!!!!!!", "js-android隐藏");
        informationFragmentTest = new InformationFragmentTest();
        informationFragmentTest.btnWvBack.setVisibility(View.GONE);
    }

    /**
     * 显示资讯页面标题栏返回按钮
     * 与js交互时用到的方法，在js里直接调用的
     */
    public void VisibleInformationBackbtn() {
        Log.e("!!!!!!!!!!", "js-android显示");
        informationFragmentTest = new InformationFragmentTest();
        informationFragmentTest.btnWvBack.setVisibility(View.VISIBLE);
        //informationFragmentTest.btnWvBack.postInvalidate();
        // informationFragmentTest.rlInformationTitle.invalidate();
    }

    /**
     * 获取id
     * 与js交互时用到的方法，在js里直接调用的
     */
    @JavascriptInterface
    public int getId() {
        Log.e("!!!!!!!!!!", "js-android显示");
        int id = ShareUtils.getId();
        return id;
    }

    /**
     * 跳转登陆界面
     * 与js交互时用到的方法，在js里直接调用的
     */
    @JavascriptInterface
    public void login() {
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
