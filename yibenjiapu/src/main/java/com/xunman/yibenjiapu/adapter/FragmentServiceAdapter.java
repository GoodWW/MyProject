package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.bean.MsgInfo;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.ShareUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名： ServiceDemo
 * 创建者： xxxxx
 * 创建时间：  2017/2/17 0017 10:21
 * 包名：com.xunman.servicedemo
 * 文件名： ${name}
 * 描述：  客服界面的 Adapter
 */

public class FragmentServiceAdapter extends BaseAdapter {

    private Context context;
    public static List<MsgInfo> datas = new ArrayList<>();
    private ViewHolder viewHolder;
    //头像昵称
    private String qqAvatarByte;
    private String qqUsername;
    private Bitmap bitmap;
    private String sur;//姓氏
    private String name;//名字

    public FragmentServiceAdapter(Context context) {
        this.context = context;
    }

    //  给adapter添加数据
    public void addDataToAdapter(MsgInfo msgInfo) {
        datas.add(msgInfo);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //使用ViewHolder来优化listView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_fragment_service, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置用户头像昵称
        if (ShareUtils.getString(context, "nickName", qqUsername) != null && ShareUtils.getBoolean(context, "login", false)) {
            viewHolder.tvName.setText(ShareUtils.getString(context, "nickName", qqUsername));
        } else if (ShareUtils.getString(context, "sur", sur) != null && ShareUtils.getString(context, "name", name) != null
                && ShareUtils.getBoolean(context, "login", true)) {
            sur = ShareUtils.getString(context, "sur", sur);
            name = ShareUtils.getString(context, "name", name);
            qqUsername = sur + name;
            ShareUtils.putString(context, "nickName", qqUsername);
            viewHolder.tvName.setText(qqUsername);
        } else {
            viewHolder.tvName.setText("请登陆");
        }

        if (ShareUtils.getString(context, "headPic", qqAvatarByte) != null && ShareUtils.getBoolean(context, "login", true)) {
            qqAvatarByte = ShareUtils.getString(context, "headPic", qqAvatarByte);
            byte[] bytes = Base64.decode(qqAvatarByte.getBytes(), 1);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            viewHolder.ivHead.setImageBitmap(bitmap);
        } else {
            viewHolder.ivHead.setImageResource(R.mipmap.header);
        }

        //获取adapter中的数据
        String left = datas.get(position).getLeft_text();
        String right = datas.get(position).getRight_text();
        //如果左边数据为空，则将数据设置给右边，同时显示右边，影藏左边
        if (left == null) {
            viewHolder.text_right.setText(right);
            viewHolder.right.setVisibility(View.VISIBLE);
            viewHolder.left.setVisibility(View.GONE);
        }
        //与上一步相反 显示左边
        if (right == null) {
            viewHolder.text_left.setText(Html.fromHtml(left));
            viewHolder.left.setVisibility(View.VISIBLE);
            viewHolder.right.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class ViewHolder {
        View rootView;
        TextView text_left;
        LinearLayout left;
        TextView text_right;
        LinearLayout right;
        ImageView ivHead;
        TextView tvName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.text_left = (TextView) rootView.findViewById(R.id.text_left);
            this.left = (LinearLayout) rootView.findViewById(R.id.left);
            this.text_right = (TextView) rootView.findViewById(R.id.text_right);
            this.right = (LinearLayout) rootView.findViewById(R.id.right);
            this.ivHead = (ImageView) rootView.findViewById(R.id.iv_head);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }
    }
}
