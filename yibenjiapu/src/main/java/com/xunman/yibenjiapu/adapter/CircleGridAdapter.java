package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.MeasureUtils;

import java.util.List;

public class CircleGridAdapter extends BaseAdapter {
	
	private List<String> mFiles;
	private LayoutInflater mLayoutInflater;
	private Context context;
	
	public CircleGridAdapter(List<String> files, Context context) {
		this.mFiles = files;
		this.context = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mFiles == null ? 0 : mFiles.size();
	}

	@Override
	public String getItem(int position) {
		return mFiles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_gridview_circle,parent, false);
			holder.imageView = (ImageView) convertView.findViewById(R.id.album_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 根据屏幕宽度动态设置图片宽高
		int width = MeasureUtils.getWidth(context);
		int imageWidth = (width / 3 - 40);
		LayoutParams lp = holder.imageView.getLayoutParams();
		lp.width = imageWidth;
		lp.height = imageWidth;
		holder.imageView.setLayoutParams(lp);
		String url = getItem(position);
		LogUtils.e("imageViewurl",url);
//		Glide.with(context)
//				.load(url)
//				.error(R.drawable.ic_launcher)
//				.into(holder.imageView);
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
		ImageLoader.getInstance().displayImage("http://172.16.1.132/Genealogy_data/Information/"+url, holder.imageView);
		//ImageLoader.getInstance().displayImage(url, holder.imageView);
		return convertView;
	}

	private static class ViewHolder {
		ImageView imageView;
	}
}
