package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunman.yibenjiapu.bean.SDynamicInfo;
import com.xunman.yibenjiapu.ui.R;

import java.util.List;

/**
 * Created by lwk on 2017/3/21.
 * 姓氏动态列表
 */

public class SurnameCradleintrAdapter extends BaseAdapter {
    private Context context;
    private TextView tvTrendsName;
    private TextView tvPublishTime;
    private TextView tvTrendsContents;
    private List<SDynamicInfo.ContentsBean> lists;
    private SDynamicInfo.ContentsBean contentsBean;

    public SurnameCradleintrAdapter(Context activity, List<SDynamicInfo.ContentsBean> lists) {
        this.context = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_surname_cradleintr,null);
            //加载控件

            inView(view);
            contentsBean = lists.get(position);
            tvTrendsName.setText(contentsBean.getTrends_name());
            tvPublishTime.setText(contentsBean.getPublish_time());
            tvTrendsContents.setText(contentsBean.getTrends_contents());
        }
        return view;
    }

    private void inView(View view) {
        tvTrendsName = (TextView) view.findViewById(R.id.tv_trends_name);
        tvPublishTime = (TextView) view.findViewById(R.id.tv_publish_time);
        tvTrendsContents = (TextView) view.findViewById(R.id.tv_trends_contents);
    }
}
