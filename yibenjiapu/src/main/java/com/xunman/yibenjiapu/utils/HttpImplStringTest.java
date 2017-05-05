package com.xunman.yibenjiapu.utils;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 访问网络数据工具类
 * <p>使用中
 */
public class HttpImplStringTest extends Thread {
     public static final String WSURL = "http://172.16.1.132/";
//    public static final String WSURL = "ws://jp.xun-m.com/Genealogy/websocket";

    public static String Http_Url = "http://211.149.170.166/Genealogy/";//远程IP链接
    public static String Http_Url_Img_Video = "http://211.149.170.166/Genealogy_data/";//IP链接

//    public static String Http_Url = "http://172.16.1.132:8080/Genealogy/";//局域网IP链接
//    public static String Http_Url_Img_Video = "http://172.16.1.132/Genealogy_data/";//局域网IP链接
    private String private_URL = null;
    private Map<String, Object> map = null;
    private Handler handler;
    private String Methd = "POST";
    private long time1;
    private String str;


    /**
     * 姓氏图片根地址
     *
     * @return
     */
    public static String getSur_Img() {
        return Http_Url_Img_Video + "Surname/Image/";
    }

    /**
     * 姓氏视频根地址
     *
     * @return
     */
    public static String getSur_Video() {
        return Http_Url_Img_Video + "Surname/Video/";
    }

    /**
     * 本地服务器访问
     *
     * @param map
     * @param path
     * @param handler
     * @param Methd
     */
    public HttpImplStringTest(Map<String, Object> map, String path, String Methd, Handler handler) {
        this.private_URL = this.Http_Url + path;
        this.map = map;
        this.handler = handler;
        this.Methd = Methd;
        client = new OkHttpClient();
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//设置读取超时时间
        client.setWriteTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS);//设置写的超时时间
        client.setConnectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS);//设置连接超时时间
    }

    //网络请求

    /**
     * 外网服务器连接
     *
     * @param map
     * @param path
     * @param handler
     * @param Methd
     */
    public HttpImplStringTest(Map<String, Object> map, String path, Handler handler, String Methd) {
        time1 = System.currentTimeMillis();
        this.private_URL = this.Http_Url + path;
        this.map = map;
        this.handler = handler;
        this.Methd = Methd;
        client = new OkHttpClient();
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//设置读取超时时间
        client.setWriteTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS);//设置写的超时时间
        client.setConnectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS);//设置连接超时时间
    }
    public HttpImplStringTest(Map<String, Object> map, Handler handler, String Methd, String path) {
        time1 = System.currentTimeMillis();
        this.private_URL =  path;
        this.map = map;
        this.handler = handler;
        this.Methd = Methd;
        client = new OkHttpClient();
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//设置读取超时时间
        client.setWriteTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS);//设置写的超时时间
        client.setConnectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS);//设置连接超时时间
    }

    private OkHttpClient client ;

    public final static int CONNECT_TIMEOUT =60;
    public final static int READ_TIMEOUT=100;
    public final static int WRITE_TIMEOUT=60;
    @SuppressWarnings("rawtypes")
    @Override
    public void run() {
        try {
            Request request;
            if (Methd.equals("GET")) {
                request = new Request.Builder().url(private_URL + "?" + GET()).get().build();
            } else {
                //RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GET());
                request = new Request.Builder().url(private_URL).post(POST().build()).build();
            }
            Response response = client.newCall(request).execute();
            str = response.body().string();
            Message msg = new Message();
            msg.what = 0x123;
//            if (client.getConnectTimeout()>CONNECT_TIMEOUT){
//                msg.obj= "链接服务器超时";
//                handler.sendMessage(msg);
//            }
            msg.obj = str;
            handler.sendMessage(msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FormEncodingBuilder POST() {
        FormEncodingBuilder formBody = new FormEncodingBuilder();
        for (Map.Entry i : map.entrySet()) {
            try {
                formBody.add(i.getKey().toString(), URLEncoder.encode(i.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return formBody;
    }

    private String GET() {
        StringBuffer sburl = new StringBuffer();
        for (Map.Entry i : map.entrySet()) {
            try {
                sburl.append(i.getKey().toString());
                sburl.append("=");
                sburl.append(URLEncoder.encode(i.getValue().toString(), "UTF-8"));
                sburl.append("&");
            } catch (Exception e) {
                sburl.append("&");
                e.printStackTrace();
            }
        }
        return sburl.toString();
    }
}
