package com.xunman.yibenjiapu.utils;

import android.content.Context;

/**
 * 项目名： VideoPlayer
 * 创建者： xxxxx
 * 创建时间：  2017/3/28 0028 17:18
 * 包名：com.xunman.videoplayer
 * 文件名： ${name}
 * 描述：  像素转换工具类
 */

public class PixelUtils {
    public static Context mContext;

    public static void intiCOntext(Context context) {
        mContext = context;
    }

    public static int dp2px(float value) {
        final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    public static int dp2px(float value,Context context) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }
}
