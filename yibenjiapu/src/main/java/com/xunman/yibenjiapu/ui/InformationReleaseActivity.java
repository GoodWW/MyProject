package com.xunman.yibenjiapu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.adapter.GridAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.CircleBean;
import com.xunman.yibenjiapu.dao.Login;
import com.xunman.yibenjiapu.mode.ExpressionMode;
import com.xunman.yibenjiapu.utils.Bimp;
import com.xunman.yibenjiapu.utils.FileUtils;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.MyPopupWindow;
import com.xunman.yibenjiapu.utils.SystemUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;
import com.xunman.yibenjiapu.view.ExpressionGridView;
import com.xunman.yibenjiapu.view.Expressions;
import com.xunman.yibenjiapu.view.NoScrollGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //放置图片GridView
    private RelativeLayout rlPicture;
    private GridAdapter mAdapter;
    private NoScrollGridView mGridView;
    public static String mImagePath;
    private static final int TAKE_PICTURE = 0x000000;
    private String mImageFileName;
    //发表按钮
    private TextView tvSend;
    //发表资讯标题
    private EditText etInformationTitle;
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
        rlPicture = (RelativeLayout) findViewById(R.id.rl_picture);
        etInformationTitle = (EditText) findViewById(R.id.et_information_title);

        rlPicture.setOnClickListener(this);
        mAdapter = new GridAdapter(this);
        mAdapter.update();
        mGridView = (NoScrollGridView) findViewById(R.id.gv_gridview);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.mBmps.size()) {
                    showPopupWindow();
                } else {
                    Intent intent = new Intent(InformationReleaseActivity.this, PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });

        tvSend = (TextView) findViewById(R.id.tv_send);

        information_release_back.setFocusable(true);
        information_release_back.setFocusableInTouchMode(true);
        information_release_back.requestFocus();

        tvSend.setOnClickListener(this);
        information_release_back.setOnClickListener(this);
        rlInformationIconFace.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        mAdapter.update();
        super.onRestart();
    }

    /**
     * 加载表情
     */
    private void initExpression() {
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // 拍照
            if (requestCode == TAKE_PICTURE) {
                if (Bimp.mDrr.size() < 9 && resultCode == -1) {
                    File file = new File(Environment.getExternalStorageDirectory() + "/" + mImageFileName);
                    mImagePath = file.getPath();
                    Bimp.mDrr.add(mImagePath);
                }
            }
        }
    }

    /**
     * 拍照
     */
    public void photo() {
        // 随机缓存照片名
        mImageFileName = FileUtils.getFileNameForSystemTime(".jpg");
        // 首先判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), mImageFileName)));
            startActivityForResult(intent, TAKE_PICTURE);
        } else {
            ToastUtil.t(this, "内存卡不存在");
        }
    }

    private void showPopupWindow() {
        isKeyboardShownToHideKeyboard();
        MyPopupWindow popupWindow = new MyPopupWindow(this);
        String[] str = {"拍照", "从相册中选择"};
        popupWindow.showPopupWindowForFoot(str, new MyPopupWindow.Callback() {
            @Override
            public void callback(String text, int position) {
                switch (position) {
                    case 0:
                        photo();
                        break;
                    case 1:
                        Intent intent = new Intent(InformationReleaseActivity.this, TestPicActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    /**
     * 判断软键盘是否弹起如弹起则隐藏
     */
    private void isKeyboardShownToHideKeyboard() {
        if (SystemUtils.isKeyboardShown(expressionTextInput.getRootView())) {
            SystemUtils.hideKeyboard(this, expressionTextInput.getApplicationWindowToken());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.information_release_back:
                FileUtils.deleteDir();
                Bimp.mBmps.clear();
                Bimp.mDrr.clear();
                Bimp.mMax = 0;
                finish();
                break;
            case R.id.rl_information_icon_face:
                if (rl_test.getVisibility() == View.GONE) {
                    rl_test.setVisibility(View.VISIBLE);
                } else if (rl_test.getVisibility() == View.VISIBLE) {
                    rl_test.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_picture:
                if (mGridView.getVisibility() == View.GONE) {
                    mGridView.setVisibility(View.VISIBLE);
                } else if (mGridView.getVisibility() == View.VISIBLE) {
                    mGridView.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_send:
                send();
                break;
        }
    }
    //网络访问进度条弹框
    private ProgressDialog dialog;
    //发布资讯
    private void send() {
        //设置一个progressdialog的弹窗
        dialog = ProgressDialog.show(InformationReleaseActivity.this, null, "正在发布，请稍候...", true, false);
        //获取用户输入标题、内容
        String etContents = expressionTextInput.getText().toString();
        String infoTitle = etInformationTitle.getText().toString();
        final List<File> listfile = new ArrayList<>();

        LogUtils.e("etContents",etContents);

        //上传的图片文件SD卡地址，存放在bimp.mDrr中
        for (int i = 0; i < Bimp.mDrr.size(); i++) {
            listfile.add(new File(Bimp.mDrr.get(i)));
        }

        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                LogUtils.e("INFO",msg.obj.toString());
                if(JSON.parseObject(msg.obj.toString()).getInteger("result") == 11){
                    // 删除缓存
                    //FileUtils.deleteDir();
                    listfile.clear();
                    Bimp.mBmps.clear();
                    Bimp.mDrr.clear();
                    Bimp.mMax = 0;
                    ToastUtil.t(InformationReleaseActivity.this, "发送成功");
                    if (dialog.isShowing())dialog.dismiss();
                    finish();
                }else if(JSON.parseObject(msg.obj.toString()).getInteger("result") == 12){
                    ToastUtil.t(InformationReleaseActivity.this, "发送失败");
                    if (dialog.isShowing())dialog.dismiss();
                }
            }
        };
        //发送信息给服务器
        Map<String, Object> DataMap = new HashMap<>();
        DataMap.put("userid", Integer.valueOf(Login.getLoginInfo("id").toString()));
        DataMap.put("username", Login.getLoginInfo("u_nickname").toString());
        DataMap.put("userhead", Login.getLoginInfo("u_datapath").toString() + Login.getLoginInfo("u_head_url").toString());
        DataMap.put("contents", etContents);
        DataMap.put("title", infoTitle);
        LogUtils.e("DataMap", JSON.toJSONString(DataMap));
        DataMap.put("files", listfile);
        new HttpImpl(DataMap,"http://172.16.1.132:8080/Genealogy/servlet2/info/PublishInfo.xml",HttpImpl.POSTMethd,handler).start();
    }
}
