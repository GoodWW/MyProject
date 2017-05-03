package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.BitmapUtils;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/2/27 15:16
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  详细信息页面
 */

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvDetailsBack;
    private ImageView ivHeadPortrait;
    private TextView tvNickname;
    //关系
    private TextView tvRelation;
    private TextView tvSex;
    //添加关系人信息按钮
    private TextView btnAddFamily;

    private Intent intent;
    private Bundle bundle;
    //昵称名字
    private String strName;
    //头像字节流
    private String headPortraitByte;
    private Bitmap bitmap;
    //关系
    private String strRelation;
    //性别
    private String strSex;
    //信息id
    private int id;
    //分支id
    private int branch_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        BaseApplication.list.add(this);
    }

    private void initView() {
        intent = getIntent();
        bundle = intent.getExtras();

        strName = bundle.getString("strName");
        strRelation = bundle.getString("relation");
        headPortraitByte = bundle.getString("headPortraitByte", headPortraitByte);
        strSex = bundle.getString("sex");
        id = bundle.getInt("id");
        branch_id = bundle.getInt("branch_id");
        bitmap = BitmapUtils.ByteToBitmap(headPortraitByte);

        tvDetailsBack = (TextView) findViewById(R.id.tv_details_back);
        ivHeadPortrait = (ImageView) findViewById(R.id.iv_head_portrait);
        tvNickname = (TextView) findViewById(R.id.tv_nickname);
        tvRelation = (TextView) findViewById(R.id.tv_relation);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        btnAddFamily = (TextView) findViewById(R.id.btn_add_family);

        ivHeadPortrait.setImageBitmap(bitmap);
        tvNickname.setText(strName);
        tvRelation.setText(strRelation);
        tvSex.setText(strSex);
        tvDetailsBack.setOnClickListener(this);
        btnAddFamily.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_details_back:
                finish();
                break;
            case R.id.btn_add_family:
                //传递添加关系人信息所需要的数据
                //信息id
                bundle.putInt("id", id);
                //分支id
                bundle.putInt("branch_id", branch_id);
                //性别
                bundle.putString("sex", strSex);
                intent.setClass(this, AddInformationRelativeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
