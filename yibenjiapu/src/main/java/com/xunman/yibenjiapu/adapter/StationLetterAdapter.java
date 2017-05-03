package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunman.yibenjiapu.bean.LetterInfo;
import com.xunman.yibenjiapu.ui.R;

import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/24 14:21
 * 包名：com.xunman.yibenjiapu.adapter
 * 文件名： ${name}
 * 描述：  TODO
 */

public class StationLetterAdapter extends BaseAdapter{
    private Context context;
    private ViewHolder viewHolder;
    public static List<LetterInfo> letterInfos;

    public StationLetterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
//        return letterInfos.size();
        return 2;
    }

    @Override
    public Object getItem(int i) {
//        return letterInfos.get(i);
        return i;
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_station_letter_test, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //获取adapter中的数据
//        String stationLetterTitle = letterInfos.get(i).getLetterTitle();
//        String stationLetterIntro = letterInfos.get(i).getLetterIntro();
//        String stationLetterOfficial = letterInfos.get(i).getLetterIntro();
//        String stationLetterTime = letterInfos.get(i).getLetterIntro();
//
//        viewHolder.tvStationLetterTitle.setText(stationLetterTitle);
//        viewHolder.tvStationLetterIntro.setText(stationLetterIntro);
//        viewHolder.tvStationLetterOfficial.setText(stationLetterOfficial);
//        viewHolder.tvStationLetterTime.setText(stationLetterTime);
        if(i == 0){
            viewHolder.tvStationLetterTitle.setText("站内信-好友申请");
            viewHolder.tvStationLetterIntro.setText("XXX申请添加你为好友");
            viewHolder.tvStationLetterOfficial.setVisibility(view.VISIBLE);
            viewHolder.tvStationLetterTime.setText("2017/2/24");
        }else {
            viewHolder.tvStationLetterTitle.setText("站内信-活动发起");
            viewHolder.tvStationLetterIntro.setText("家族XXX，发起XXX活动");
            viewHolder.tvStationLetterOfficial.setVisibility(view.GONE);
            viewHolder.tvStationLetterTime.setText("2017/4/10");
        }

        return view;
    }
    public class ViewHolder {
        View rootView;
        TextView tvStationLetterTitle;
        TextView tvStationLetterIntro;
        TextView tvStationLetterOfficial;
        TextView tvStationLetterTime;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            //标题
            this.tvStationLetterTitle = (TextView) rootView.findViewById(R.id.tv_station_letter_title);
            //简介
            this.tvStationLetterIntro = (TextView) rootView.findViewById(R.id.tv_station_letter_intro);
            //是否官方
            this.tvStationLetterOfficial = (TextView) rootView.findViewById(R.id.tv_station_letter_official);
            //收到时间
            this.tvStationLetterTime = (TextView) rootView.findViewById(R.id.tv_station_letter_time);
        }
    }
}
