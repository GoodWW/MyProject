package com.xunman.yibenjiapu.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/5.
 */

public class HttpImpl extends Thread {
    public static String GETMethd="GET", POSTMethd="POST";
    private String METHD="POST";
    private String private_URL = null;
    private Handler handler;
    private Map<String, Object> DATA = new HashMap<>();
    private StringBuilder GETParams = new StringBuilder();
    private OkHttpClient mOkHttpClient;

    public HttpImpl() {
        setHttpStyle();
    }

    public HttpImpl(Map<String, Object> DATA, String URL, String METHD, Handler handler) {
        setHttpStyle();
        this.DATA = DATA;
        private_URL = URL;
        this.METHD = METHD;
        this.handler = handler;
    }

    private void setHttpStyle() {
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
    }


    @Override
    public void run() {
        Request request;
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        try {
            for (Map.Entry i : DATA.entrySet()) {
                Object object = DATA.get(i.getKey().toString());
                if ((object instanceof File)) {
                    File file = (File) object;
                    builder.addFormDataPart(i.getKey().toString(), file.getName(), RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"), file));
                } else if ((object instanceof List)) {
                    List<File> listFile = (List<File>) object;
                    for (File file : listFile) {
                        builder.addFormDataPart(i.getKey().toString(), file.getName(), RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"), file));
                    }
                } else {
                    if (METHD.equals(POSTMethd)) {
                        builder.addFormDataPart(i.getKey().toString(), i.getValue().toString());
                    } else {
                        GETParams.append(String.format("%s=%s", i.getKey().toString(), URLEncoder.encode(i.getValue().toString(), "utf-8")));
                        GETParams.append("&");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (METHD.equals(POSTMethd)) {
            request = new Request.Builder()
                    .url(private_URL).header("Content-Type","text/html; charset=utf-8")
                    .post(builder.build())
                    .build();
        } else {
            String requestUrl = String.format("%s?%s", private_URL, GETParams.toString());
            request = new Request.Builder().url(requestUrl).build();
        }
        Response response;
        try {
            response = mOkHttpClient.newCall(request).execute();
            String jsonString = response.body().string();
            Message msg = new Message();
            msg.what = 0x123;
            msg.obj = jsonString;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
