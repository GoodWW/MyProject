package com.xunman.yibenjiapu.fragment;

import android.app.Dialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.JavaScriptinterface;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;

/**
 * Created by lwk on 2017/3/17
 * <p>
 * 描述    资讯界面WebView
 */

public class InformationFragmentTest extends Fragment implements View.OnClickListener{
    private View view;
    private Intent intent;
    private WebView webView;
//    private String Url = "http://jp.xun-m.com:8081/Genealogy/myproject/information/index.html";
    private String Url = "http://211.149.170.166/yiben/index.html";
    private int userId = 1;
    private String webUrl;
    public static Button btnWvBack;
    //资讯标题栏
    public static RelativeLayout rlInformationTitle;

    public static Fragment instance() {
        InformationFragmentTest informationFragment = new InformationFragmentTest();
        return informationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information, container, false);
        webView = (WebView) view.findViewById(R.id.wv_zx);
        rlInformationTitle = (RelativeLayout) view.findViewById(R.id.rl_information_title);
        btnWvBack = (Button) view.findViewById(R.id.btn_wv_back);
        btnWvBack.setOnClickListener(this);
        init();
        return view;
    }
    private Dialog dialog;
    private void init() {
        if(ShareUtils.getInt(getActivity(), "id", userId) != 0 && ShareUtils.getBoolean(getActivity(),"login",true)){
            userId = ShareUtils.getInt(getActivity(), "id", userId);
        }else {
            userId = 0;
        }
        LogUtils.e("userId", userId+"");
        webUrl = Url+"?user_id="+userId;
        //WebView加载web资源
        webView.loadUrl(webUrl);
        //加载webview显示进度框
        dialog = ProgressDialog.show(getActivity(),null,"资讯页面加载中，请稍后..",false, true);
        webView.reload();
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
            //webview加载完成，关闭对话框
            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
            }
        });
        //返回网页
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavaScriptinterface(getActivity(),intent), "android");
        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                // TODO Auto-generated method stub
//                if (newProgress == 100) {
//                    // 网页加载完成
//                } else {
//                    // 加载中
//                }
//            }
//        });
        //只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
        //webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //不使用缓存，只从网络获取数据
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    // WebView回退方法
    public void actionKey(final int keyCode) {
        new Thread () {
            public void run () {
                try {
                    Instrumentation inst=new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wv_back:
                actionKey(KeyEvent.KEYCODE_BACK);
                break;
        }
    }
}
