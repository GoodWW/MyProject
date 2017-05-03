package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunman.yibenjiapu.ui.R;

/**
 * Created by Administrator on 2017/3/8.
 * 商品兑换记录列表
 */

public class MyPrizeAdapter extends BaseAdapter {
    private Context context;
    private TextView tvPrizeName;
    private TextView tvPrizeContent;
    private TextView tvPrizeTime;

    public MyPrizeAdapter(Context activity) {
        this.context = activity;
    }

    @Override
    public int getCount() {
        return 5;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_jackpot,null);
            //加载控件
            inView(view);
        }
        return view;
    }

    private void inView(View view) {
        tvPrizeName = (TextView) view.findViewById(R.id.tv_prize_name);
        tvPrizeContent = (TextView) view.findViewById(R.id.tv_prize_content);
        tvPrizeTime = (TextView) view.findViewById(R.id.tv_prize_time);
    }
}
