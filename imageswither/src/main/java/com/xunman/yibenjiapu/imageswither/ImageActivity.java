package com.xunman.yibenjiapu.imageswither;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ImageActivity extends Activity {
    // public HashMap<String,Bitmap> imagesCache=new HashMap<String,
    // Bitmap>();//图片缓存
    public List<String> urls;
    public GuideGallery images_ga;
    private int positon = 0;
    private Thread timeThread = null;
    public boolean timeFlag = true;
    private boolean isExit = false;
    public ImageTimerTask timeTaks = null;
    Uri uri;
    Intent intent;
    int gallerypisition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_connection_image);
        init();
        timeTaks = new ImageTimerTask();
        // 参考网址  http://blog.csdn.net/weidan1121/article/details/527307#html
        autoGallery.scheduleAtFixedRate(timeTaks, 5000, 5000);
        timeThread = new Thread() {
            public void run() {
                while (!isExit) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (timeTaks) {
                        if (!timeFlag) {
                            timeTaks.timeCondition = true;
                            timeTaks.notifyAll();
                        }
                    }
                    timeFlag = true;
                }
            };
        };
        timeThread.start();
    }

    private void init() {
        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.mipmap.icon);
        // imagesCache.put("background_non_load",image); //设置缓存中默认的图片

        images_ga = (GuideGallery) findViewById(R.id.image_wall_gallery);
        images_ga.setImageActivity(this);

        ImageAdapter imageAdapter = new ImageAdapter(this);
        images_ga.setAdapter(imageAdapter);
        LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
        pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));
        for (int i = 0; i < 4; i++) {
            ImageView pointView = new ImageView(this);
            if (i == 0) {
                pointView.setBackgroundResource(R.mipmap.feature_point_cur);
            } else
                pointView.setBackgroundResource(R.mipmap.feature_point);
            pointLinear.addView(pointView);
        }
        images_ga.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                System.out.println(arg2 + "arg2");
                switch (arg2) {
                    case 0:
                        uri = Uri.parse("http://www.36939.net/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                        break;
                    case 1:
                        uri = Uri.parse("http://www.jiqunejia.com/default.aspx");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                        break;
                    case 2:
                        uri = Uri.parse("http://www.jiqunejia.tv/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                        break;
                    case 3:
                        uri = Uri.parse("http://city.4000100006.com/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                        break;

                    default:
                        break;
                }

            }
        });

    }

    public void changePointView(int cur) {
        LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
        View view = pointLinear.getChildAt(positon);
        View curView = pointLinear.getChildAt(cur);
        if (view != null && curView != null) {
            ImageView pointView = (ImageView) view;
            ImageView curPointView = (ImageView) curView;
            pointView.setBackgroundResource(R.mipmap.feature_point);
            curPointView.setBackgroundResource(R.mipmap.feature_point_cur);
            positon = cur;
        }
    }

    final Handler autoGalleryHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    images_ga.setSelection(message.getData().getInt("pos"));
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        timeFlag = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeTaks.timeCondition = false;
    }

    class ImageTimerTask extends TimerTask {
        public volatile boolean timeCondition = true;

        // int gallerypisition = 0;
        public void run() {
            synchronized (this) {
                while (!timeCondition) {
                    try {
                        Thread.sleep(100);
                        wait();
                    } catch (InterruptedException e) {
                        Thread.interrupted();
                    }
                }
            }
            try {
                gallerypisition = images_ga.getSelectedItemPosition() + 1;
                System.out.println(gallerypisition + "");
                Message msg = new Message();
                Bundle date = new Bundle();// 存放数据
                date.putInt("pos", gallerypisition);
                msg.setData(date);
                msg.what = 1;// 消息标识
                autoGalleryHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Timer autoGallery = new Timer();

}
