package com.xunman.yibenjiapu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.StaticClass;

/**
 * 闪页
 * <p>使用中</p>
 */
public class FirstActivity extends Activity {
    private Intent intent;
    //2月20号修改 页面跳转
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLE_SPLASH:
                    //判断程序是否第第一次运行
                    if (isFirst()) {
                            intent = new Intent(FirstActivity.this, GuideActivity.class);
                            startActivity(intent);
                    } else {
                            intent = new Intent(FirstActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        BaseApplication.list.add(this);
        handler.sendEmptyMessageDelayed(StaticClass.HANDLE_SPLASH,1000);

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
    }

    //禁止返回键    只要不去调用父类的方法就禁止了
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

}
