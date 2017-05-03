package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;

import org.w3c.dom.Text;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/2 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  点亮服务页面
 */
public class ServiceLightActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLightServiceBack;
    //选择省市
    private TextView tvSelect;
    private int requestCode = 101;//请求码
    //判断加载哪个界面
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_service1);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvLightServiceBack = (TextView) findViewById(R.id.tv_light_service_back);
        tvLightServiceBack.setOnClickListener(this);

        tvSelect = (TextView) findViewById(R.id.tv_select);
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceLightActivity.this, ShowRegionActivity.class);
                intent.putExtra("address", "address");
                startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_light_service_back:
                finish();
                break;
        }
    }

    /**
     * 将返回数据给tv
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102) {
            tvSelect.setText(data.getStringExtra("address"));
        }
    }
}
