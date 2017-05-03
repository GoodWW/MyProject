package com.xunman.yibenjiapu.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.fragment.ActionFragment;
import com.xunman.yibenjiapu.fragment.ActionFragmentTest;
import com.xunman.yibenjiapu.fragment.InformationFragment;
import com.xunman.yibenjiapu.fragment.InformationFragmentTest;
import com.xunman.yibenjiapu.fragment.MainFragmentTestForViewpager;
import com.xunman.yibenjiapu.fragment.MyselfFragment2;
import com.xunman.yibenjiapu.fragment.ServiceFragment;
import com.xunman.yibenjiapu.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private String tag = "MainActivity";
    private LinearLayout l1, l2, l3, l4, l5;
    private ImageView im1, im2, im3, im4, im5;
    private TextView tv1, tv2, tv3, tv4, tv5;
    private Intent intent;
    private MyselfFragment2 myselfFragment2 = (MyselfFragment2) MyselfFragment2.instance();
    private FragmentManager fragmentManager;
    private List<Fragment> list = new ArrayList<>();
    private FragmentTransaction transaction;
    //底部导航栏
    public static LinearLayout llMainBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = this.getIntent();
        setContentView(R.layout.activity_main);
        intiView();
        BaseApplication.list.add(this);
        BaseApplication.list1.add(this);
    }


    private void intiView() {
        l1 = (LinearLayout) findViewById(R.id.main_ll_1);
        l2 = (LinearLayout) findViewById(R.id.main_ll_2);
        l3 = (LinearLayout) findViewById(R.id.main_ll_3);
        l4 = (LinearLayout) findViewById(R.id.main_ll_4);
        l5 = (LinearLayout) findViewById(R.id.main_ll_5);
        im1 = (ImageView) findViewById(R.id.main_im_1);
        im2 = (ImageView) findViewById(R.id.main_im_2);
        im3 = (ImageView) findViewById(R.id.main_im_3);
        im4 = (ImageView) findViewById(R.id.main_im_4);
        im5 = (ImageView) findViewById(R.id.main_im_5);
        tv1 = (TextView) findViewById(R.id.main_tv_1);
        tv2 = (TextView) findViewById(R.id.main_tv_2);
        tv3 = (TextView) findViewById(R.id.main_tv_3);
        tv4 = (TextView) findViewById(R.id.main_tv_4);
        tv5 = (TextView) findViewById(R.id.main_tv_5);
        llMainBottomNavigation = (LinearLayout) findViewById(R.id.ll_main_bottom_navigation);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);

        list.add(MainFragmentTestForViewpager.instance());

        //list.add(InformationFragmentTest.instance());
        list.add(InformationFragment.instance());

        list.add(ActionFragmentTest.instance());
        list.add(ServiceFragment.instance());
        list.add(myselfFragment2);
        fragmentManager =  getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_center, MainFragmentTestForViewpager.instance());
        transaction.commit();
        select(1);
    }

    @Override
    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_ll_1:
                select(1);
                transaction.replace(R.id.main_center, list.get(0));
                transaction.commit();
                break;
            case R.id.main_ll_2:
                select(2);
                transaction.replace(R.id.main_center, list.get(1));
                transaction.commit();
                break;
            case R.id.main_ll_3:
                select(3);
                transaction.replace(R.id.main_center, list.get(2));
                transaction.commit();
                break;
            case R.id.main_ll_4:
                select(4);
                transaction.replace(R.id.main_center, list.get(3));
                transaction.commit();
                break;
            case R.id.main_ll_5:
                select(5);
                transaction.replace(R.id.main_center, list.get(4));
                transaction.commit();
                break;
        }
    }

    private void select(int id) {
        switch (id) {
            case 1:
                im1.setBackgroundResource(R.drawable.home_t);
                im2.setBackgroundResource(R.drawable.information);
                im3.setBackgroundResource(R.drawable.activity);
                im4.setBackgroundResource(R.drawable.service);
                im5.setBackgroundResource(R.drawable.myself);
                break;
            case 2:
                im1.setBackgroundResource(R.drawable.homepage);
                im2.setBackgroundResource(R.drawable.information_t);
                im3.setBackgroundResource(R.drawable.activity);
                im4.setBackgroundResource(R.drawable.service);
                im5.setBackgroundResource(R.drawable.myself);
                break;
            case 3:
                im1.setBackgroundResource(R.drawable.homepage);
                im2.setBackgroundResource(R.drawable.information);
                im3.setBackgroundResource(R.drawable.activity_t);
                im4.setBackgroundResource(R.drawable.service);
                im5.setBackgroundResource(R.drawable.myself);
                break;
            case 4:
                im1.setBackgroundResource(R.drawable.homepage);
                im2.setBackgroundResource(R.drawable.information);
                im3.setBackgroundResource(R.drawable.activity);
                im4.setBackgroundResource(R.drawable.service_t);
                im5.setBackgroundResource(R.drawable.myself);
                break;
            case 5:
                im1.setBackgroundResource(R.drawable.homepage);
                im2.setBackgroundResource(R.drawable.information);
                im3.setBackgroundResource(R.drawable.activity);
                im4.setBackgroundResource(R.drawable.service);
                im5.setBackgroundResource(R.drawable.myself_t);
                break;
        }
    }

    private static long firstTime;

    /**
     * 连续按两次返回键就退出
     */
    @Override
    public void onBackPressed() {

        LogUtils.e(tag, "onBackPressed");
        if (firstTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
//            for (int i = 0;i<BaseApplication.list.size();i++){
//                list.get(i).
//            }
        } else {
            Toast.makeText(MainActivity.this, "再按一次退出界面", Toast.LENGTH_SHORT).show();
        }
        firstTime = System.currentTimeMillis();
    }

    // -------------------------------------隐藏输入法-----------------------------------------------------
    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
//            llMainBottomNavigation.setVisibility(View.GONE);
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                llMainBottomNavigation.setVisibility(View.VISIBLE);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
//                llMainBottomNavigation.setVisibility(View.GONE);
                return false;
            } else {
//                llMainBottomNavigation.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
            llMainBottomNavigation.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.e("返回得到", "requestCode====>" + requestCode+ "<==" + "resultCode==>" + "<=="+resultCode + "<=="+ "data==>" + data);
        myselfFragment2.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
