package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xunman.yibenjiapu.ui.R;

/**
 * Created by lwk on 2017/5/2
 * 描述： 活动列表适配器
 */

public class ActionAdapter extends BaseAdapter {
    private Context context;

    public ActionAdapter(Context activity) {
        this.context = activity;
    }

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
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_action,null);
        }
        return view;
    }
}
