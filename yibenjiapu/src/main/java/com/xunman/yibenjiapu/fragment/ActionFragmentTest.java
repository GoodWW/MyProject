package com.xunman.yibenjiapu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunman.yibenjiapu.adapter.ActionAdapter;
import com.xunman.yibenjiapu.bean.ActionLightBean;
import com.xunman.yibenjiapu.ui.ActionActivity;
import com.xunman.yibenjiapu.ui.ActionLightActivity;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/60010 13:31
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  活动列表界面
 */

public class ActionFragmentTest extends Fragment{
    private Intent intent;
    private Bundle bundle;
    private View view;
    private ListView lvAction;
    private ActionAdapter actionAdapter;
    private List<ActionLightBean.ActionListBean> actionListBeen;
    private ActionLightBean actionLightBean;

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

        actionListBeen = new ArrayList<>();
        actionAdapter = new ActionAdapter(getActivity(), actionListBeen);
        lvAction.setAdapter(actionAdapter);
        lvAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //判断传入的活动id，判断是“点亮活动”还是其他活动，跳转不同界面
                if(actionListBeen.get(position).getId() == 1){
                    //传入活动id
                    bundle.putInt("actionId", actionListBeen.get(position).getId());
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), ActionLightActivity.class);
                    startActivity(intent);
                }else{
                    //传入活动id
                    bundle.putInt("actionId", actionListBeen.get(position).getId());
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), ActionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 获取活动列表信息
     */
    private void getInformation() {
        Handler ActionH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String info = msg.obj.toString();
                    LogUtils.e("返回信息___活动", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){
                        actionLightBean =JSON.toJavaObject(JSON.parseObject(info), ActionLightBean.class);
                        actionListBeen.addAll(actionLightBean.getInfo());
                    }else if(result == 12){
                        ToastUtil.t(getActivity(), "活动列表加载失败");
                    }
                }
            }
        };
        Map<String, Object> mapAction = new HashMap<>();
        mapAction.put("start",1);
        mapAction.put("number",15);
        mapAction.put("sort","subtract");
        mapAction.put("label","");
        new HttpImpl(mapAction, "", "POST",ActionH).start();
    }
}
