package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.Bimp;
import com.xunman.yibenjiapu.utils.FileUtils;

import java.io.IOException;

/**
 * Created by lwk on 2017/5/3.
 * 发布图片gridview的适配器
 */

public class GridAdapter extends BaseAdapter {
    private LayoutInflater inflater; // 视图容器
    private int selectedPosition = -1;// 选中的位置
    private boolean shape;
    private Context context;

    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public GridAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void update() {
        loading();
    }

    public int getCount() {
        return (Bimp.mBmps.size() + 1);
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == Bimp.mBmps.size()) {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused));
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        }else {
            holder.image.setImageBitmap(Bimp.mBmps.get(position));
        }
        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (Bimp.mMax == Bimp.mDrr.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        try {
                            if(Bimp.mDrr.size() <= 0)
                                return;
                            String path = Bimp.mDrr.get(Bimp.mMax);
                            Bitmap bm = Bimp.revitionImageSize(path);
                            Bimp.mBmps.add(bm);
                            String newStr = path.substring(path.lastIndexOf("/") + 1,path.lastIndexOf("."));
                            FileUtils.saveBitmap(bm, "" + newStr);
                            Bimp.mMax += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
