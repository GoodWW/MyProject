package com.xunman.yibenjiapu.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回按钮
     */
    private TextView activity_setting_back;
    /**
     * 清除视频缓存
     */
    private TextView settings_cache_tips;
    /**
     * 修改密码
     */
    private TextView settings_change_password;
    /**
     * 检查更新
     */
    private TextView settings_update;
    /**
     * 关于我们
     */
    private TextView settings_about_us;
    /**
     * 退出登录 登陆按钮
     */
    private TextView settings_user_logout;
    private TextView settings_change_phone;

    private ShareUtils sfu;
    private String tag = "SettingActivity";

    private Intent intent;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        intiCheck();
        if (isLogin) {
            settings_user_logout.setText("退出登陆");
        } else {
            settings_user_logout.setText("登  陆");
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        BaseApplication.list.add(this);
    }

    private void intiCheck() {
        settings_cache_tips.setOnClickListener(this);
        settings_change_password.setOnClickListener(this);
        settings_update.setOnClickListener(this);
        settings_about_us.setOnClickListener(this);
        settings_user_logout.setOnClickListener(this);
        activity_setting_back.setOnClickListener(this);
        settings_change_phone.setOnClickListener(this);
    }

    private void init() {
        isLogin = ShareUtils.getBoolean(SettingActivity.this, "login", false);
        settings_change_phone = (TextView) findViewById(R.id.settings_change_phone);
        settings_user_logout = (TextView) findViewById(R.id.settings_user_logout);
        settings_cache_tips = (TextView) findViewById(R.id.settings_cache_tips);
        settings_change_password = (TextView) findViewById(R.id.settings_change_password);
        settings_update = (TextView) findViewById(R.id.settings_update);
        settings_about_us = (TextView) findViewById(R.id.settings_about_us);
        activity_setting_back = (TextView) findViewById(R.id.activity_setting_back);
    }

    /**
     * (non-Javadoc)
     *
     * @see View.OnClickListener#onClick(View)
     */
    @Override
    public void onClick(View view) {
        intent = getIntent();
        switch (view.getId()) {
            case R.id.settings_about_us:// 关于我们
                LogUtils.e(tag, "关于我们");
                intent.setClass(this, AboutUsActivity.class);
                LogUtils.e(tag, "跳转到关于我们界面");
                startActivity(intent);
                break;
            case R.id.settings_update:// 检查更新
                //ToastUtil.t(this, "暂时没有更新！");
                notNewVersionDlgShow(); // 提示当前为最新版本
                break;
            case R.id.settings_change_password:// 修改密码
                LogUtils.e(tag, "修改密码");
                if (ShareUtils.getBoolean(SettingActivity.this, "login", false)) {
                    intent.setClass(SettingActivity.this, LoginChPaActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(this, ChangePassWordActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.settings_cache_tips:// 清楚视频缓存
                ToastUtil.t(this, "清理成功！");
                break;
            case R.id.settings_user_logout:// 退出登陆
                LogUtils.e(tag, "退出登陆");
                if (settings_user_logout.getText().toString().equals("退出登陆")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this)
                            .setTitle("确认退出")
                            .setIcon(R.drawable.close)
                            .setMessage("您确认需要退出吗？\n将删除所有信息");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            ShareUtils.deleAll(SettingActivity.this);
//                            ShareUtils.putBoolean(SettingActivity.this, StaticClass.SHARE_IS_FIRST, false);
                            ShareUtils.deleAll(SettingActivity.this);
                            ShareUtils.putBoolean(SettingActivity.this, "isFirst", false);
                            ToastUtil.t(SettingActivity.this, "退出登陆成功！");
                            intent.setClass(SettingActivity.this, LoginActivity.class);
                            startActivity(intent);
                            for (int i = 0; i < BaseApplication.list.size(); i++) {
                                LogUtils.e("iiiiiiiiiii", "i====" + i);
                                BaseApplication.list.get(i).finish();
                            }
                            BaseApplication.list.clear();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.t(SettingActivity.this, "暂时不退出了！");
                        }
                    }).create().show();
                } else {
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.settings_change_phone:// 手机号变更
                if (ShareUtils.getBoolean(SettingActivity.this, "login", false)) {
                    intent.setClass(this, ChangePhoneActivity.class);
                } else {
                    intent.setClass(this, LoginActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.activity_setting_back:// 返回按钮
                finish();
                break;
        }
    }

    /**
     * 提示当前为最新版本
     */
    private void notNewVersionDlgShow() {
        int verCode = getVerCode(this);
        String verName = getVerName(this);
        //内部测试版本Alpha
        //外部测试版本Beta
        String str = "当前版本Alpha:" + verName + "." + verCode + ",\n已是最新版,无需更新!";
        Dialog dialog = new android.app.AlertDialog.Builder(this).setTitle("软件更新")
                .setMessage(str)// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public int getVerCode(Context context) {
        int verCode = -1;
        try {
            //注意："com.example.try_downloadfile_progress"对应AndroidManifest.xml里的package="……"部分
            verCode = context.getPackageManager().getPackageInfo(
                    "com.xunman.yibenjiapu.ui", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.xunman.yibenjiapu.ui", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verName;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Setting Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
