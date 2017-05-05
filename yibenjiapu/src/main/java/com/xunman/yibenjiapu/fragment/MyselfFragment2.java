package com.xunman.yibenjiapu.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunman.yibenjiapu.ui.LoginActivity;
import com.xunman.yibenjiapu.ui.MyDetailsActivity;
import com.xunman.yibenjiapu.ui.MyIntegralActivity;
import com.xunman.yibenjiapu.ui.MyPrizeActivity;
import com.xunman.yibenjiapu.ui.MyVipActivity;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.ui.SettingActivity;
import com.xunman.yibenjiapu.ui.StationLetterActivity;
import com.xunman.yibenjiapu.utils.BitmapUtils;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;

import java.io.ByteArrayOutputStream;
/**
 * Created by Administrator on 2017/1/9 0009.
 * 使用中
 */

public class MyselfFragment2 extends Fragment implements View.OnClickListener {
    private TextView myself_username, myself_my_prize, myself_my_integral, myself_station_letter, myself_setting, myself_service,myself_my_vip;//,myself_build
    private View mainView;
    //private RelativeLayout llMy;
    private ImageView myselfIcon, iv_icon_vip;//vip图标
    //QQ头像字节流
    private String qqAvatarByte;
    private String qqUsername;
    private Bitmap bitmap;
    private String sur;//姓氏
    private String name;//名字
    private Bitmap bitmap_intenet;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                bitmap_intenet = (Bitmap) msg.obj;
                myselfIcon.setImageBitmap(bitmap_intenet);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap_intenet.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                qqAvatarByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                ShareUtils.putString(getActivity(), "headPic", qqAvatarByte);
            }
        }
    };
    private Intent intent;
    //服务器返回的我的积分商城数据
    private String myIntegral;
    //用于传递我的积分商城数据
    private Bundle bundle;
    private Handler hMyIntegralInfo;

    public static Fragment instance() {

        MyselfFragment2 myselfFragment = new MyselfFragment2();
        return myselfFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
        bundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mainView == null) {

            mainView = inflater.inflate(R.layout.fragment_myself2, container, false);
            intiView();
        } else {
            return mainView;
        }
        if (ShareUtils.getString(getActivity(), "nickName", qqUsername) != null && ShareUtils.getBoolean(getActivity(), "login", false)) {
            myself_username.setText(ShareUtils.getString(getActivity(), "nickName", qqUsername));
        } else if (ShareUtils.getString(getActivity(), "sur", sur) != null && ShareUtils.getString(getActivity(), "name", name) != null
                && ShareUtils.getBoolean(getActivity(), "login", true)) {
            sur = ShareUtils.getString(getActivity(), "sur", sur);
            name = ShareUtils.getString(getActivity(), "name", name);
            qqUsername = sur + name;
            ShareUtils.putString(getActivity(), "nickName", qqUsername);
            myself_username.setText(qqUsername);
        } else {
            myself_username.setText("请登陆");
        }

        if (ShareUtils.getString(getActivity(), "headPic", qqAvatarByte) != null && ShareUtils.getBoolean(getActivity(), "login", false)) {
            qqAvatarByte = ShareUtils.getString(getActivity(), "headPic", qqAvatarByte);
            byte[] bytes = Base64.decode(qqAvatarByte.getBytes(), 1);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            myselfIcon.setImageBitmap(bitmap);
        } else if (ShareUtils.getString(getActivity(), "headUrl", "") != null && ShareUtils.getBoolean(getActivity(), "login", false)) {
            new Thread() {
                @Override
                public void run() {
//                    String s = HttpImplStringTest.Http_Url_Img_Video + ShareUtils.getString(getActivity(), "headUrl", "");
                    String s = HttpImplStringTest.WSURL + ShareUtils.getString(getActivity(), "headUrl", "");
                    LogUtils.e("!!!!!!!!!!!", s);
                    bitmap_intenet = BitmapUtils.getbitmap(s);
                    Message msg = new Message();
                    msg.obj = bitmap_intenet;
                    msg.what = 0x123;
                    handler.sendMessage(msg);
                }
            }.start();
        } else {
            myselfIcon.setImageResource(R.mipmap.header);
        }
        return mainView;
    }

    private void intiView() {
        myself_username = (TextView) mainView.findViewById(R.id.myself_username);
        myself_my_prize = (TextView) mainView.findViewById(R.id.myself_my_prize);
        myself_my_integral = (TextView) mainView.findViewById(R.id.myself_my_integral);
        myself_station_letter = (TextView) mainView.findViewById(R.id.myself_station_letter);
        myself_setting = (TextView) mainView.findViewById(R.id.myself_setting);
        myself_my_vip = (TextView) mainView.findViewById(R.id.myself_my_vip);
//        myself_service = (TextView) mainView.findViewById(R.id.myself_service);
//        myself_build = (TextView) mainView.findViewById(R.id.myself_build);
        //llMy = (RelativeLayout) mainView.findViewById(R.id.ll_my);
        myselfIcon = (ImageView) mainView.findViewById(R.id.myself_icon);
        iv_icon_vip = (ImageView) mainView.findViewById(R.id.iv_icon_vip);

        myself_username.setOnClickListener(this);
        myself_my_prize.setOnClickListener(this);
        myself_my_integral.setOnClickListener(this);
        myself_station_letter.setOnClickListener(this);
        myself_setting.setOnClickListener(this);
        myselfIcon.setOnClickListener(this);
        myself_my_vip.setOnClickListener(this);
//        myself_service.setOnClickListener(this);
//        myself_build.setOnClickListener(this);
        //llMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.myself_username://登陆名
                if (ShareUtils.getString(getActivity(), "nickName", qqUsername) != null && ShareUtils.getBoolean(getActivity(), "login", true)) {
                    //用Bundle携带数据
                    qqUsername = ShareUtils.getString(getActivity(), "nickName", qqUsername);
                    String str = "张人文";
                    //传递nickName参数
                    bundle.putString("nickName", str);
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), MyDetailsActivity.class);
                    getActivity().startActivityForResult(intent, 11);
                } else {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myself_icon://头像
                if (ShareUtils.getString(getActivity(), "nickName", qqUsername) != null && ShareUtils.getBoolean(getActivity(), "login", true)) {
                    intent.setClass(getActivity(), MyDetailsActivity.class);
                    //用Bundle携带数据
//                    qqUsername = ShareUtils.getString(getActivity(), "nickName", qqUsername);
                    qqUsername = "张人文";
                    //传递nickName参数
                    bundle.putString("nickName", qqUsername);
                    intent.putExtras(bundle);
                    getActivity().startActivityForResult(intent, 11);
                } else {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myself_my_prize://我的奖品
                intent.setClass(getActivity(), MyPrizeActivity.class);
                startActivity(intent);
                break;
            case R.id.myself_my_integral://我的积分
//                hMyIntegralInfo = new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        Bundle bund = msg.getData();
//                        if (myIntegral != null) {
//                            //跳转积分商城页面
//                            intent = new Intent(getActivity(), MyIntegralActivity.class);
//                            //传递获取的积分商城数据
//                            intent.putExtras(bund);
//                            startActivity(intent);
//                        } else {
//                            LogUtils.e("myIntegral=========>>", "空了");
//                            getMyIntegral(bundle);
//                        }
//                    }
//                };
                //跳转积分商城页面
                intent = new Intent(getActivity(), MyIntegralActivity.class);
                startActivity(intent);
                break;
            case R.id.myself_station_letter://站内信
                intent.setClass(getActivity(), StationLetterActivity.class);
                startActivity(intent);
                break;
            case R.id.myself_setting://设置
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.myself_my_vip://我的会员
                intent.setClass(getActivity(), MyVipActivity.class);
                startActivity(intent);
                break;
        }
    }

