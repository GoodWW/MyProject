package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名： SmartButler
 * 创建者： xxxxx
 * 创建时间：  2017/2/9 0009 17:40
 * 包名：com.xunman.smartbutler.ui
 * 文件名： ${name}
 * 描述：  引导页面
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    //容器 用来装三个界面
    List<View> mList = new ArrayList<>();
    private View view1, view2, view3;
    //点
    private ImageView point1, point2, point3;
    //跳过按钮
    private ImageView tv_back;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        intiView();
        BaseApplication.list.add(this);
    }

    //初始化数据
    private void intiView() {

        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);

//        point1 = (ImageView) findViewById(R.id.point1);
//        point2 = (ImageView) findViewById(R.id.point2);
//        point3 = (ImageView) findViewById(R.id.point3);

        //设置点的图片
//        setPointImg(true, false, false);
//        setPointImg2(true, false);

        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
        view1 = View.inflate(this, R.layout.page_item_one, null);
        view2 = View.inflate(this, R.layout.page_item_two, null);
//        view3 = View.inflate(this, R.layout.page_item_three, null);
        mList.add(view1);
        mList.add(view2);
//        mList.add(view3);

//        view3.findViewById(R.id.bt_start).setOnClickListener(this);
        view2.findViewById(R.id.bt_start).setOnClickListener(this);

        //给ViewPager设置适配器
        mViewPager.setAdapter(new GuideAdapter());
        //监听ViewPager的滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // 监听ViewPager页面被选中时的动态
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        setPointImg(true, false, false);
//                        setPointImg2(true,false);
                        tv_back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
//                        setPointImg2(false,true);
//                        setPointImg(false, true, false);
                        tv_back.setVisibility(View.GONE);
                        break;
//                    case 2:
//                        setPointImg(false, false, true);
//                        tv_back.setVisibility(View.GONE);
//                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setPointImg(boolean isPoint1, boolean isPoint2, boolean isPoint3) {
        if (isPoint1) {
            point1.setBackgroundResource(R.drawable.point1);
        } else {
            point1.setBackgroundResource(R.drawable.point2);
        }
        if (isPoint2) {
            point2.setBackgroundResource(R.drawable.point1);
        } else {
            point2.setBackgroundResource(R.drawable.point2);
        }
        if (isPoint3) {
            point3.setBackgroundResource(R.drawable.point1);
        } else {
            point3.setBackgroundResource(R.drawable.point2);
        }
    }

    private void setPointImg2(boolean isPoint1, boolean isPoint2) {
        if (isPoint1) {
            point1.setBackgroundResource(R.drawable.point1);
        } else {
            point1.setBackgroundResource(R.drawable.point2);
        }
        if (isPoint2) {
            point2.setBackgroundResource(R.drawable.point1);
        } else {
            point2.setBackgroundResource(R.drawable.point2);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
            case R.id.tv_back:
                intent = getIntent();
                intent.setClass(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        //固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //添加View的方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LogUtils.e("sdfsdafsdafasdf",mList.size()+"个");
            //将ViewGroup将转成ViewPager比较好操作
            LogUtils.e("空指针报错container",container+"");
            LogUtils.e("空指针报错mList",mList+"");
            LogUtils.e("空指针报错position",position+"");
            container.addView(mList.get(position));
            return mList.get(position);
        }

        //移除View的方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //将ViewGroup将转成ViewPager比较好操作
            container.removeView(mList.get(position));
            //不需要调用父类了直接移除
//            super.destroyItem(container, position, object);
        }
    }
}
