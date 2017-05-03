package com.xunman.yibenjiapu.fragment;

import android.app.Dialog;
import android.app.Instrumentation;
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
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.xunman.yibenjiapu.adapter.ActionAdapter;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.JavaScriptinterface;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/4/10 0010 13:31
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  活动界面加载webview
 */

public class ActionFragment extends Fragment implements View.OnClickListener{
    private String str;
    private ListView fg_action_lv;
    private View view;
    private Intent intent;
    private ActionAdapter adapter;
    private WebView wvAction;
//    private String Url = "http://172.16.1.135:8020/yiben/activity/index.html";//本地连接
    private String Url = "http://jp.xun-m.com/yiben/activity/index.html";//网络链接


    public static Fragment instance() {
        ActionFragment actionFragment = new ActionFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("key",str);
//        actionFragment.setArguments(bundle);
        return actionFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_action, container, false);
        intiView();
        init();
//        BaseAdapter adapter = getBaseAdapter();
//        setClickFg_Action_Lv();
//        fg_action_lv.setAdapter(adapter);
        return view;
    }

//    private void setClickFg_Action_Lv() {
//        fg_action_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                intent.setClass(getActivity(), ItemActionActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    private void intiView() {
        //fg_action_lv = (ListView) view.findViewById(R.id.fg_action_lv);
        wvAction = (WebView) view.findViewById(R.id.wv_action);
    }
    private Dialog dialog;
    private void init() {
        //WebView加载web资源
        wvAction.loadUrl(Url);
//        dialog = ProgressDialog.show(getActivity(),null,"活动页面加载中，请稍后..");
//        wvAction.reload();
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvAction.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //返回网页
        wvAction.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvAction.canGoBack()) {
                        wvAction.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        //启用支持javascript
        WebSettings settings = wvAction.getSettings();
        settings.setJavaScriptEnabled(true);
        wvAction.addJavascriptInterface(new JavaScriptinterface(getActivity(), intent), "android");
        wvAction.setWebChromeClient(new WebChromeClient());
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
        wvAction.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
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

    public BaseAdapter getBaseAdapter() {
        BaseAdapter adpater = new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view ==null){

                    view = LayoutInflater.from(getActivity()).inflate(R.layout.item_fragment_action,null);

                }
                return view;
            }
        };
        return adpater;
    }
}
