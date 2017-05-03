package com.xunman.yibenjiapu.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.User_Relation_info;
import com.xunman.yibenjiapu.menu.ArcLayout;
import com.xunman.yibenjiapu.menu.ArcMenu;
import com.xunman.yibenjiapu.utils.BitmapUtils;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.io.ByteArrayOutputStream;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/4/10 0010 13:31
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  家谱树界面
 */

public class JiaPuTreeTestActivity extends AppCompatActivity implements View.OnClickListener{
    //返回按钮
    private TextView tvTreeBack;
    //菜单整体布局
    private RelativeLayout rlMy;
    private RelativeLayout rlYy;
    private RelativeLayout rlNainai;
    private RelativeLayout rlBaba;
    private RelativeLayout rlMama;
    private RelativeLayout rlXifu;
    private RelativeLayout rlSon;
    private RelativeLayout rlSonXifu;
    //菜单
    private ArcMenu amMy;
    private ArcMenu amMyWife;
    private ArcMenu amTreeAncestorMan;
    private ArcMenu amTreeAncestorWoman;
    private ArcMenu amTreeGrandpa;
    private ArcMenu amTreeGrandma;
    private ArcMenu amTreeFather;
    private ArcMenu amTreeMother;
    private ArcMenu amTreeSonDaughter;
    private ArcMenu amTreeSon;
    //名字
    private TextView tvMy;
    private TextView tvMyWife;
//    private TextView tvAncestorMan;
//    private TextView tvAncestorWoman;
    private TextView tvGrandpa;
    private TextView tvGrandma;
    private TextView tvFather;
    private TextView tvMother;
    private TextView tvMySonDaughter;
    private TextView tvMySon;
    //关系
    private TextView tvMyWifeRelation;
    private TextView tvAncestorManRelation;
    private TextView tvAncestorWomanRelation;
    private TextView tvGrandpaRelation;
    private TextView tvGrandmaRelation;
    private TextView tvFatherRelation;
    private TextView tvMotherRelation;
    private TextView tvMySonDaughterRelation;
    private TextView tvMySonRelation;

    //我的头像
    private String qqAvatarByte;
    private Bitmap bitmap;
    private String qqUsername;//QQ昵称
    private String sur;//姓氏
    private String name;//名字

    //TextView无数据状态文本
    private String strMe = "";
    private String str = "未设置";

    private Intent intent;
    private Bundle bundle;
    //bundle需要传递的头像字节流
    private String headPortraitByte;
    /**
     * 服务器返回的用户家谱树关系信息实体类
     */
    private User_Relation_info user_relation_info;
//    /**
//     * 判断是否拿到了返回信息
//     *      true:  获取了用户家谱树关系信息
//     *      false: 没有
//     */
//    private Boolean isUser_Relation_info;
    //祖父到爷爷间的线
//    private LinearLayout llLine1;
    //爷爷到爸爸间的线
    private LinearLayout llLine2;
    //爸爸到我之间的线
    private LinearLayout llLine3;
    //我到儿子之间的线
    private LinearLayout llLine4;

