package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xunman.yibenjiapu.bean.UserInfoTest;
import com.xunman.yibenjiapu.menu.BottomMenu;
import com.xunman.yibenjiapu.utils.ShareUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/2 09:32
 * 包名：com.xunman.yibenjiapu.ui
 * 描述：  我的信息页面
 */

public class MyDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvDetailsBack;
    private RelativeLayout rlMyUsername;
    private RelativeLayout rlHeadPortrait;
    private ImageView ivHeadPortrait;
    private TextView tvMyUsername;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    // 裁剪后图片的宽(X)和高(Y),50 X 50。
    private static int output_X = 80;
    private static int output_Y = 80;
    private BottomMenu menuWindow;
    private Intent intent;
    //用户个人信息
    private UserInfoTest userInfoTest;
    private String nickName;
    private String qqAvatarByte;
    private SharedPreferences sharedPreferences;
    private Bitmap bitmap;

    private TextView tvMyTelephone;
    private TextView tvMyGender;
    private TextView tvMyFamilyAddress;
    private String telephone;
    private String gender;
    private String familyAddress;
    private Bundle bundle;
    private Bitmap bitmap_intenet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_my);
        intent = getIntent();
        initView();
        //用Bundle携带数据
        bundle = intent.getExtras();
        if (bundle.getString("nickName") != null) {
            //传递name参数为tinyphp
            nickName = bundle.getString("nickName");
            tvMyUsername.setText(nickName);
        } else if (ShareUtils.getString(MyDetailsActivity.this, "nickName", nickName) != null) {
            nickName = ShareUtils.getString(MyDetailsActivity.this, "nickName", nickName);
            tvMyUsername.setText(nickName);
        } else {
            tvMyUsername.setText("未设置昵称");
        }

        if (ShareUtils.getString(MyDetailsActivity.this, "headPic", qqAvatarByte) != null) {
            byte[] bytes = Base64.decode(ShareUtils.getString(this, "headPic", qqAvatarByte).getBytes(), 1);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            ivHeadPortrait.setImageBitmap(bitmap);
        } else {
            ivHeadPortrait.setImageResource(R.mipmap.header);
        }

        if (ShareUtils.getString(MyDetailsActivity.this, "telephone", telephone) != null) {
            telephone = ShareUtils.getString(MyDetailsActivity.this, "telephone", telephone);
            tvMyTelephone.setText(telephone);
        }
        if (ShareUtils.getString(MyDetailsActivity.this, "gender", gender) != null) {
            gender = ShareUtils.getString(MyDetailsActivity.this, "gender", gender);
            tvMyGender.setText(gender);
        }
        if (ShareUtils.getString(MyDetailsActivity.this, "familyAddress", familyAddress) != null) {
            familyAddress = ShareUtils.getString(MyDetailsActivity.this, "familyAddress", familyAddress);
            tvMyFamilyAddress.setText(familyAddress);
        }
    }

    private void initView() {
        tvDetailsBack = (TextView) findViewById(R.id.tv_my_details_back);
        tvDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickName = tvMyUsername.getText().toString().trim();
                intent.putExtra("nickName", nickName);
                intent.putExtra("qqAvatarByte", qqAvatarByte);
                MyDetailsActivity.this.setResult(11, intent);
                finish();
            }
        });

        rlMyUsername = (RelativeLayout) findViewById(R.id.rl_my_username);
        rlMyUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MyDetailsActivity.this, ChangeUsernameActivity.class);
                bundle.putString("tvMyUsername", tvMyUsername.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });
        rlHeadPortrait = (RelativeLayout) findViewById(R.id.rl_head_portrait);
        rlHeadPortrait.setOnClickListener(this);
        ivHeadPortrait = (ImageView) findViewById(R.id.iv_head_portrait);
        tvMyUsername = (TextView) findViewById(R.id.tv_my_username);

        tvMyTelephone = (TextView) findViewById(R.id.tv_my_telephone);
        tvMyGender = (TextView) findViewById(R.id.tv_my_gender);
        tvMyFamilyAddress = (TextView) findViewById(R.id.tv_my_familyAddress);
    }

    @Override
    public void onClick(View view) {
        menuWindow = new BottomMenu(MyDetailsActivity.this, clickListener);
        menuWindow.show();
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
//        intent.setClass(this, MainActivity.class);
//        startActivity(intent);
        // super.onBackPressed();
        nickName = tvMyUsername.getText().toString().trim();
        intent.putExtra("nickName", nickName);
        intent.putExtra("qqAvatarByte", qqAvatarByte);
        MyDetailsActivity.this.setResult(11, intent);
        finish();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_album:
                    Toast.makeText(MyDetailsActivity.this, "相册", Toast.LENGTH_SHORT).show();
                    choseHeadImageFromGallery();
                    break;
                case R.id.btn_photograph:
                    Toast.makeText(MyDetailsActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                    choseHeadImageFromCameraCapture();
                    break;
            }
        }
    };

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
                break;
        }
        if (requestCode == 1 & resultCode == 1) {
            String nickName1 = intent.getExtras().getString("nickName1");
            tvMyUsername.setText(nickName1);
        }
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int mScreenWidth = metric.widthPixels;  // 屏幕宽度（像素）
        int mScreenHeight = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;
        int mDipWidth = (int) (mScreenWidth / density);//设备独立宽像素
        int mDipHeight = (int) (mScreenHeight / density);//设备独立高像素
        output_X = mDipWidth;
        output_Y = mDipWidth;

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            qqAvatarByte = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            //添加信息到本地share存储
            addToShare();
            ivHeadPortrait.setImageBitmap(photo);
        }
    }

    //添加信息到本地share存储
    private void addToShare() {
        ShareUtils.putString(this, "headPic", qqAvatarByte);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    // 我的头像点击放大
    public void show_click(View v) {
        intent.setClass(this, ImageShower.class);
        bundle.putString("bigImg", qqAvatarByte);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
