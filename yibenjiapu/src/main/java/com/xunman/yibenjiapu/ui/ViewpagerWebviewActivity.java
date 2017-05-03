package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 描述   首页viewpager 点击后webview
 */
public class ViewpagerWebviewActivity extends AppCompatActivity {
    private WebView wvViewpager;
    private String Url = "http://jp.xun-m.com/Genealogy/myproject/information/index.html";
    private int userId = 1;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_webview);
        intiview();
        init();
    }

    private void intiview() {
        wvViewpager = (WebView) findViewById(R.id.wv_viewpager);
    }

    private void init() {
        webUrl = Url+"?user_id="+userId;
        //WebView加载web资源
        wvViewpager.loadUrl(webUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvViewpager.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //返回网页
        wvViewpager.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvViewpager.canGoBack()) {
                        wvViewpager.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        //启用支持javascript
        WebSettings settings = wvViewpager.getSettings();
        settings.setJavaScriptEnabled(true);
        wvViewpager.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                } else {
                    // 加载中
                }
            }
        });
        //优先使用缓存
        wvViewpager.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
//
//    // WebView回退方法
//    public void actionKey(final int keyCode) {
//        new Thread () {
//            public void run () {
//                try {
//                    Instrumentation inst=new Instrumentation();
//                    inst.sendKeyDownUpSync(keyCode);
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_wv_back:
//                actionKey(KeyEvent.KEYCODE_BACK);
//                break;
//        }
//    }
}
