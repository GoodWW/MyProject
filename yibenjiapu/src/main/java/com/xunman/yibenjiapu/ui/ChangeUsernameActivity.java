package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.UserInfoTest;
import com.xunman.yibenjiapu.utils.ShareUtils;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/2 09:32
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  我的信息页面
 */

public class ChangeUsernameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvDetailsBack;
    private EditText etChangeUsername;
    private Button btnSureChange;
    private Intent intent;
    private String nickName;
    //我的信息页面传过来的昵称
    private String myUsername;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        intent = getIntent();
        bundle = intent.getExtras();
        initView();
        BaseApplication.list.add(this);
    }

    private void initView() {
        tvDetailsBack = (TextView) findViewById(R.id.tv_details_back);
        tvDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etChangeUsername = (EditText) findViewById(R.id.et_change_username);
        myUsername = bundle.getString("tvMyUsername");
        etChangeUsername.setText(myUsername);
        etChangeUsername.setSelection(etChangeUsername.getText().length());

        btnSureChange = (Button) findViewById(R.id.btn_sure_change);
        btnSureChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sure_change:
                nickName = etChangeUsername.getText().toString();
                ShareUtils.putString(ChangeUsernameActivity.this, "nickName", nickName);
                intent.putExtra("nickName1",nickName);
                setResult(1,intent);
                finish();
                break;
        }
    }
}
