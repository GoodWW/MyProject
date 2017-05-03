package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView about_us_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        intiView();
        inti();
        BaseApplication.list.add(this);
    }

    private void inti() {
        about_us_back.setOnClickListener(this);
    }

    private void intiView() {
        about_us_back = (TextView) findViewById(R.id.about_us_back);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.about_us_back:
                finish();
                break;
        }

    }
}