//    /**
//     * 获取积分商城数据
//     *
//     * @param bundleMyIntegral Bundle对象
//     */
//    private void getMyIntegral(final Bundle bundleMyIntegral) {
//        //获取网络数据,积分商城数据
//        Handler hMyIntegral = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                if (msg.what == 0x123) {
//                    myIntegral = msg.obj.toString();
//                    LogUtils.e("积分商城数据:", myIntegral);
//                    bundleMyIntegral.putString("MyIntegralInfo", myIntegral);
//
//                    Message msg1 = new Message();
//                    msg1.setData(bundleMyIntegral);
//                    hMyIntegralInfo.sendMessageDelayed(msg1, 0);
//                }
//            }
//        };
//        Map<String, Object> map = new HashMap<>();
//        new HttpImplStringTest(map, "1111111", hMyIntegral, "POST").start();
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        LogUtils.e("sadfasdfsadf",data.getStringExtra("llll"));
//        ToastUtil.t(getActivity(),data.getStringExtra("llll"));
        if (requestCode == 11 & resultCode == 11) {
            bundle = data.getExtras();
            String nickName = bundle.getString("nickName");
            String qqAvatarByte = bundle.getString("qqAvatarByte");
            if (!nickName.equals("")) {
                myself_username.setText(nickName);
                if (qqAvatarByte != null) {
                    byte[] bytes = Base64.decode(qqAvatarByte.getBytes(), 1);
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    myselfIcon.setImageBitmap(bitmap);
                } else if (ShareUtils.getString(getActivity(), "headPic", qqAvatarByte) != null) {
                    qqAvatarByte = ShareUtils.getString(getActivity(), "headPic", qqAvatarByte);
                    bitmap = BitmapUtils.ByteToBitmap(qqAvatarByte);
                    myselfIcon.setImageBitmap(bitmap);
                } else {
                    myselfIcon.setImageResource(R.mipmap.logo);
                }
            }
            LogUtils.e("++++++", "nickName====" + nickName);
            LogUtils.e("++++++", "qqAvatarByte====" + qqAvatarByte);

        }
    }
}
