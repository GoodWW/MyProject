package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunman.yibenjiapu.ui.R;
/**
 * Created by Administrator on 2017/3/8.
 * 商品兑换记录列表
 */

public class ConvertRecordAdapter extends BaseAdapter {
    private Context context;
    private TextView tvYear;
    private TextView tvDate;
    private ImageView ivCommodity;
    private TextView tvCommodityName;
    private TextView tvCommodityPrice;
    private TextView tvCommodityNumber;

    public ConvertRecordAdapter(Context activity) {
        this.context = activity;
    }

    @Override
    public int getCount() {
        return 20;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_convert_record,null);
            //加载控件
            inView(view);
        }
        return view;
    }

    private void inView(View view) {
        tvYear = (TextView) view.findViewById(R.id.tv_year);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvCommodityName = (TextView) view.findViewById(R.id.tv_commodity_name);
        tvCommodityPrice = (TextView) view.findViewById(R.id.tv_commodity_price);
        tvCommodityNumber = (TextView) view.findViewById(R.id.tv_commodity_number);
        ivCommodity = (ImageView) view.findViewById(R.id.iv_commodity);
    }
}
