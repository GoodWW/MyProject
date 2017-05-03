package com.xunman.yibenjiapu.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xunman.yibenjiapu.adapter.ExpressionAdapter;
import com.xunman.yibenjiapu.mode.ExpressionMode;
import com.xunman.yibenjiapu.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwk on 2017.4.27.
 * 表情框
 */
public class ExpressionGridView extends RelativeLayout implements AdapterView.OnItemClickListener {//, View.OnTouchListener
    private final int TRANSVERSE_NUMBER = 7;
    private final int VERTICAL_NUMBER = 3;
    private ExpressionViewPager expressionViewPager;//自定义了屏蔽滑动
    private List<ExpressionMode> expressionModes;
    private int page;
    private final int PAGE_MAX_NUMBER = 21;
    private List<GridView> expressionGridViewList;
    private EditText editText;
    private int choose = -1;
    private List<List<ExpressionMode>> lists = new ArrayList<>();
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    private List<ExpressionMode> list;
    private boolean isMove = false;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public ExpressionGridView(Context context) {
        super(context);


    }

    private void initView() {

    }

    public ExpressionGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setExpressionModes(List<ExpressionMode> expressionModes) {
        this.expressionModes = expressionModes;
        page = expressionModes.size() / (PAGE_MAX_NUMBER) + 1;
        expressionGridViewList = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            final GridView gridView = (GridView) View.inflate(getContext(), R.layout.gridview, null);
            List<ExpressionMode> list = this.expressionModes.subList(PAGE_MAX_NUMBER * i, PAGE_MAX_NUMBER * (i + 1) > expressionModes.size() ? expressionModes.size() - 1 : PAGE_MAX_NUMBER * (i + 1));
            lists.add(list);
            if (i == 0) {
                this.list = list;
            }
            final ExpressionAdapter expressionAdatper = new ExpressionAdapter(list, getContext());
            gridView.setAdapter(expressionAdatper);
            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    expressionViewPager.setScanScroll(true);//屏蔽ViewPage滑动事件
                    isMove = true;
                    setSelect(choose);
                    return false;
                }
            });
            //gridView.setOnTouchListener(this);
            gridView.setOnItemClickListener(this);
            expressionGridViewList.add(gridView);
        }

        expressionViewPager = (ExpressionViewPager) View.inflate(getContext(), R.layout.viewpage, null);
        addView(expressionViewPager);
        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return expressionGridViewList.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView(expressionGridViewList.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager) container).addView(expressionGridViewList.get(position));
                return expressionGridViewList.get(position);
            }
        };
        expressionViewPager.setAdapter(mPagerAdapter);
        expressionViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                relativeLayout.setVisibility(GONE);
//                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onPageSelected(int position) {
                list = lists.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ExpressionAdapter expressionAdapter = (ExpressionAdapter) parent.getAdapter();
        ExpressionMode expressionMode = (ExpressionMode) expressionAdapter.getItem(position);
        ExpressionUtil.installExpression(getContext(), editText, expressionMode);
    }


//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        final int action = event.getAction();
//        final float x = event.getX();
//        final float y = event.getY();
//        Log.e("12","x:"+x+"y:"+y);
//        int heighnum = (int) (y / (v.getHeight() / VERTICAL_NUMBER));
//        int widthnum = (int) (x / (v.getWidth() / TRANSVERSE_NUMBER));
//        //超出View 范围
//        if (heighnum <= 0) heighnum = 0;
//        if (heighnum >= VERTICAL_NUMBER) heighnum = VERTICAL_NUMBER - 1;
//        if (widthnum <= 0) widthnum = 0;
//        if (widthnum >= TRANSVERSE_NUMBER) widthnum = TRANSVERSE_NUMBER - 1;
//
//        //没有填充满
//        int listSize = list.size();
//        if (heighnum >= listSize / TRANSVERSE_NUMBER && widthnum >= listSize % TRANSVERSE_NUMBER) {
//            widthnum = listSize % TRANSVERSE_NUMBER;
//            heighnum = listSize / TRANSVERSE_NUMBER;
//            heighnum--;
//        }
//        final int c = (heighnum) * TRANSVERSE_NUMBER + widthnum;
//        choose = c;
//        if (choose >= listSize)
//            choose = listSize - 1;//需要减1 不要越界
//        switch (action) {
//            case MotionEvent.ACTION_UP:
//                choose = -1;//
//                expressionViewPager.setScanScroll(false);
//                isMove = false;
//
//                relativeLayout.setVisibility(GONE);
//                imageView.setVisibility(View.GONE);
//                break;
//
//          default:
//                if (isMove)
//                    setSelect(choose);
//                break;
//        }
//        return false;
//    }

    /**
     * 显示表情
     *
     * @param select 滑动选中效果
     */
    private void setSelect(int select) {
        imageView.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(VISIBLE);

        int w = choose % TRANSVERSE_NUMBER;
        int h = choose / TRANSVERSE_NUMBER;

        //设置弹出框的大小个位置
        View v = expressionGridViewList.get(0);
        LayoutParams layoutParams = new LayoutParams((v.getWidth() / TRANSVERSE_NUMBER), (v.getWidth() / TRANSVERSE_NUMBER));
        layoutParams.setMargins(w * (v.getWidth() / TRANSVERSE_NUMBER), (h) * (v.getHeight() / VERTICAL_NUMBER) - 30 + v.getHeight() / VERTICAL_NUMBER, 0, 0);
        relativeLayout.setLayoutParams(layoutParams);

        imageView.setBackgroundResource(list.get(choose > list.size() ? list.size() : select).getResources());
    }
}
