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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.xunman.yibenjiapu.adapter.ActionAdapter;
import com.xunman.yibenjiapu.ui.ActionLightActivity;
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

public class ActionFragmentTest extends Fragment{
    private Intent intent;
    private View view;
    private ListView lvAction;
    private ActionAdapter actionAdapter;

    public static Fragment instance() {
        ActionFragmentTest actionFragment = new ActionFragmentTest();
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
        view = inflater.inflate(R.layout.fragment_action_test, container, false);
        intiView();
        return view;
    }

    private void intiView() {
        lvAction = (ListView) view.findViewById(R.id.lv_action);

        actionAdapter = new ActionAdapter(getActivity());
        lvAction.setAdapter(actionAdapter);
        lvAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(getActivity(), ActionLightActivity.class);
                startActivity(intent);
            }
        });
    }
}
