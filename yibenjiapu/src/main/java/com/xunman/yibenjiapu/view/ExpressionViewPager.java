package com.xunman.yibenjiapu.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ExpressionViewPager extends ViewPager {

    private boolean isCanScroll = false;

    public ExpressionViewPager(Context context) {
        super(context);
    }

    public ExpressionViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewPager) {
            return isCanScroll;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

}