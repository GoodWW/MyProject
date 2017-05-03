package com.xunman.yibenjiapu.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/3/7 0007 9:09
 * 包名：com.xunman.yibenjiapu.utils
 * 文件名： ${name}
 * 描述：  Toast工具类 保证整个程序只有一个Toast
 */

public class ToastUtil  {
    public static void t(Context context,String content ){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
