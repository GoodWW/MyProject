package com.xunman.yibenjiapu.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xunman.yibenjiapu.adapter.FamilyBranchAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.Family_branch;
import com.xunman.yibenjiapu.bean.SDynamicInfo;
import com.xunman.yibenjiapu.bean.User_Relation_info;
import com.xunman.yibenjiapu.utils.CustomVideoView;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.PixelUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页item进入的页面
 * 修改标题下方为listview
 */

public class ItemMainActivity extends Activity implements View.OnClickListener {
    private TextView activity_item_main_back;
    //接收数据的bundle
    private Bundle bundle;
    private Intent intent;
    //头顶图片
    private ImageView item_main_name;
    //家族分支列表
    private ListView lvFamilyBranch;
    private List<Family_branch.BranchBean> lists;
    private Family_branch family_branch;
    private SDynamicInfo sDynamicInfo;

    private TextView tvNameOrigin;

    private String sCradleintr;
    private String strSD;
    //动态标题和内容
    private String trendsName;
    private String trendsContents;
    private TextView tvTrendsName;
    private TextView tvTrendsContents;
    private LinearLayout llNameDynamic;

    private CustomVideoView videoView;
    //控制进度的布局   控制栏布局
    private LinearLayout controllerLayout, controllerbar_layout;
    //暂停和播放的按钮  全屏和半屏的切换   音量控制图标
    private ImageView play_controller_img;//, screen_img, volume_img
    //当前播放的时间   视频播放的总时间
    private TextView time_current_tv, time_total_tv;
    //控制播放的进度   控制音量的进度
    private SeekBar play_seek;//, volume_seek
    //设置一个常量用来控制更新UI
    public static final int UPDATE_UI = 1;

    private boolean isVisible = true;//标记位  是否显示  控制台
    /**
     * 当前获取的屏幕宽  和  高
     */
    private int screen_width, screen_height;
    /**
     * 最外层的布局
     */
    private RelativeLayout videoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_main);
        intent = getIntent();
        bundle = intent.getExtras();
        intiUi();
        //弄好以后我们首先做播放器的播放和暂停这个控件
        //2、播放器里面的一些事件
        setPlayerEvent();
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/VID_20170328_103524.mp4";
        String sUrl = bundle.getString("mp4_url");
        LogUtils.e("滴滴滴","id==="+sUrl);
        videoView.setVideoURI(Uri.parse(sUrl));
        //本地视频播放   面传入一个本地地址  设置视频播放源
