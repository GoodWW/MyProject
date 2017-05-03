package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 项目名：   MainFragmentTestAdapterViewpager
 * 创建者：   lwk
 * 创建时间： 2017/2/22
 * 包名：     com.xunman.yibenjiapu.adapter
 * 文件名：   ${name}
 * 描述：     MainFragment头部Viewpager专用Adapter
 */

public class MainFragmentTestAdapterViewpager extends PagerAdapter{
    private List<View> viewList;
    private Context context;

    public MainFragmentTestAdapterViewpager(Context activity,List<View> list) {
        // TODO Auto-generated constructor stub
        this.viewList = list;
        this.context = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(viewList.get(position));
    }
    private View view;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        //container.addView(this.viewList.get(position));
        view = this.viewList.get(position);
        container.addView(view);
        //首页viewpager点击后跳转的webView暂时不用
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ViewpagerWebviewActivity.class);
//                context.startActivity(intent);
//            }
//        });
        return view;
    }
}