    //定义菜单按钮数组
    private static final int[] ITEM_DRAWABLES = { R.drawable.man, R.drawable.mmmmmmm1 ,
            R.drawable.mmmmmmm1, R.drawable.mmmmmmm1, R.drawable.mmmmmmm1, R.drawable.man };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jia_pu_tree_test);

        intent = getIntent();
        bundle = intent.getExtras();
        //取得个人关系人物详细信息返回数据
        String str = bundle.getString("str", "4321");
        LogUtils.e("返回user_relation_info", str);
        user_relation_info = JSON.parseObject(str, User_Relation_info.class);
        String s = user_relation_info.getInfo().getG_father().getName();
        LogUtils.e("",s);

        //设置焦点，确认进入界面显示"我"
        rlMy = (RelativeLayout) findViewById(R.id.rl_my);
        rlMy.setFocusable(true);
        rlMy.setFocusableInTouchMode(true);
        rlMy.requestFocus();

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int mScreenWidth = metric.widthPixels;  // 屏幕宽度（像素）
        int mScreenHeight = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;
        int mDipWidth = (int)(mScreenWidth/density);//设备独立宽像素
        int mDipHeight = (int)(mScreenHeight/density);//设备独立高像素

        ArcLayout.mChildPadding = px2dip(this,mScreenWidth/36*(int)density);

        rlXifu = (RelativeLayout) findViewById(R.id.rl_xifu);
        ViewGroup.LayoutParams params = rlXifu.getLayoutParams();

        ViewGroup.MarginLayoutParams marginParams;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            //基于View本身原有的布局参数对象
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        marginParams.setMargins(-mDipWidth/2,0,0,0);
        rlXifu.setLayoutParams(marginParams);

        initView();

        addMenu();

        BaseApplication.list.add(this);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 初始化控件
     */
    private void initView(){
        tvTreeBack = (TextView) findViewById(R.id.tv_tree_back);
        tvTreeBack.setOnClickListener(this);
        //人物名字的textview
        tvMy = (TextView) findViewById(R.id.tv_tree_my);
        tvMyWife = (TextView) findViewById(R.id.tv_tree_my_girl_name);
//        tvAncestorMan = (TextView) findViewById(R.id.tv_tree_zu_xian_man_name);
//        tvAncestorWoman = (TextView) findViewById(R.id.tv_tree_zu_xian_woman_name);
        tvGrandpa = (TextView) findViewById(R.id.tv_tree_ye_ye_name);
        tvGrandma = (TextView) findViewById(R.id.tv_tree_nai_nai_name);
        tvFather = (TextView) findViewById(R.id.tv_tree_ba_ba_name);
        tvMother = (TextView) findViewById(R.id.tv_tree_ma_ma_name);
        tvMySonDaughter = (TextView) findViewById(R.id.tv_tree_son_girl_name);
        tvMySon = (TextView) findViewById(R.id.tv_tree_son_name);
        //人物关系的textview
        tvMyWifeRelation = (TextView) findViewById(R.id.tv_tree_my_girl);
//        tvAncestorManRelation = (TextView) findViewById(R.id.tv_tree_zu_xian_man);
//        tvAncestorWomanRelation = (TextView) findViewById(R.id.tv_tree_zu_xian_woman);
        tvGrandpaRelation = (TextView) findViewById(R.id.tv_tree_ye_ye);
        tvGrandmaRelation = (TextView) findViewById(R.id.tv_tree_nai_nai);
        tvFatherRelation = (TextView) findViewById(R.id.tv_tree_ba_ba);
        tvMotherRelation = (TextView) findViewById(R.id.tv_tree_ma_ma);
        tvMySonDaughterRelation = (TextView) findViewById(R.id.tv_tree_son_girl);
        tvMySonRelation = (TextView) findViewById(R.id.tv_tree_son);
        //控件间线条的textview
        llLine2 = (LinearLayout) findViewById(R.id.ll_line2);
        llLine3 = (LinearLayout) findViewById(R.id.ll_line3);
        llLine4 = (LinearLayout) findViewById(R.id.ll_line4);
        //菜单整体布局
        rlYy = (RelativeLayout) findViewById(R.id.rl_yy);
        rlNainai = (RelativeLayout) findViewById(R.id.rl_nainai);
        rlBaba = (RelativeLayout) findViewById(R.id.rl_baba);
        rlMama = (RelativeLayout) findViewById(R.id.rl_mama);
        rlSon = (RelativeLayout) findViewById(R.id.rl_son);
        rlSonXifu = (RelativeLayout) findViewById(R.id.rl_son_xifu);

        if (ShareUtils.getString(this, "nickName", qqUsername)!= null&&ShareUtils.getBoolean(this,"login",true)){
            tvMy.setText(ShareUtils.getString(this, "nickName", qqUsername));
        }else if(ShareUtils.getString(this, "sur", sur) != null && ShareUtils.getString(this, "name", name) != null
                &&ShareUtils.getBoolean(this,"login",true)){
            sur = ShareUtils.getString(this, "sur", sur);
            name = ShareUtils.getString(this, "name", name);
            qqUsername = sur + name;
            ShareUtils.putString(this, "nickName", qqUsername);
            tvMy.setText(qqUsername);
        }else {
            strMe = "我(<font color='#3333FF'>未登陆</font>)";
            tvMy.setText(Html.fromHtml(strMe));
        }
        //判断服务器返回信息
        isServerReturnInfo();

        tvMy.setOnClickListener(this);
        tvMyWife.setOnClickListener(this);
//        tvAncestorMan.setOnClickListener(this);
//        tvAncestorWoman.setOnClickListener(this);
        tvGrandpa.setOnClickListener(this);
        tvGrandma.setOnClickListener(this);
        tvFather.setOnClickListener(this);
        tvMother.setOnClickListener(this);
        tvMySonDaughter.setOnClickListener(this);
        tvMySon.setOnClickListener(this);
    }

    /**
     * 判断服务器是否返回有信息
     * 如果有就显示该控件并设置上去信息
     */
    private void isServerReturnInfo(){
        if(user_relation_info.getInfo().getG_father().getName() != null){
            rlYy.setVisibility(View.VISIBLE);
            llLine2.setVisibility(View.VISIBLE);
            tvGrandpa.setText(user_relation_info.getInfo().getG_father().getName());
        }
        if(user_relation_info.getInfo().getG_mather().getName() != null){
            rlNainai.setVisibility(View.VISIBLE);
            llLine2.setVisibility(View.VISIBLE);
            tvGrandma.setText(user_relation_info.getInfo().getG_mather().getName());
        }
        if(user_relation_info.getInfo().getChild().get(0).getName() != null){
            rlSon.setVisibility(View.VISIBLE);
            llLine4.setVisibility(View.VISIBLE);
            tvMySon.setText(user_relation_info.getInfo().getChild().get(0).getName());
        }
        if(user_relation_info.getInfo().getFather().getName() != null){
            rlBaba.setVisibility(View.VISIBLE);
            llLine3.setVisibility(View.VISIBLE);
            tvFather.setText(user_relation_info.getInfo().getFather().getName());
        }
        if(user_relation_info.getInfo().getMather().getName() != null){
            rlMama.setVisibility(View.VISIBLE);
            llLine3.setVisibility(View.VISIBLE);
            tvMother.setText(user_relation_info.getInfo().getMather().getName());
        }
    }

    private void addMenu(){
        amMy = (ArcMenu) findViewById(R.id.tree_my);
        if (ShareUtils.getString(this, "headPic", qqAvatarByte) != null && ShareUtils.getBoolean(this, "login", true)) {
            qqAvatarByte = ShareUtils.getString(this, "headPic", qqAvatarByte);
            byte[] bytes = Base64.decode(qqAvatarByte.getBytes(), 1);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            amMy.mHintView.setImageBitmap(bitmap);
        } else {
            amMy.mHintView.setImageResource(R.mipmap.man);
        }
        initArcMenu(amMy, ITEM_DRAWABLES);

        amMyWife = (ArcMenu) findViewById(R.id.tree_my_girl);
        amMyWife.mHintView.setImageResource(R.mipmap.woman);
        initArcMenu(amMyWife, ITEM_DRAWABLES);

//        amTreeAncestorMan = (ArcMenu) findViewById(R.id.tree_zu_xian_man);
//        amTreeAncestorMan.mHintView.setImageResource(R.mipmap.old_man);
//        initArcMenu(amTreeAncestorMan, ITEM_DRAWABLES);
//
//        amTreeAncestorWoman = (ArcMenu) findViewById(R.id.tree_zu_xian_woman);
//        amTreeAncestorWoman.mHintView.setImageResource(R.mipmap.old_woman);
//        initArcMenu(amTreeAncestorWoman, ITEM_DRAWABLES);

        amTreeGrandpa = (ArcMenu) findViewById(R.id.tree_ye_ye);
        amTreeGrandpa.mHintView.setImageResource(R.mipmap.old_man);
        initArcMenu(amTreeGrandpa, ITEM_DRAWABLES);

        amTreeGrandma = (ArcMenu) findViewById(R.id.tree_nai_nai);
        amTreeGrandma.mHintView.setImageResource(R.mipmap.old_woman);
        initArcMenu(amTreeGrandma, ITEM_DRAWABLES);

        amTreeFather = (ArcMenu) findViewById(R.id.tree_ba_ba);
        amTreeFather.mHintView.setImageResource(R.mipmap.wrinkly_man);
        initArcMenu(amTreeFather, ITEM_DRAWABLES);

        amTreeMother = (ArcMenu) findViewById(R.id.tree_ma_ma);
        amTreeMother.mHintView.setImageResource(R.mipmap.wrinkly_woman);
        initArcMenu(amTreeMother, ITEM_DRAWABLES);

        amTreeSon = (ArcMenu) findViewById(R.id.tree_son);
        amTreeSon.mHintView.setImageResource(R.mipmap.boy);
        initArcMenu(amTreeSon, ITEM_DRAWABLES);

        amTreeSonDaughter= (ArcMenu) findViewById(R.id.tree_son_girl);
        amTreeSonDaughter.mHintView.setImageResource(R.mipmap.gril);
        initArcMenu(amTreeSonDaughter, ITEM_DRAWABLES);
    }

    private void initArcMenu(ArcMenu menu,int[] itemDrawables) {
        final int itemCount = itemDrawables.length;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(this);
            if(i <= 4 & i >0){
                item.setVisibility(View.INVISIBLE);
            }
            item.setImageResource(itemDrawables[i]);
            final int position = i;
            switch (menu.getId()){
                //我
                case R.id.tree_my:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false) || !tvMy.getText().toString().equals(Html.fromHtml(strMe).toString())){
                                        //  获取头像转换为字节流
                                        amMy.mHintView.setDrawingCacheEnabled(true);
//                                        Bitmap obmp = Bitmap.createBitmap(amMy.mHintView.getDrawingCache());
//                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                                        obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//                                        headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                        headPortraitByte = BitmapUtils.imageViewToByte(amMy.mHintView);
                                        amMy.mHintView.setDrawingCacheEnabled(false);
                                        //  获取昵称
                                        String strMy = tvMy.getText().toString();
                                        //  获取性别
                                        String strMysex = user_relation_info.getInfo().getMe().getSex();
                                        //  传递到查看信息页面
                                        bundle.putString("headPortraitByte",headPortraitByte);
                                        bundle.putString("strName", strMy);
                                        bundle.putString("relation", "我");
                                        bundle.putString("sex", strMysex);
                                        intent.putExtras(bundle);
                                        intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                        startActivity(intent);
                                    }else {
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                //媳妇
                case R.id.tree_my_girl:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvMyWife.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strMyWifeRelation = tvMyWifeRelation.getText().toString();
                                            bundle.putString("relation", strMyWifeRelation.substring(0,strMyWifeRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strMyWifeRelation = tvMyWifeRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amMyWife.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amMyWife.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amMyWife.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strMyWife = tvMyWife.getText().toString();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strMyWifeRelation.substring(0, strMyWifeRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strMyWife);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
//                //男祖先
//                case R.id.tree_zu_xian_man:
//                    menu.addItem(item, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            switch (position){
//                                case 0:
//                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case 5:
//                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
//                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
//                                        startActivity(intent);
//                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
//                                        if (tvAncestorMan.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
//                                            //传送"关系"信息到添加信息界面
//                                            String strAncestorManRelation = tvAncestorManRelation.getText().toString();
//                                            bundle.putString("relation", strAncestorManRelation.substring(0,strAncestorManRelation.length()-1));
//                                            intent.putExtras(bundle);
//                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
//                                            startActivity(intent);
//                                        }else{      //已经有了这个关系人物的基本信息
//                                            //获取该关系人物的"关系"
//                                            String strAncestorManRelation = tvAncestorManRelation.getText().toString();
//                                            //获取该关系人物的"头像"并转换成字节流
//                                            amTreeAncestorMan.mHintView.setDrawingCacheEnabled(true);
//                                            Bitmap obmp = Bitmap.createBitmap(amTreeAncestorMan.mHintView.getDrawingCache());
//                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
//                                            amTreeAncestorMan.mHintView.setDrawingCacheEnabled(false);
//                                            //获取该关系人物的"昵称"
//                                            String strAncestorMan = tvAncestorMan.getText().toString();
//                                            //传递到查看信息界面
//                                            bundle.putString("relation", strAncestorManRelation.substring(0, strAncestorManRelation.length() - 1));
//                                            bundle.putString("headPortraitByte", headPortraitByte);
//                                            bundle.putString("strName", strAncestorMan);
//                                            intent.putExtras(bundle);
//                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
//                                            startActivity(intent);
//                                        }
//                                    }
//                                    break;
//                            }
//                        }
//                    });
//                    break;
//                //女祖先
//                case R.id.tree_zu_xian_woman:
//                    menu.addItem(item, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            switch (position){
//                                case 0:
//                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case 5:
//                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
//                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
//                                        startActivity(intent);
//                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
//                                        if (tvAncestorWoman.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
//                                            //传送"关系"信息到添加信息界面
//                                            String strAncestorWomanRelation = tvAncestorWomanRelation.getText().toString();
//                                            bundle.putString("relation", strAncestorWomanRelation.substring(0,strAncestorWomanRelation.length()-1));
//                                            intent.putExtras(bundle);
//                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
//                                            startActivity(intent);
//                                        }else{      //已经有了这个关系人物的基本信息
//                                            //获取该关系人物的"关系"
//                                            String strAncestorWomanRelation = tvAncestorWomanRelation.getText().toString();
//                                            //获取该关系人物的"头像"并转换成字节流
//                                            amTreeAncestorWoman.mHintView.setDrawingCacheEnabled(true);
//                                            Bitmap obmp = Bitmap.createBitmap(amTreeAncestorWoman.mHintView.getDrawingCache());
//                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
//                                            amTreeAncestorWoman.mHintView.setDrawingCacheEnabled(false);
//                                            //获取该关系人物的"昵称"
//                                            String strAncestorWoman = tvAncestorWoman.getText().toString();
//                                            //传递到查看信息界面
//                                            bundle.putString("relation", strAncestorWomanRelation.substring(0, strAncestorWomanRelation.length() - 1));
//                                            bundle.putString("headPortraitByte", headPortraitByte);
//                                            bundle.putString("strName", strAncestorWoman);
//                                            intent.putExtras(bundle);
//                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
//                                            startActivity(intent);
//                                        }
//                                    }
//                                    break;
//                            }
//                        }
//                    });
//                    break;
                //爷爷
                case R.id.tree_ye_ye:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvGrandpa.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strGrandpaRelation = tvGrandpaRelation.getText().toString();
                                            bundle.putString("relation", strGrandpaRelation.substring(0,strGrandpaRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strGrandpaRelation = tvGrandpaRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amTreeGrandpa.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amTreeGrandpa.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amTreeGrandpa.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strMyGrandpa = tvGrandpa.getText().toString();
                                            //  获取性别
                                            String strSex = user_relation_info.getInfo().getG_father().getSex();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strGrandpaRelation.substring(0, strGrandpaRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strMyGrandpa);
                                            bundle.putString("sex", strSex);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                //奶奶
                case R.id.tree_nai_nai:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvGrandma.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strGrandmoRelation = tvGrandmaRelation.getText().toString();
                                            bundle.putString("relation", strGrandmoRelation.substring(0,strGrandmoRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strGrandmoRelation = tvGrandmaRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amTreeGrandma.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amTreeGrandma.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amTreeGrandma.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strGrandma = tvGrandma.getText().toString();
                                            //  获取性别
                                            String strSex = user_relation_info.getInfo().getG_mather().getSex();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strGrandmoRelation.substring(0, strGrandmoRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strGrandma);
                                            bundle.getString("sex", strSex);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                //爸爸
                case R.id.tree_ba_ba:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvFather.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strFatherRelation = tvFatherRelation.getText().toString();
                                            bundle.putString("relation", strFatherRelation.substring(0,strFatherRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strFatherRelation = tvFatherRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amTreeFather.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amTreeFather.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amTreeFather.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strFather = tvFather.getText().toString();
                                            //  获取性别
                                            String strSex = user_relation_info.getInfo().getFather().getSex();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strFatherRelation.substring(0, strFatherRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strFather);
                                            bundle.getString("sex", strSex);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                //妈妈
                case R.id.tree_ma_ma:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvMother.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strMotherRelation = tvMotherRelation.getText().toString();
                                            bundle.putString("relation", strMotherRelation.substring(0,strMotherRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strMotherRelation = tvMotherRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amTreeMother.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amTreeMother.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amTreeMother.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strMother = tvMother.getText().toString();
                                            //  获取性别
                                            String strSex = user_relation_info.getInfo().getMather().getSex();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strMotherRelation.substring(0, strMotherRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strMother);
                                            bundle.putString("sex", strSex);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                //儿子
                case R.id.tree_son:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvMySon.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strSonRelation = tvMySonRelation.getText().toString();
                                            bundle.putString("relation", strSonRelation.substring(0,strSonRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strSonRelation = tvMySonRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amTreeSon.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amTreeSon.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amTreeSon.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strSon = tvMySon.getText().toString();
                                            //  获取性别
                                            String strSex = user_relation_info.getInfo().getChild().get(0).getSex();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strSonRelation.substring(0, strSonRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strSon);
                                            bundle.putString("sex", strSex);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                //儿媳
                case R.id.tree_son_girl:
                    menu.addItem(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (position){
                                case 0:
                                    Toast.makeText(JiaPuTreeTestActivity.this, "刷新数据" + position, Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    if (!ShareUtils.getBoolean(JiaPuTreeTestActivity.this, "login", false)){    //用户未登陆状态，跳转登陆界面
                                        intent.setClass(JiaPuTreeTestActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else {     //用户登陆状态下，判断用户是否添加了这个关系人物的基本信息
                                        if (tvMySonDaughter.getText().toString().equals(str)) {    //没有添加这个关系人物的基本信息
                                            //传送"关系"信息到添加信息界面
                                            String strSonDaughterRelation = tvMySonDaughterRelation.getText().toString();
                                            bundle.putString("relation", strSonDaughterRelation.substring(0,strSonDaughterRelation.length()-1));
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, AddInformationRelativeActivity.class);
                                            startActivity(intent);
                                        }else{      //已经有了这个关系人物的基本信息
                                            //获取该关系人物的"关系"
                                            String strSonDaughterRelation = tvMySonDaughterRelation.getText().toString();
                                            //获取该关系人物的"头像"并转换成字节流
                                            amTreeSon.mHintView.setDrawingCacheEnabled(true);
                                            Bitmap obmp = Bitmap.createBitmap(amTreeSon.mHintView.getDrawingCache());
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                                            headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                                            amTreeSon.mHintView.setDrawingCacheEnabled(false);
                                            //获取该关系人物的"昵称"
                                            String strSonDaughter = tvMySonDaughter.getText().toString();
                                            //传递到查看信息界面
                                            bundle.putString("relation", strSonDaughterRelation.substring(0, strSonDaughterRelation.length() - 1));
                                            bundle.putString("headPortraitByte", headPortraitByte);
                                            bundle.putString("strName", strSonDaughter);
                                            intent.putExtras(bundle);
                                            intent.setClass(JiaPuTreeTestActivity.this, DetailsActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_tree_back:
                finish();
                break;
            case R.id.tv_tree_my:
                if (ShareUtils.getBoolean(this, "login", false) || !tvMy.getText().toString().equals(Html.fromHtml(strMe).toString())){
                    intent.setClass(JiaPuTreeTestActivity.this, JiaPuTreeTestActivity.class);
                    amMy.mHintView.setDrawingCacheEnabled(true);
                    Bitmap obmp = Bitmap.createBitmap(amMy.mHintView.getDrawingCache());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                    headPortraitByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                    bundle.putString("headPortraitByte",headPortraitByte);
                    amMy.mHintView.setDrawingCacheEnabled(false);
                    String stvMy = tvMy.getText().toString();
                    bundle.putString("stvMy", stvMy);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tree_my_girl_name:
                //ToastUtil.t(this, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvMyWife.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strMyWifeRelation = tvMyWifeRelation.getText().toString();
                    bundle.putString("relation", strMyWifeRelation.substring(0,strMyWifeRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
//            case R.id.tv_tree_zu_xian_man_name:
//                if (!ShareUtils.getBoolean(this, "login", false)){
//                    intent.setClass(this, LoginActivity.class);
//                    startActivity(intent);
//                }else if (tvFather.getText().toString().equals(str)){
//                    ToastUtil.t(this, "请先填写父亲信息");
//                }else if(tvGrandpa.getText().toString().equals(str)){
//                    ToastUtil.t(this, "请先填写爷爷信息");
//                }else if (tvAncestorMan.getText().toString().equals(str)){
//                    //传送关系到下一个界面
//                    //Bundle bundle = intent.getExtras();
//                    String strAncestorManRelation = tvAncestorManRelation.getText().toString();
//                    bundle.putString("relation", strAncestorManRelation.substring(0,strAncestorManRelation.length()-1));
//                    intent.putExtras(bundle);
//                    intent.setClass(this, AddInformationRelativeActivity.class);
//                    startActivity(intent);
//                }
//                break;
//            case R.id.tv_tree_zu_xian_woman_name:
//                if (!ShareUtils.getBoolean(this, "login", false)){
//                    intent.setClass(this, LoginActivity.class);
//                    startActivity(intent);
//                }else if (tvFather.getText().toString().equals(str)){
//                    ToastUtil.t(this, "请先填写父亲信息");
//                }else if(tvGrandpa.getText().toString().equals(str)){
//                    ToastUtil.t(this, "请先填写爷爷信息");
//                }else if(tvAncestorWoman.getText().toString().equals(str)){
//                    //传送关系到下一个界面
//                    //Bundle bundle = intent.getExtras();
//                    String strAncestorWomanRelation = tvAncestorWomanRelation.getText().toString();
//                    bundle.putString("relation", strAncestorWomanRelation.substring(0,strAncestorWomanRelation.length()-1));
//                    intent.putExtras(bundle);
//                    intent.setClass(this, AddInformationRelativeActivity.class);
//                    startActivity(intent);
//                }
//                break;
            case R.id.tv_tree_ye_ye_name:
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvFather.getText().toString().equals(str)){
                    ToastUtil.t(this, "请先填写父亲信息");
                }else if (tvGrandpa.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strGrandpaRelation = tvGrandpaRelation.getText().toString();
                    bundle.putString("relation", strGrandpaRelation.substring(0,strGrandpaRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tree_nai_nai_name:
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvFather.getText().toString().equals(str)){
                    ToastUtil.t(this, "请先填写父亲信息");
                }else if (tvGrandma.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strGrandmaRelation = tvGrandmaRelation.getText().toString();
                    bundle.putString("relation", strGrandmaRelation.substring(0,strGrandmaRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tree_ba_ba_name:
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvFather.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strFatherRelation = tvFatherRelation.getText().toString();
                    bundle.putString("relation", strFatherRelation.substring(0,strFatherRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tree_ma_ma_name:
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvMother.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strMotherRelation = tvMotherRelation.getText().toString();
                    bundle.putString("relation", strMotherRelation.substring(0,strMotherRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tree_son_girl_name:
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvMySon.getText().toString().equals(str)){
                    ToastUtil.t(this, "请先填写儿子信息");
                }else if (tvMySonDaughter.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strMySonDaughterRelation = tvMySonDaughterRelation.getText().toString();
                    bundle.putString("relation", strMySonDaughterRelation.substring(0,strMySonDaughterRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tree_son_name:
                if (!ShareUtils.getBoolean(this, "login", false)){
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                }else if (tvMySon.getText().toString().equals(str)){
                    //传送关系到下一个界面
                    //Bundle bundle = intent.getExtras();
                    String strMySonRelation = tvMySonRelation.getText().toString();
                    bundle.putString("relation", strMySonRelation.substring(0,strMySonRelation.length()-1));
                    intent.putExtras(bundle);
                    intent.setClass(this, AddInformationRelativeActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
