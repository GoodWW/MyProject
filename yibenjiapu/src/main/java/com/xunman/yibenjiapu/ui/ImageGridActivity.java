package com.xunman.yibenjiapu.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.xunman.yibenjiapu.adapter.ImageGridAdapter;
import com.xunman.yibenjiapu.bean.ImageItem;
import com.xunman.yibenjiapu.utils.AlbumHelper;
import com.xunman.yibenjiapu.utils.Bimp;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 相册展示页
 * @author zhouyou
 */
@SuppressLint("HandlerLeak")
public class ImageGridActivity extends AppCompatActivity {
	
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	private ImageGridAdapter mAdapter;
	private List<ImageItem> mDataList;
	private AlbumHelper mHelper;
	private GridView mGridView;
	private Button mBt;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				ToastUtil.t(ImageGridActivity.this, "最多选择9张图片");
				break;
			default:
				break;
			}
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_grid);

		mHelper = AlbumHelper.getHelper();
		mHelper.init(getApplicationContext());
		mDataList = (List<ImageItem>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);

		initView();
		mBt = (Button) findViewById(R.id.bt);
		mBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ArrayList<String> list = new ArrayList<String>();
				Collection<String> c = mAdapter.mMap.values();
				Iterator<String> it = c.iterator();
				for (; it.hasNext();) {
					list.add(it.next());
				}

				if (Bimp.mActBool) {
					Intent intent = new Intent(ImageGridActivity.this, InformationReleaseActivity.class);
					startActivity(intent);
					Bimp.mActBool = false;
				}
				
				for (int i = 0; i < list.size(); i++) {
					if (Bimp.mDrr.size() < 9) {
						Bimp.mDrr.add(list.get(i));
					}
				}
				finish();
			}
		});
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridview);
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mAdapter = new ImageGridAdapter(ImageGridActivity.this, mDataList, mHandler);
		mGridView.setAdapter(mAdapter);
		
		mAdapter.setTextCallback(new ImageGridAdapter.TextCallback() {
			public void onListen(int count) {
				mBt.setText("完成" + "(" + count + ")");
			}
		});

		mGridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mAdapter.notifyDataSetChanged();
			}
		});
	}
}
