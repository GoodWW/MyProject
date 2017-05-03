package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.xunman.yibenjiapu.adapter.ImageBucketAdapter;
import com.xunman.yibenjiapu.bean.ImageBucket;
import com.xunman.yibenjiapu.utils.AlbumHelper;

import java.io.Serializable;
import java.util.List;

/**
 * 选择相册页
 * @author zhouyou
 */
public class TestPicActivity extends AppCompatActivity {
	
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	private ImageBucketAdapter mAdapter;
	private List<ImageBucket> mDataList;
	private GridView mGridView;
	private AlbumHelper mHelper;
	public static Bitmap mBimap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_bucket);

		mHelper = AlbumHelper.getHelper();
		mHelper.init(getApplicationContext());

		initData();
		initView();
	}

	/** 初始化数据 */
	private void initData() {
		mDataList = mHelper.getImagesBucketList(false);	
		mBimap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused);
	}

	/** 初始化view视图 */
	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridview);
		mAdapter = new ImageBucketAdapter(TestPicActivity.this, mDataList);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/**
				 * 通知适配器，绑定的数据发生了改变，应当刷新视图
				 */
				Intent intent = new Intent(TestPicActivity.this, ImageGridActivity.class);
				intent.putExtra(TestPicActivity.EXTRA_IMAGE_LIST, (Serializable) mDataList.get(position).imageList);
				startActivity(intent);
				finish();
			}
		});
	}
}
