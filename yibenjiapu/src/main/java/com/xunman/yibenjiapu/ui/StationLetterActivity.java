package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xunman.yibenjiapu.adapter.StationLetterAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;

/**
 * 站内信
 */
public class StationLetterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView activity_station_letter_back;
    private ListView lvMessage;
    private StationLetterAdapter stationLetterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_letter);
        intiView();

        stationLetterAdapter = new StationLetterAdapter(this);
        lvMessage.setAdapter(stationLetterAdapter);
        BaseApplication.list.add(this);
    }

    private void intiView() {
        activity_station_letter_back = (TextView) findViewById(R.id.activity_station_letter_back);
        lvMessage = (ListView) findViewById(R.id.lv_message);

        activity_station_letter_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_station_letter_back:
                finish();
                break;
        }
    }
}
