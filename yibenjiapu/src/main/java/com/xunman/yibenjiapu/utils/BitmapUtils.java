package com.xunman.yibenjiapu.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BitmapUtils {

    public static String TAG = "UTIL";

    public static Bitmap getbitmap(String imageUri) {
        Log.v(TAG, "getbitmap:" + imageUri);
        // 显示网络上的图片
        Bitmap bitmap = null;
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

            Log.v(TAG, "image download finished." + imageUri);
        } catch (IOException e) {
            e.printStackTrace();
            Log.v(TAG, "getbitmap bmp fail---");
            return null;
        }
        return bitmap;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    public static Bitmap decodeUriAsBitmapFromNet(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 传入ImageView获得转换完成的字节流
     *
     * @param imageView     放置图片控件
     * @return byte 转换完成的字节流
     */
    public static String imageViewToByte(ImageView imageView){
        Bitmap obmp = Bitmap.createBitmap(imageView.getDrawingCache());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        obmp.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        return new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
    }

    /**
     * 传入字节流获得转换完成的bitmap
     *
     * @param strbyte       字节流
     * @return bitmap 转换完成的bitmap
     */
    public static Bitmap ByteToBitmap(String strbyte){
        byte[] bytes = Base64.decode(strbyte.getBytes(), 1);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * Bitmap轉字節流
     *
     * @param bitmap       字节流
     * @return string 转换完成的字節流
     */
    public static String BitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        return new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
    }
}
