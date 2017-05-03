package com.xunman.yibenjiapu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.ui.R;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/3/23 0023 13:56
 * 包名：com.xunman.yibenjiapu.fragment
 * 文件名： ${name}
 * 描述：  可以展开的好友和客服列表   未使用
 */

public class ServiceExpandableFragment extends Fragment {

    public static Fragment instance() {
        ServiceExpandableFragment serviceExpandableFragment = new ServiceExpandableFragment();
        return serviceExpandableFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_expandable, container, false);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logos = new int[]{
                    R.drawable.man,
                    R.drawable.header,
                    R.drawable.b
            };
            private String[] armTupes = new String[]{
                    "神族兵种", "虫族兵种", "人族兵种"
            };
            private String[][] arms = new String[][]{
                    {"狂战士", "龙战士", "甲兵", "大兵"},
                    {"小狗", "小色", "自爆飞机", "飞龙"},
                    {"机甲兵", "护士mm", "幽灵"}
            };
            //
            @Override
            public int getGroupCount() {
//                return 0;
                return armTupes.length;
            }

            //
            @Override
            public int getChildrenCount(int groupPosition) {
//                return 0;
                return arms[groupPosition].length;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(getActivity());
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }

            //获取指定组位置处的数据
            @Override
            public Object getGroup(int groupPosition) {
//                return null;
                return armTupes[groupPosition];
            }

            //获取自定组位置，指定子列表项处 的 子列表数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
//                return null;
                return arms[groupPosition][childPosition];
            }

            //
            @Override
            public long getGroupId(int groupPosition) {
//                return 0;
                return groupPosition;
            }

            //
            @Override
            public long getChildId(int groupPosition, int childPosition) {
//                return 0;
                return childPosition;
            }

            //
            @Override
            public boolean hasStableIds() {
//                return false;
                return true;
            }

            //该方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//                return null;
                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(0);
                ImageView logo = new ImageView(getActivity());
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            //该方法决定每个子选项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            //
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
//                return false;
                return true;
            }
        };
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.service_expandable_list);
        expandableListView.setAdapter(adapter);
        return view;
    }
}
