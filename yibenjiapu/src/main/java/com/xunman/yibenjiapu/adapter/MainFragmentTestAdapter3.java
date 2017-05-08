package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xunman.yibenjiapu.bean.SurnameList1;
import com.xunman.yibenjiapu.bean.Surname_Info;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;

import java.util.List;

/**
 * Created by lwk on 2017/2/22 0016.
 * 描述： 删除BaseAdapter中的ViewPager
 */

public class MainFragmentTestAdapter3 extends BaseAdapter {
    private int K = 0;
    private LayoutInflater inflater;
    private List<SurnameList1.ContentsBean> lists;
    private SurnameList1.ContentsBean contentsBean;
    private Context context;
    private FragmentManager fragmentManager;
    private Surname_Info sum;

    public MainFragmentTestAdapter3(Context activity, List<SurnameList1.ContentsBean> lists, FragmentManager fragmentManager, Surname_Info sum) {
        this.fragmentManager = fragmentManager;
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.lists = lists;
        this.sum = sum;
    }

    public void setList(List<SurnameList1.ContentsBean> lists) {
        this.lists.addAll(lists);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    public void onDateChange(List<SurnameList1.ContentsBean> lists) {
        this.lists = lists;
        LogUtils.e("返回lists", "lists" + lists.toString());
        this.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_fragment_main_test, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_fragment_main_first_name);
            viewHolder.intr = (TextView) view.findViewById(R.id.item_fragment_main_intr);
            viewHolder.head = (ImageView) view.findViewById(R.id.item_fragment_main_head);
            viewHolder.fenzhi = (TextView) view.findViewById(R.id.item_fragment_main_num);
            viewHolder.renshu = (TextView) view.findViewById(R.id.item_fragment_main_number);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (sum != null) {
            if (position == 0) {
                Glide.with(context)
                        .load(HttpImplStringTest.getSur_Img() + sum.getId() + ".png")
//                        .placeholder(R.mipmap.logo)
//                        .crossFade()
                        .into(viewHolder.head);
                viewHolder.name.setText(sum.getSurname());//surname
                viewHolder.intr.setText(sum.getCradleintr());//ancestorsintr
                viewHolder.renshu.setText(String.valueOf(sum.getOnline_number()));
                viewHolder.fenzhi.setText(String.valueOf(sum.getBranch()));
                contentsBean = null;
                K = 1;
            } else {
                position -= K;
                contentsBean = lists.get(position);
                Glide.with(context)
                        .load(HttpImplStringTest.getSur_Img() + contentsBean.getID() + ".png")
//                        .placeholder(R.mipmap.logo)
//                        .crossFade()
                        .into(viewHolder.head);
                viewHolder.name.setText(contentsBean.getSurname());
                viewHolder.intr.setText(contentsBean.getCradleintr());
                Log.e("=|=|=======", String.valueOf(contentsBean.getOline_number()));
                viewHolder.renshu.setText(String.valueOf(contentsBean.getOline_number()));
                viewHolder.fenzhi.setText(String.valueOf(contentsBean.getBranch()));
            }
        } else {
            position -= K;
            contentsBean = lists.get(position);
            Glide.with(context)
                    .load(HttpImplStringTest.getSur_Img() + contentsBean.getID() + ".png")
//                    .placeholder(R.mipmap.logo_logo)
//                    .crossFade()
                    .into(viewHolder.head);
            viewHolder.name.setText(contentsBean.getSurname());
            viewHolder.intr.setText(contentsBean.getCradleintr());
            Log.e("=|=|=======", String.valueOf(contentsBean.getOline_number()));
            viewHolder.renshu.setText(String.valueOf(contentsBean.getOline_number()));
            viewHolder.fenzhi.setText(String.valueOf(contentsBean.getBranch()));
        }
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView intr;
        ImageView head;
        TextView fenzhi;
        TextView renshu;
    }
}

