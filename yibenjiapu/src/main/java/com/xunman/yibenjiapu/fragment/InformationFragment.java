package com.xunman.yibenjiapu.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunman.yibenjiapu.adapter.InformationAdapter;
import com.xunman.yibenjiapu.bean.InformationBean;
import com.xunman.yibenjiapu.customview.RefreshListView;
import com.xunman.yibenjiapu.dao.Login;
import com.xunman.yibenjiapu.ui.InformationDetailsActivity;
import com.xunman.yibenjiapu.ui.InformationReleaseActivity;
import com.xunman.yibenjiapu.ui.LoginActivity;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;
import com.xunman.yibenjiapu.view.ProgressFragmentDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lwk on 2017/5/2
 * <p>
 * 描述    资讯列表界面
 */

public class InformationFragment extends Fragment implements View.OnClickListener,RefreshListView.ILoadListtener{
    private View view;
    private Intent intent;
    private Bundle bundle;
    //发布的论坛消息列表
    private RefreshListView lvInformation;
    //发布动态按钮
    private ImageView ivBtnRelease;
    //资讯列表Adapter
    private InformationAdapter informationAdapter;
    //服务器返回信息映射类
    private InformationBean informationBean;

    private Boolean information = true;

    public static Fragment instance() {
        InformationFragment informationFragment = new InformationFragment();
        return informationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information_test, container, false);
        //设置一个progressdialog的弹窗
//        dialog = new ProgressFragmentDialog();
//        dialog.show(getFragmentManager(), "progress");
        getInformation();
        init();
        return view;
    }
    //网络访问进度条
    private ProgressFragmentDialog dialog;
    private List<InformationBean.InfoBean> infoBeen;
    /**
     * 获取资讯列表信息
     */
    private void getInformation() {
        Handler InfoH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String info = msg.obj.toString();
                    LogUtils.e("返回信息___资讯", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){
                        informationBean=JSON.toJavaObject(JSON.parseObject(info), InformationBean.class);
                        infoBeen.addAll(informationBean.getInfo());
                        informationAdapter.updateInfoAdapter(infoBeen);
                        if (dialog.getDialog().isShowing())dialog.dismiss();
                    }else if(result == 12){
                        ToastUtil.t(getActivity(), "资讯加载失败");
                        if (dialog.getDialog().isShowing())dialog.dismiss();
                    }
                }
            }
        };
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("start",1);
        mapInfo.put("number",15);
        mapInfo.put("sort","subtract");
        mapInfo.put("label","");
        new HttpImpl(mapInfo, "http://172.16.1.132:8080/Genealogy/servlet2/info/PullInfo.xml", "POST",InfoH).start();
    }


    private void init() {
        lvInformation = (RefreshListView) view.findViewById(R.id.lv_information);
        lvInformation.setInterface(this);
        ivBtnRelease = (ImageView) view.findViewById(R.id.iv_btn_release);

        ivBtnRelease.setOnClickListener(this);
        infoBeen = new ArrayList<>();
        informationAdapter = new InformationAdapter(infoBeen,getActivity(),intent);
        lvInformation.setAdapter(informationAdapter);
        lvInformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传点击的该item对应的id
                bundle = intent.getExtras();
                bundle.putInt("infoId", infoBeen.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getActivity(), InformationDetailsActivity.class);
                startActivity(intent);
            }
        });
        //设置刷新
        lvInformation.setonRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLow();
            }
        });
    }

    //刷新 list数据
    private void refreshLow() {
        Handler refreshInfoH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    infoBeen.clear();
                    String info = msg.obj.toString();
                    LogUtils.e("刷新返回信息__资讯", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){
                        informationBean=JSON.toJavaObject(JSON.parseObject(info), InformationBean.class);
                        infoBeen.addAll(informationBean.getInfo());
                        LogUtils.e("listindex", infoBeen.size() + "个");
                        informationAdapter.notifyDataSetChanged();
                        lvInformation.onRefreshComplete();
                        ToastUtil.t(getActivity(), "刷新成功！");
                    }
                }
            }
        };
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("start",1);
        mapInfo.put("number",15);
        mapInfo.put("sort","subtract");
        mapInfo.put("label","");
        new HttpImpl(mapInfo, "http://172.16.1.132:8080/Genealogy/servlet2/info/PullInfo.xml", "POST",refreshInfoH).start();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showListView(RefreshListView lv) {
        informationAdapter.onDateChange(infoBeen);
    }

    @Override
    public void onLoad() {
        setDate();
    }

    private void setDate() {
        addMore();
        showListView(lvInformation);
    }

    /**
     * 加载更多代码
     */
    private void addMore() {
        Handler addMoreInfoH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String str = msg.obj.toString();
                    if (str.equals("链接服务器超时")) {
                        ToastUtil.t(getActivity(), str);
                    } else {
//                        JSONObject JSONobj = JSON.parseObject(info);
//                        LogUtils.e("strstr", str);
//                        informationBean=JSON.toJavaObject(JSON.parseObject(info), InformationBean.class);
                        String s = JSONObject.parseObject(str).getString("info");
//                        if (!s.equals("[]")) {
//                            LogUtils.e("strstr", s);
//                            informationBean=JSON.toJavaObject(JSON.parseObject(str), InformationBean.class);
//                            infoBeen.addAll(informationBean.getInfo());
//                            LogUtils.e("listindex", infoBeen.size() + "个");
//                            lvInformation.loadConplete();
//                        } else {
//                            LogUtils.e("strstr", s);
//                            ToastUtil.t(getActivity(), "已经加载完了，没有更多了！");
//                            lvInformation.loadConplete();
//                        }
                        if (s != null) {
                            informationBean=JSON.toJavaObject(JSON.parseObject(str), InformationBean.class);
                            infoBeen.addAll(informationBean.getInfo());
                            LogUtils.e("listindex", infoBeen.size() + "个");
                            lvInformation.loadConplete();
                        } else {
                            ToastUtil.t(getActivity(), "已经加载完了，没有更多了！");
                            lvInformation.loadConplete();
                        }
                    }
                }
            }
        };
//        if (infoBeen.size() == 15) {
            Map<String, Object> map = new HashMap<>();
            map.put("start", infoBeen.size()+1);
            map.put("number", 10);
            map.put("sort","subtract");
            map.put("label","");
            new HttpImpl(map, "http://172.16.1.132:8080/Genealogy/servlet2/info/PullInfo.xml", "POST",addMoreInfoH).start();
//        } else {
//            Map<String, Object> map = new HashMap<>();
//            map.put("start", start1 + 10);
//            map.put("number", 10);
//            map.put("sort", "subtract");
//            map.put("key", "number");
//            new HttpImpl(map, "http://172.16.1.132:8080/Genealogy/servlet2/info/PullInfo.xml", "POST",addMoreInfoH).start();
//            start1 += 10;
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_btn_release:
                if(Login.getLoginInfo("id")== null){
                    ToastUtil.t(getActivity(), "请先登陆");
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getActivity(), InformationReleaseActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
