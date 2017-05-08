package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.bean.InformationDetailsBean;
import com.xunman.yibenjiapu.ui.CommentReplyActivity;
import com.xunman.yibenjiapu.ui.R;

import java.util.List;

/**
 * Created by lwk on 2017/5/2
 * 描述： 帖子详情评论列表适配器
 */

public class InformationCommentAdapter extends BaseAdapter {
    private Context context;
    private Intent intent;
    private List<InformationDetailsBean.InfoDetailsBean> infoDetailsBeen;

    public InformationCommentAdapter(Context activity,Intent intent, List<InformationDetailsBean.InfoDetailsBean> infoDetailsBeen) {
        this.context = activity;
        this.intent = intent;
        this.infoDetailsBeen = infoDetailsBeen;
    }

    @Override
    public int getCount() {
        return infoDetailsBeen.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_information_comment,null);
            holder = new ViewHolder();
            holder.ivCommentAvatar = (ImageView) view.findViewById(R.id.iv_comment_avatar);
            holder.tvCommentName = (TextView) view.findViewById(R.id.tv_comment_name);
            holder.tvCommentContent = (TextView) view.findViewById(R.id.tv_comment_content);
            holder.tvCommentFloorNum = (TextView) view.findViewById(R.id.tv_comment_floor_num);
            holder.tvCommentDate = (TextView) view.findViewById(R.id.tv_comment_date);
            holder.tvCommentTime = (TextView) view.findViewById(R.id.tv_comment_time);
            holder.tvCommentReply = (TextView) view.findViewById(R.id.tv_comment_reply);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvCommentFloorNum.setText(position+1+"");
        //评论回复按钮点击事件，跳转到评论详情回复界面
        LinearLayout ll_comment_bottom = (LinearLayout) view.findViewById(R.id.ll_comment_bottom);
        ll_comment_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传评论id
                intent.putExtra("commentId", infoDetailsBeen.get(position).getId());
                intent.setClass(context,CommentReplyActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }

    public class ViewHolder {
        //评论头像
        private ImageView ivCommentAvatar;
        //评论昵称
        private TextView tvCommentName;
        //评论内容
        private TextView tvCommentContent;
        //评论楼层数
        private TextView tvCommentFloorNum;
        //评论日期
        private TextView tvCommentDate;
        //评论时间
        private TextView tvCommentTime;
        //评论回复按钮
        private TextView tvCommentReply;
    }
}
