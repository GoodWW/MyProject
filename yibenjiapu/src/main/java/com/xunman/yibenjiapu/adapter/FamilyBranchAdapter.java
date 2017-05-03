package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunman.yibenjiapu.bean.Family_branch;
import com.xunman.yibenjiapu.ui.R;

import java.util.List;

/**
 * Created by lwk on 2017/3/30.
 * 家族分支列表
 */

public class FamilyBranchAdapter extends BaseAdapter {
    private Context context;
    private TextView tv_surname;
    private List<Family_branch.BranchBean> lists;
    private Family_branch.BranchBean contentsBean;

    public FamilyBranchAdapter(Context activity, List<Family_branch.BranchBean> lists) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_details_page5,null);
            //加载控件

            inView(view);
            contentsBean = lists.get(position);
            tv_surname.setText(contentsBean.getSur());
        }
        return view;
    }

    private void inView(View view) {
        tv_surname = (TextView) view.findViewById(R.id.tv_surname);
    }
}
