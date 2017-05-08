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
 * Created by lwk on 2017/5/2
 * 描述： 帖子详情评论列表适配器
 */

public class CommentReplyAdapter extends BaseAdapter {
    private Context context;

    public CommentReplyAdapter(Context activity) {
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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_comment_reply,null);
            holder = new ViewHolder();
            holder.ivReplyAvatar = (ImageView) view.findViewById(R.id.iv_reply_avatar);
            holder.tvReplyName = (TextView) view.findViewById(R.id.tv_reply_name);
            holder.tvReplyContent = (TextView) view.findViewById(R.id.tv_reply_content);
            holder.tvReplyFloorNum = (TextView) view.findViewById(R.id.tv_reply_floor_num);
            holder.tvReplyDate = (TextView) view.findViewById(R.id.tv_reply_date);
            holder.tvReplyTime = (TextView) view.findViewById(R.id.tv_reply_time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvReplyFloorNum.setText(i+1+"");
        return view;
    }

    public class ViewHolder {
        //评论回复头像
        private ImageView ivReplyAvatar;
        //评论回复昵称
        private TextView tvReplyName;
        //评论回复内容
        private TextView tvReplyContent;
        //评论回复楼层数
        private TextView tvReplyFloorNum;
        //评论日期
        private TextView tvReplyDate;
        //评论时间
        private TextView tvReplyTime;
    }
}
