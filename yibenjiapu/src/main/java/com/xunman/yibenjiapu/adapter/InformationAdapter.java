package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xunman.yibenjiapu.bean.InformationBean;
import com.xunman.yibenjiapu.ui.ImagePagerActivity;
import com.xunman.yibenjiapu.ui.InformationDetailsActivity;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.BitmapUtils;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.TimeUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;
import com.xunman.yibenjiapu.view.NoScrollGridView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lwk on 2017/5/2
 * 描述： 资讯列表适配器
 */

public class InformationAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private List<InformationBean.InfoBean> infoBeans;
    private Intent intent;

    public InformationAdapter(List<InformationBean.InfoBean> beans, Context context, Intent intent) {
        infoBeans = beans;
        LogUtils.e("head", JSON.toJSONString(infoBeans));
        this.context = context;
        this.intent = intent;
    }
    public void onDateChange(List<InformationBean.InfoBean> lists) {
        this.infoBeans = lists;
        LogUtils.e("返回lists", "lists" + lists.toString());
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return infoBeans.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_information, null);
            holder = new ViewHolder();
            holder.iv_information_avatar = (ImageView) view.findViewById(R.id.iv_information_avatar);
            holder.tv_information_nickname = (TextView) view.findViewById(R.id.tv_information_nickname);
            holder.tv_information_minute = (TextView) view.findViewById(R.id.tv_information_minute);
            holder.tv_pv = (TextView) view.findViewById(R.id.tv_pv);
            holder.tv_information_content = (TextView) view.findViewById(R.id.tv_information_content);
            holder.tv_information_comment_num = (TextView) view.findViewById(R.id.tv_information_comment_num);
            holder.tv_information_like_num = (TextView) view.findViewById(R.id.tv_information_like_num);
            holder.gridView = (NoScrollGridView) view.findViewById(R.id.gridView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
       // final InformationBean.InfoBean infomation = (InformationBean.InfoBean) getItem(position);
        if (null != infoBeans) {
            // 设置头像
            String imgUrl = "http://172.16.1.132/"+infoBeans.get(position).getInfo_userhead().trim();
            LogUtils.e("imgUrl",imgUrl);
            // 创建默认的ImageLoader的参数 不加回报java.lang.IllegalStateException
            // 但不是每次用到ImageLoader都要加
            //ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
            //ImageLoader.getInstance().displayImage(imgUrl, holder.iv_information_avatar);
            Glide.with(context)
                    .load("http://172.16.1.132/Genealogy_data/user_info/790935943/head/0.png")
                    .error(R.drawable.ic_launcher)
                    .into(holder.iv_information_avatar);
            // 设置昵称
            holder.tv_information_nickname.setText(infoBeans.get(position).getInfo_username());
            // 设置时间
            long millionSeconds = 0;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                millionSeconds = sdf.parse(infoBeans.get(position).getInfo_time()).getTime();//毫秒
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String timeDistance = new TimeUtils().getDistanceTime(millionSeconds, System.currentTimeMillis());
            holder.tv_information_minute.setText(timeDistance);
            // 设置浏览量
            holder.tv_pv.setText(String.valueOf(infoBeans.get(position).getInfo_browse()));
            // 设置内容
            holder.tv_information_content.setText(infoBeans.get(position).getInfo_contents());
            // 设置评论数
            holder.tv_information_comment_num.setText(String.valueOf(infoBeans.get(position).getInfo_comment()));
            // 设置点赞数
            holder.tv_information_like_num.setText(String.valueOf(infoBeans.get(position).getInfo_like()));
            // 判断是否有图片
            LogUtils.e("getInfo_picture",infoBeans.get(position).getInfo_picture());
            if (infoBeans.get(position).getInfo_picture() != null && !infoBeans.get(position).getInfo_picture().equals("")) {
                // 有：设置Adapter显示图片
                holder.gridView.setVisibility(View.VISIBLE);
                final List<String> picturelist = JSON.parseObject(infoBeans.get(position).getInfo_picture(), ArrayList.class);
                holder.gridView.setAdapter(new CircleGridAdapter(picturelist, context));
                // 设置点击事件
                holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        enterPhotoDetailed(picturelist, position);
                    }
                });
            } else {
                // 否：隐藏
                holder.gridView.setVisibility(View.GONE);
            }
            LinearLayout ll_ping = (LinearLayout) view.findViewById(R.id.ll_ping);
            LinearLayout ll_shang = (LinearLayout) view.findViewById(R.id.ll_shang);
            LinearLayout ll_zhuan = (LinearLayout) view.findViewById(R.id.ll_zhuan);
            LinearLayout ll_zan = (LinearLayout) view.findViewById(R.id.ll_zan);
            //“点击评字执行item的点击事件跳转页面，赏、转、赞执行响应操作”
            ll_shang.setOnClickListener(this);
            ll_zhuan.setOnClickListener(this);
            ll_zan.setOnClickListener(this);
        }
        return view;
    }

    /**
     * 进入图片详情页
     * @param urls 图片数组
     * @param position 角标
     */
    protected void enterPhotoDetailed(List<String> urls, int position) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (CharSequence) urls);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        context.startActivity(intent);
    }

    public void updateInfoAdapter(List<InformationBean.InfoBean> beans){
        this.infoBeans = beans;
        this.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_shang:
                ToastUtil.t(context,"点击了赏");
                break;
            case R.id.ll_zhuan:
                ToastUtil.t(context,"点击了转");
                break;
            case R.id.ll_zan:
                ToastUtil.t(context,"点击了赞");
                break;
        }
    }

    public class ViewHolder {
        //发布人头像
        private ImageView iv_information_avatar;
        //发布人昵称
        private TextView tv_information_nickname;
        //发布时间
        private TextView tv_information_minute;
        //浏览量
        private TextView tv_pv;
        //帖子内容
        private TextView tv_information_content;
        //评论数量
        private TextView tv_information_comment_num;
        //点赞数量
        private TextView tv_information_like_num;
        //发布的图片
        private NoScrollGridView gridView;
    }

}
