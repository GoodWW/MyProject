package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xunman.yibenjiapu.adapter.MyPrizeAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.customview.DeletableEditText;

public class MyPrizeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView activity_my_prize_back;
    private ListView lvJackpot;
    private MyPrizeAdapter myPrizeAdapter;
    private DeletableEditText et_CDKEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prize);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        activity_my_prize_back = (TextView) findViewById(R.id.activity_my_prize_back);
        activity_my_prize_back.setOnClickListener(this);

        lvJackpot = (ListView) findViewById(R.id.lv_jackpot);
        myPrizeAdapter = new MyPrizeAdapter(this);
        lvJackpot.setAdapter(myPrizeAdapter);

//        et_CDKEY = (DeletableEditText) findViewById(R.id.et_CDKEY);
//        et_CDKEY.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                et_CDKEY.setFocusable(true);
//                et_CDKEY.setFocusableInTouchMode(true);
//                et_CDKEY.requestFocus();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_my_prize_back:
                finish();
                break;
        }
    }
}
