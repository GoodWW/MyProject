package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.ActionBean;
import com.xunman.yibenjiapu.utils.BitmapUtils;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/2 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  活动页面
 */
public class ActionActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvActionBack;
    //活动标题
    private TextView tvSpecificActionTitle;
    //活动图片
    private ImageView ivAction;
    //活动按钮
    private TextView tvActionBtn;
    private ActionBean actionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        bundle = intent.getExtras();
        setContentView(R.layout.activity_action);
        getAction();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvActionBack = (TextView) findViewById(R.id.tv_action_back);
        tvSpecificActionTitle = (TextView) findViewById(R.id.tv_specific_action_title);
        ivAction = (ImageView) findViewById(R.id.iv_action);
        tvActionBtn = (TextView) findViewById(R.id.tv_action_btn);

        tvActionBack.setOnClickListener(this);
        tvActionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_light_back:
                finish();
                break;
            case R.id.tv_action_btn:
                ToastUtil.t(ActionActivity.this, "报名成功");
                break;
        }
    }
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            Drawable drawable =new BitmapDrawable(bitmap);
//            tvActionBtn.setBackground(drawable);
            tvActionBtn.setBackground(drawable);
            isWorking = false;

        }
    };
    Thread thread;
    private Boolean isWorking;

    /**
     * 获取活动信息
     */
    private void getAction() {
        Handler ActionH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String info = msg.obj.toString();
                    LogUtils.e("返回信息___活动", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){
                        actionBean =JSON.toJavaObject(JSON.parseObject(info), ActionBean.class);
                        if(actionBean != null) {
                            tvSpecificActionTitle.setText(actionBean.getTitle());
                            Glide.with(ActionActivity.this)
                                    .load(actionBean.getImage())
                                    .error(R.drawable.ic_launcher)
                                    .into(ivAction);
                            isWorking = true;
                             new Thread(new Runnable() {
                                 @Override
                                 public void run() {
                                     while (isWorking){
                                         Bitmap bitmap = BitmapUtils.getbitmap(actionBean.getImagebtn());
                                         Message msg = new Message();
                                         msg.obj = bitmap;
                                         handler.sendMessage(msg);
                                     }
                                 }
                             }).start();
                            tvActionBtn.setText(actionBean.getTvbtn());

                        }else{
                            ToastUtil.t(ActionActivity.this, "活动内容获取失败");
                        }
                    }else if(result == 12){
                        ToastUtil.t(ActionActivity.this, "活动加载失败");
                    }
                }
            }
        };
        Map<String, Object> mapAction = new HashMap<>();
        mapAction.put("actionId",bundle.getInt("actionId"));
        new HttpImpl(mapAction, "", "POST",ActionH).start();
    }
}
