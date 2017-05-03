package com.xunman.yibenjiapu.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.xunman.yibenjiapu.utils.ShareUtils;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/2 09:32
 * 包名：com.xunman.yibenjiapu.ui
 * 描述：  放大头像
 */
public class ImageShower extends Activity {
    private ImageView ivHeadPortraitMax;
    private Bitmap bitmap;
    private String qqAvatarByte;
    private String bigImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);


        ivHeadPortraitMax = (ImageView) findViewById(R.id.iv_head_portrait_max);
        if (ShareUtils.getString(this, "headPic", qqAvatarByte) != null) {
            byte[] bytes = Base64.decode(ShareUtils.getString(this, "headPic", qqAvatarByte).getBytes(), 1);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            ivHeadPortraitMax.setImageBitmap(bitmap);
        } else {
            ivHeadPortraitMax.setImageResource(R.mipmap.header);
        }

        final ImageLoadingDialog dialog = new ImageLoadingDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000 * 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        finish();
        return true;
    }

}
