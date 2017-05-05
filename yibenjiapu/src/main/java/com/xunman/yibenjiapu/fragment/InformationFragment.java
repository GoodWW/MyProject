package com.xunman.yibenjiapu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xunman.yibenjiapu.adapter.InformationAdapter;
import com.xunman.yibenjiapu.ui.InformationDetailsActivity;
import com.xunman.yibenjiapu.ui.InformationReleaseActivity;
import com.xunman.yibenjiapu.ui.R;

/**
 * Created by lwk on 2017/5/2
 * <p>
 * 描述    资讯列表界面
 */

public class InformationFragment extends Fragment implements View.OnClickListener{
    private View view;
    private Intent intent;
    //发布的论坛消息列表
    private ListView lvInformation;
    //发布动态按钮
    private ImageView ivBtnRelease;
    //资讯列表Adapter
    private InformationAdapter informationAdapter;

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
        init();
        return view;
    }

    private void init() {
        lvInformation = (ListView) view.findViewById(R.id.lv_information);
        ivBtnRelease = (ImageView) view.findViewById(R.id.iv_btn_release);

        ivBtnRelease.setOnClickListener(this);
        informationAdapter = new InformationAdapter(getActivity());
        lvInformation.setAdapter(informationAdapter);
        lvInformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(getActivity(), InformationDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_btn_release:
                intent.setClass(getActivity(), InformationReleaseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
