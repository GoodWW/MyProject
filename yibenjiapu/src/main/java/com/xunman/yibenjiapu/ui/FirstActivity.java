package com.xunman.yibenjiapu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.StaticClass;

import java.util.HashMap;
import java.util.Map;

/**
 * 闪页
 * <p>使用中</p>
 */
public class FirstActivity extends Activity {
    private String OnlineString_a = null, OnlineString_b = null;
    private Intent intent;
    private Bundle bundle;
    //2月20号修改 页面跳转
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bund = msg.getData();
            switch (msg.what) {
                case StaticClass.HANDLE_SPLASH:
                    //判断程序是否第第一次运行
                    if (isFirst()) {
                        if (OnlineString_a != null && OnlineString_b != null) {
                            intent = new Intent(FirstActivity.this, GuideActivity.class);
                            intent.putExtras(bund);
                            startActivity(intent);
                            finish();
                        } else {
                            LogUtils.e("sur=========>>", "空了");
                            get(bundle);
                        }
                    } else {
                        if (OnlineString_a != null && OnlineString_b != null) {
                            intent = new Intent(FirstActivity.this, MainActivity.class);
                            intent.putExtras(bund);
                            startActivity(intent);
                            finish();
                        } else {
                            LogUtils.e("sur1=========>>", "空了");
                            get(bundle);
                        }
                    }
                    break;
            }
        }
    };
    private static long firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
//        Intent intentService = new Intent();
//        intentService.setClass(this, WebSocketService.class);
//        startService(intentService);
//        savedInstanceState = getIntent().getExtras();
        bundle = new Bundle();
        get(bundle);
        BaseApplication.list.add(this);
        firstTime = System.currentTimeMillis();
    }
    private Handler handler_family_and_name;
    private Handler handler_ad;
    /**
     * 获取姓氏第一页信息
     *
     * @param savedInstanceState Bundle对象
     */
    private void get(final Bundle savedInstanceState) {

        //获取网络数据,姓氏数据

        handler_family_and_name = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    OnlineString_a = msg.obj.toString();
                    LogUtils.e("首页初次获取姓氏数据===", OnlineString_a);
                    savedInstanceState.putString("surnameintr", OnlineString_a);
                }
            }
        };
        //获取网络数据,广告数据

        handler_ad = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    OnlineString_b = msg.obj.toString();
                    LogUtils.e("首页获取广告数据==", OnlineString_b);
                    savedInstanceState.putString("AdvertisementInfo", OnlineString_b);
                    Message msg1 = new Message();
                    msg1.what = StaticClass.HANDLE_SPLASH;
                    msg1.setData(savedInstanceState);
                    handler.sendMessageDelayed(msg1, 200);
                }
            }
        };

        Map<String, Object> map = new HashMap<>();
        map.put("start", 1);
        map.put("number", 15);
        map.put("sort", "sort");
        map.put("key", "number");
        new HttpImplStringTest(map, "SelectSurname.xml", handler_family_and_name, "POST").start();

        Map<String, Object> map2 = new HashMap<>();
        map2.put("start", 1);
        map2.put("number", 3);
        map2.put("time", null);
        new HttpImplStringTest(map2, "SelectAdvertisementList.xml", handler_ad, "GET").start();
    }

    //2月20日修改
    // 判断程序是否是第一次运行
    public boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            LogUtils.e("isFirst", "值：" + isFirst);
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            LogUtils.e("isFirst", "值：" + isFirst);
            //是第一次运行
            isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            LogUtils.e("isFirst", "值：" + isFirst);
            return true;
        } else {
            //不是第一次运行 直接返回false
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler_family_and_name.removeCallbacksAndMessages(null);
        handler_ad.removeCallbacksAndMessages(null);
    }

    //禁止返回键    只要不去调用父类的方法就禁止了
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

}
