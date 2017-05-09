package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xunman.yibenjiapu.bean.ActionLightBean;
import com.xunman.yibenjiapu.ui.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lwk on 2017/5/2
 * 描述： 活动列表适配器
 */

public class ActionAdapter extends BaseAdapter {
    private Context context;
    private List<ActionLightBean.ActionListBean> actionListBeen;

    public ActionAdapter(Context activity, List<ActionLightBean.ActionListBean> actionListBeen) {
        this.context = activity;
        this.actionListBeen = actionListBeen;
    }

    @Override
    public int getCount() {
        return actionListBeen.size();
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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_action, null);
            holder = new ViewHolder();
            holder.ivActionBanner = (ImageView) view.findViewById(R.id.iv_action_banner);
            holder.ivActionType = (ImageView) view.findViewById(R.id.iv_action_type);
            holder.tvActionTitle = (TextView) view.findViewById(R.id.tv_action_title);
            holder.tvActionTag1 = (TextView) view.findViewById(R.id.tv_action_tag1);
            holder.tvActionTag2 = (TextView) view.findViewById(R.id.tv_action_tag2);
            holder.tvActionTag3 = (TextView) view.findViewById(R.id.tv_action_tag3);
            holder.tvActionTag4 = (TextView) view.findViewById(R.id.tv_action_tag4);
            holder.tvActionTag5 = (TextView) view.findViewById(R.id.tv_action_tag5);
            holder.tvActionIntro = (TextView) view.findViewById(R.id.tv_action_intro);
            holder.tvActionPeopleNum = (TextView) view.findViewById(R.id.tv_action_people_num);
            holder.tvActionTime = (TextView) view.findViewById(R.id.tv_action_time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if(actionListBeen != null){
            //设置活动图
            Glide.with(context)
                    .load("服务器返回的地址")
                    .error(R.drawable.ic_launcher)
                    .into(holder.ivActionBanner);
            //设置活动类型 是否是官方活动
            if(actionListBeen.get(position).isType()){ //是官方
                holder.ivActionType.setVisibility(View.VISIBLE);
            }else {
                holder.ivActionType.setVisibility(View.GONE);
            }
            //设置活动标题
            holder.tvActionTitle.setText(actionListBeen.get(position).getTitle());
            //设置活动标签
            for(int i = 0; i <actionListBeen.get(position).getTag().size(); i++){
                if(i == 0){
                    holder.tvActionTag1.setText(actionListBeen.get(position).getTag().get(i));
                }else if(i == 1){
                    holder.tvActionTag2.setVisibility(View.VISIBLE);
                    holder.tvActionTag2.setText(actionListBeen.get(position).getTag().get(i));
                }else if(i == 2){
                    holder.tvActionTag3.setVisibility(View.VISIBLE);
                    holder.tvActionTag3.setText(actionListBeen.get(position).getTag().get(i));
                }else if(i == 3){
                    holder.tvActionTag4.setVisibility(View.VISIBLE);
                    holder.tvActionTag4.setText(actionListBeen.get(position).getTag().get(i));
                }else if(i == 4){
                    holder.tvActionTag5.setVisibility(View.VISIBLE);
                    holder.tvActionTag5.setText(actionListBeen.get(position).getTag().get(i));
                }
            }
            //设置活动简介
            holder.tvActionIntro.setText(actionListBeen.get(position).getIntro());
            //设置活动报名人数
            holder.tvActionPeopleNum.setText(actionListBeen.get(position).getPeopleNum());
            //设置活动时间
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                long millionSeconds = sdf.parse(actionListBeen.get(position).getTime()).getTime();//服务器返回时间毫秒
                int day= (int) ((millionSeconds - System.currentTimeMillis())/1000/60/60/24);
                if(day < 0){
                    holder.tvActionTime.setText("已结束");
                    holder.tvActionTime.setBackgroundResource(R.drawable.icon_action_end);
                }
                holder.tvActionTime.setText(day+"天后结束");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    public class ViewHolder {
        //活动图
        private ImageView ivActionBanner;
        //活动类型
        private ImageView ivActionType;
        //活动标题
        private TextView tvActionTitle;
        //活动标签
        private TextView tvActionTag1,tvActionTag2,tvActionTag3,tvActionTag4,tvActionTag5;
        //活动简介
        private TextView tvActionIntro;
        //活动报名人数
        private TextView tvActionPeopleNum;
        //活动时间
        private TextView tvActionTime;
    }
}