//        videoView.setVideoPath(path);
        videoView.start();
        //当视频开始播放的时候就应该调用更新UI的方法
        UIHandler.sendEmptyMessage(UPDATE_UI);

        //姓氏起源
        sCradleintr = bundle.getString("sCradleintr");

        //姓氏动态
        strSD = bundle.getString("strSD");
        sDynamicInfo = JSON.parseObject(strSD, SDynamicInfo.class);
        trendsName = sDynamicInfo.getContents().get(0).getTrends_name();
        trendsContents = sDynamicInfo.getContents().get(0).getTrends_contents();

        //家族分支
        String strSB = bundle.getString("strSB");
        if (strSB ==null){
            Toast.makeText(this, "动态加载失败，请重新加载", Toast.LENGTH_SHORT).show();
        }else{
            family_branch = JSON.parseObject(strSB, Family_branch.class);
            lists = new ArrayList<>();
            lists.addAll(family_branch.getBranch());
            intiView();
            //设置顶部图片
            setTopImg();
            BaseApplication.list.add(this);
        }
    }

    //设置顶部图片
    private void setTopImg() {
        Glide.with(this)
                .load(bundle.getString("sUrl"))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//是将图片原尺寸缓存到本地。
                .placeholder(R.mipmap.zhao)
                .crossFade()
                .into(item_main_name);
    }
    private ProgressDialog dialog;
    private void intiView() {
        item_main_name = (ImageView) findViewById(R.id.item_main_name);
        activity_item_main_back = (TextView) findViewById(R.id.activity_item_main_back);
        activity_item_main_back.setOnClickListener(this);
        lvFamilyBranch = (ListView) findViewById(R.id.lv_family_branch);
        FamilyBranchAdapter familyBranchAdapter = new FamilyBranchAdapter(this, lists);
        lvFamilyBranch.setAdapter(familyBranchAdapter);
        lvFamilyBranch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置一个progressdialog的弹窗
                dialog = ProgressDialog.show(ItemMainActivity.this, null, "网络正在加载，请稍候...", true, false);
                Handler h = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 0x123) {
                            String str = msg.obj.toString();
                            LogUtils.e("家谱树返回信息", str);
                            //user_relation_info = JSON.parseObject(str, User_Relation_info.class);
                            bundle.putString("str", str);
                            intent.putExtras(bundle);
                            intent.setClass(ItemMainActivity.this, JiaPuTreeActivity.class);
                            startActivity(intent);
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    }
                };
                Map<String, Object> map = new HashMap<>();
                map.put("id",2);
                LogUtils.e("id===========>>", "id========>" + map.toString());
                new HttpImplStringTest(map, "SelectFamily.xml", h, "GET").start();
            }
        });

        //姓氏起源
        tvNameOrigin = (TextView) findViewById(R.id.tv_name_origin);
        tvNameOrigin.setText(sCradleintr);

        //姓氏动态
        tvTrendsName = (TextView) findViewById(R.id.tv_trends_name);
        tvTrendsContents = (TextView) findViewById(R.id.tv_trends_contents);
        tvTrendsName.setText(trendsName);
        tvTrendsContents.setText(trendsContents);
        llNameDynamic = (LinearLayout) findViewById(R.id.ll_name_dynamic);
        llNameDynamic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_item_main_back:
                finish();
                break;
            case R.id.ll_name_dynamic:
                intent.setClass(ItemMainActivity.this, SurnameCradleintrActivity.class);
                bundle.putString("strSD", strSD);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    /**
     * 时间的格式化
     *
     * @param textView    视频的总时间  和当前播放的时间都是TextView
     * @param millisecond 毫秒
     */
    private void updateTextViewWithTimeFormat(TextView textView, int millisecond) {
        //毫秒转成秒
        int second = millisecond / 1000;
        //秒转成小时
        int hh = second / 3600;
        //转化成分钟
        int mm = second % 3600 / 60;
        //格式化之后的秒
        int ss = second % 60;
        String str = null;
        //判断时间的格式
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
        textView.setText(str);
    }

    //需要一个方法去实时的刷新UI界面  通过一个Handler  完成的
    //达到播放进度和两个TextView的同步  达到界面刷新的效果
    private Handler UIHandler = new Handler() {
        //在这个方法里面不做判断了，直接做做刷新UI的处理
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //如果msg.what 等于UPDATE_UI  就执行刷新UI的操作
            if (msg.what == UPDATE_UI) {
                //首先获取当前视频播放的时间   返回的是一个毫秒值
                int currentPosition = videoView.getCurrentPosition();
                int totalDuration = videoView.getDuration();
                //视频的总时间的获取
                //这两个值都是int类型的值   格式化的话就只需要调用上面写好的格式化方法
                //格式化视频的总播放时间
                updateTextViewWithTimeFormat(time_total_tv, totalDuration);
                //格式化视频当前播放的时间
                updateTextViewWithTimeFormat(time_current_tv, currentPosition);
                //有了这些还需要去得到当前视频播放的进度
                //设置视频播放的最大进度
                play_seek.setMax(totalDuration);
                //设置当前播放的进度
                play_seek.setProgress(currentPosition);
                //需要连续不断的去调用  就让Hnadler自己和自己通信  达到反复执行的目的  相当于每隔0.5秒就会去更新UI
                UIHandler.sendEmptyMessageDelayed(UPDATE_UI, 500);
            }
        }
    };

    private void setPlayerEvent() {
        //控制播放器的播放和暂停  写好这个呢  我们还需要控制播放的进度
        play_controller_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮的时候根据视频的播放状态改变按钮的图片
                if (videoView.isPlaying())//如果视频播放器是播放状态的时候执行  暂停操作
                {
                    play_controller_img.setImageResource(R.drawable.play_btn_style);
                    //暂停播放
                    videoView.pause();
                    //当停止播放的时候需要停止刷新UI
                    UIHandler.removeMessages(UPDATE_UI);
                } else {
                    play_controller_img.setImageResource(R.drawable.pause_btn_stytle);
                    //继续播放
                    videoView.start();
                    //继续播放  需要回复UIHandler
                    UIHandler.sendEmptyMessage(UPDATE_UI);
                }
            }
        });
        //seekBar播放进度的拖动事件
        play_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //5、同步当前正在播放的时间的TextView
                updateTextViewWithTimeFormat(time_current_tv, i);
            }
            //刚开始拖动的时候
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //1、停止更新UI
                UIHandler.removeMessages(UPDATE_UI);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //2、拿到当前拖动的进度
                int progress = seekBar.getProgress();
                //3、videoView 直接跳转到当前的进度   让视频的播放进度遵循seekbar停止拖动这一刻的进度
                videoView.seekTo(progress);
                //4、再继续更新UI
                UIHandler.sendEmptyMessage(UPDATE_UI);
            }
        });

        //控制VideoView的手势事件
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (isVisible) {
                            controllerbar_layout.setVisibility(View.VISIBLE);
                        } else {
                            controllerbar_layout.setVisibility(View.GONE);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isVisible) {
                            isVisible = false;
                        } else {
                            isVisible = true;
                        }
                        break;
                }
                return true;
            }
        });
    }

    //1、初始化视频播放器中控件
    private void intiUi() {
        PixelUtils.intiCOntext(this);
        controllerbar_layout = (LinearLayout) findViewById(R.id.controllerbar_layout);
        videoView = (CustomVideoView) findViewById(R.id.videoView);
        controllerLayout = (LinearLayout) findViewById(R.id.controllerbar_layout);
        play_controller_img = (ImageView) findViewById(R.id.pause_img);

        time_current_tv = (TextView) findViewById(R.id.time_current_tv);
        time_total_tv = (TextView) findViewById(R.id.time_total_tv);
        play_seek = (SeekBar) findViewById(R.id.play_seek);

        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
        videoLayout = (RelativeLayout) findViewById(R.id.videoLayout);
    }

    /**
     * 设置VideoView的宽和高
     *
     * @param width 宽
     * @param heiht 高
     */
    private void setVideoViewScale(int width, int heiht) {
        //获取当前VideoView的layoutparents
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = heiht;
        videoView.setLayoutParams(layoutParams);
        //给最外层的布局也同样设置一下
        ViewGroup.LayoutParams layoutParams1 = videoLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = heiht;
        videoLayout.setLayoutParams(layoutParams1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当停止播放的时候需要停止刷新UI
        UIHandler.removeMessages(UPDATE_UI);
    }
}
