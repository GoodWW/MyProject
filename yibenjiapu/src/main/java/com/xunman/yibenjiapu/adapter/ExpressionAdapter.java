package com.xunman.yibenjiapu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xunman.yibenjiapu.mode.ExpressionMode;
import com.xunman.yibenjiapu.ui.R;

import java.util.List;

/**
 * Created by lwk on 2017.4.27.
 */
public class ExpressionAdapter extends BaseAdapter {
    private List<ExpressionMode> expressionModes;
    private Context context;

    public ExpressionAdapter(List<ExpressionMode> expressionModes, Context context) {
        this.expressionModes = expressionModes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return expressionModes.size();
    }

    @Override
    public Object getItem(int position) {
        return expressionModes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.singleexpression, null);
            viewHodler = new ViewHodler();
            viewHodler.expression = (ImageView) convertView.findViewById(R.id.expression_image);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        viewHodler.expression.setBackgroundResource(expressionModes.get(position).getResources());
        return convertView;
    }

    class ViewHodler {
        ImageView expression;

    }
}
