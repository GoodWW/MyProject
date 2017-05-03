package com.xunman.yibenjiapu.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 项目名： VideoPlayer
 * 创建者： 张人文
 * 创建时间：  2017/3/29 0029 15:32
 * 包名：com.xunman.videoplayer
 * 文件名： ${name}
 * 描述：  自定义 VideoView  实现横竖屏切换无黑边
 */

public class CustomVideoView extends VideoView {
    int defaultWidth = 1920;//设置默认的宽和高
    int defaultHeight = 1280;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写测量方法   重新计算   强行指定VideoView的宽高   不受activity 里面那句话  以及  视频分辨率 的控制
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(defaultWidth, widthMeasureSpec);//如果能拿到测量结果就显示 不能就用默认的值
        int height = getDefaultSize(defaultHeight, heightMeasureSpec);//如果能拿到测量结果就显示 不能就用默认的值
        setMeasuredDimension(width, height);//设置屏幕的宽和高
    }
}
