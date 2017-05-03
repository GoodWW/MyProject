package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.mode.ExpressionMode;
import com.xunman.yibenjiapu.view.ExpressionGridView;
import com.xunman.yibenjiapu.view.Expressions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/4/27
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  资讯发布界面
 */

public class InformationReleaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    //返回按钮
    private TextView information_release_back;
    //表情按钮
    private RelativeLayout rlInformationIconFace;
    //发布内容输入框
    private EditText expressionTextInput;
    private ExpressionGridView expressionGridview;
    private RelativeLayout rl_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_information_release);
        initView();
        initExpression();
        BaseApplication.list.add(this);
    }

    private void initView() {
        information_release_back = (TextView) findViewById(R.id.information_release_back);
        rlInformationIconFace = (RelativeLayout) findViewById(R.id.rl_information_icon_face);
        expressionTextInput = (EditText) findViewById(R.id.et_information_input);
        expressionGridview = (ExpressionGridView) findViewById(R.id.expression_gridview);
        rl_test = (RelativeLayout) findViewById(R.id.rl_test);

        information_release_back.setFocusable(true);
        information_release_back.setFocusableInTouchMode(true);
        information_release_back.requestFocus();

        information_release_back.setOnClickListener(this);
        rlInformationIconFace.setOnClickListener(this);
    }

    /**
     * 加载表情
     */
    private void initExpression(){
        expressionGridview.setEditText(expressionTextInput);
        List<ExpressionMode> expressionUtils = new ArrayList<>();
        List<Integer> imgs = new ArrayList<>();
        imgs.addAll(Arrays.<Integer>asList(Expressions.expressionImgs));
        imgs.addAll(Arrays.<Integer>asList(Expressions.expressionImgs1));
        imgs.addAll(Arrays.<Integer>asList(Expressions.expressionImgs2));
        imgs.addAll(Arrays.<Integer>asList(Expressions.expressionImgs3));
        imgs.addAll(Arrays.<Integer>asList(Expressions.expressionImgs4));
        List<String> nams = new ArrayList<>();
        nams.addAll(Arrays.asList(Expressions.expressionImgNames));
        nams.addAll(Arrays.asList(Expressions.expressionImgNames1));
        nams.addAll(Arrays.asList(Expressions.expressionImgNames2));
        nams.addAll(Arrays.asList(Expressions.expressionImgNames3));
        nams.addAll(Arrays.asList(Expressions.expressionImgNames4));
        for (int i = 0; i < imgs.size(); i++) {
            ExpressionMode expressionMode = new ExpressionMode();
            expressionMode.setValue(nams.get(i));
            expressionMode.setResources(imgs.get(i));
            expressionUtils.add(expressionMode);
        }
//        expressionGridview.setRelativeLayout((RelativeLayout) findViewById(R.id.face_view));
//        expressionGridview.setImageView((ImageView) findViewById(R.id.face_img));
        expressionGridview.setExpressionModes(expressionUtils);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.information_release_back:
                finish();
                break;
            case R.id.rl_information_icon_face:
                if(rl_test.getVisibility() == View.GONE){
                    rl_test.setVisibility(View.VISIBLE);
                }else if(rl_test.getVisibility() == View.VISIBLE){
                    rl_test.setVisibility(View.GONE);
                }
                break;
        }
    }
}
