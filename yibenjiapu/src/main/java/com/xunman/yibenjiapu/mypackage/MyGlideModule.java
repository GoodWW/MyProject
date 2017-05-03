package com.xunman.yibenjiapu.mypackage;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/4/1 0001 11:06
 * 包名：com.xunman.yibenjiapu.mypackage
 * 文件名： ${name}
 * 描述：  TODO
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context,10240));
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //这个文件可能不存在于你的设备中。然而你可以用任何文件路径，去指定一个图片路径。
//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"img");
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, "img", 10240));
        builder.setDiskCache(
                new ExternalCacheDiskCacheFactory(context, "img", 10240));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}
