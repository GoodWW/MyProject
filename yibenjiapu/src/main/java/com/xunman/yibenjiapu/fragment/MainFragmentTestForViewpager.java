package com.xunman.yibenjiapu.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.xunman.yibenjiapu.adapter.MainFragmentTestAdapter3;
import com.xunman.yibenjiapu.adapter.MainFragmentTestAdapterViewpager;
import com.xunman.yibenjiapu.bean.AdvertisementInfo;
import com.xunman.yibenjiapu.bean.AdvertisementInfoNew;
import com.xunman.yibenjiapu.bean.SurnameList1;
import com.xunman.yibenjiapu.bean.Surname_Info;
import com.xunman.yibenjiapu.customview.RefreshListView;
import com.xunman.yibenjiapu.dao.FirstDao;
import com.xunman.yibenjiapu.ui.ItemMainActivity;
import com.xunman.yibenjiapu.ui.LoginActivity;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ShareUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lwk on 2017/2/22 0016.
 * 描述： 在fragement中动态加载viewpager
 */

public class MainFragmentTestForViewpager extends Fragment implements RefreshListView.ILoadListtener {

    private int k = 0;
    //    private String PathUrl = HttpImplStringTest.Http_Url;
    private Intent intent;
    private SurnameList1 surnameList1;
    //    private SurnameList1 surname;
//    private AdvertisementInfo advertisementInfo;
    private Bundle extras;
    private List<SurnameList1.ContentsBean> lists;
    private List<SurnameList1> list;
    private List<AdvertisementInfo> adList;
    //    private List<AdvertisementInfo.ContentsBean> adLists;
    private List<AdvertisementInfoNew.ContentsBean> adLists;
    private MainFragmentTestAdapter3 adapter;
    private RefreshListView lv;
    private List<View> viewList;
    private AdvertisementInfoNew advertisementInfoNew;
    private int isLoad = 0;
    private View contentView = null;
    private HeadView headView;
    private int int_ad = 0;
    //    private SurnameList1.ContentsBean contentsBean ;
    private Surname_Info sum;
    //    // 定位item按钮
//    private TextView tvMainItemLocation;
    private Handler handler_family_and_name;
    private Handler handler_ad;


    public static Fragment instance() {

        MainFragmentTestForViewpager mainFragment = new MainFragmentTestForViewpager();
        return mainFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtils.e("返回", "onCreate");

        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();

        extras = intent.getExtras();
        list = new ArrayList<>();
        lists = new ArrayList<>();

        adList = new ArrayList<>();
        adLists = new ArrayList<>();
        if (int_ad == 0) {
            getAdDate();
            int_ad = 1;
        }
        getNameDate();
    }

    String strSB;
    String strSD;
//    SDynamicInfo sDynamicInfo;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.e("返回", "onCreateView");
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_main_for_viewpager, container, false);
        } else {
            return contentView;
        }

        lv = (RefreshListView) contentView.findViewById(R.id.lv);
        lv.setInterface(this);
        showListView(lv);

        LogUtils.e("返回adapter", "adapter" + adapter);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new RefreshListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (ShareUtils.getBoolean(getActivity(), "login", false)) {
                    toTtem(arg2);
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        //设置刷新
        lv.setonRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLow();
            }
        });
        return contentView;
    }

    private void toTtem(int arg2) {
        if (arg2 > 1) {
            int sId;
            String sUrl;
            String name;
            if (sum != null) {
                if (arg2 == 2) {
                    sId = sum.getId();
                    //得到姓氏
                    LogUtils.e("sId", sId + "空");
                    name = sum.getSurname();
                    extras = intent.getExtras();
                    //视频播放地址
                    String mp4_url = HttpImplStringTest.getSur_Video() + sId + ".mp4";
                    LogUtils.e("mp4_url", mp4_url + "空");
                    extras.putString("mp4_url", mp4_url);
                    //将姓氏图片地址传送过去
                    sUrl = HttpImplStringTest.getSur_Img() + sId + ".png";
                    LogUtils.e("sUrl", sUrl + "空");
                    extras.putString("sUrl", sUrl);
                    //将姓氏起源传过去
                    String sCradleintr = sum.getCradleintr();
                    LogUtils.e("sCradleintr", sCradleintr + "空");
                    extras.putString("sCradleintr", sCradleintr);
                    k = 1;
                } else {
                    arg2 -= k;
                    //得到姓氏id
                    sId = lists.get(arg2 - 2).getID();
                    LogUtils.e("sId", sId + "空");
                    //得到姓氏
                    name = lists.get(arg2 - 2).getSurname();
                    extras = intent.getExtras();
                    //将姓氏图片地址传送过去
                    sUrl = HttpImplStringTest.getSur_Img() + sId + ".png";
                    extras.putString("sUrl", sUrl);
                    //视频播放地址
                    String mp4_url = HttpImplStringTest.getSur_Video() + sId + ".mp4";
                    extras.putString("mp4_url", mp4_url);
                    //将姓氏起源传过去
                    String sCradleintr = lists.get(arg2 - 2).getCradleintr();
                    extras.putString("sCradleintr", sCradleintr);
                }
            } else {
                arg2 -= k;
                //得到姓氏id
                sId = lists.get(arg2 - 2).getID();
                extras.putInt("sId", sId);
                intent.putExtras(extras);
                //得到姓氏
                name = lists.get(arg2 - 2).getSurname();
                extras = intent.getExtras();
                //将姓氏图片地址传送过去
                sUrl = HttpImplStringTest.getSur_Img() + sId + ".png";
                extras.putString("sUrl", sUrl);
                //视频播放地址
                String mp4_url = HttpImplStringTest.getSur_Video() + sId + ".mp4";
                extras.putString("mp4_url", mp4_url);
                //将姓氏起源传过去
                String sCradleintr = lists.get(arg2 - 2).getCradleintr();
                extras.putString("sCradleintr", sCradleintr);
            }
            //从服务器获取家族分支数据
            Handler SBranchH = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        strSB = msg.obj.toString();
                        JSONObject jsonObject = JSON.parseObject(strSB);
                        int result = jsonObject.getInteger("result");
                        if (result == 11) {
                            LogUtils.e("返回strSB++", strSB);
                            extras.putString("strSB", strSB);
                            intent.putExtras(extras);
                        } else if (result == 12) {
                            Toast.makeText(getActivity(), "当前姓氏没有家族分支", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            };
            Map<String, Object> map1 = new HashMap<>();
            map1.put("sur", name);
            map1.put("start", 1);
            map1.put("number", 10);
            LogUtils.e("surmsg===========>>", "map========>" + map1.toString());
            new HttpImplStringTest(map1, "Select_Sur_Branch.xml", SBranchH, "GET").start();
            //从服务器获取姓氏动态数据
            Handler SDynamicH = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        strSD = msg.obj.toString();
                        LogUtils.e("返回strSD++", "返回strSD======" + strSD);
                        JSONObject jsonObject = JSON.parseObject(strSD);
                        int result1 = jsonObject.getInteger("result");
                        if (result1 == 11) {
                            extras.putString("strSD", strSD);
                            intent.putExtras(extras);
                            isLoad++;
                            if (strSB != null && strSD != null && isLoad == 1) {
                                intent.setClass(getActivity(), ItemMainActivity.class);
                                startActivity(intent);
                            } else {
                                isLoad = 0;
                            }
                        } else if (result1 == 12) {
                            Toast.makeText(getActivity(), "当前姓氏没有动态", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            };
            Map<String, Object> map = new HashMap<>();
            map.put("sur_id", sId);
            map.put("start", 1);
            map.put("number", 20);
            LogUtils.e("surmsg===========>>", "map========>" + map.toString());
            new HttpImplStringTest(map, "select_sur_trends.xml", SDynamicH, "GET").start();
            if (strSB == null) {
                new HttpImplStringTest(map1, "Select_Sur_Branch.xml", SBranchH, "GET").start();
                if (strSD == null) {
                    new HttpImplStringTest(map, "select_sur_trends.xml", SDynamicH, "GET").start();
                }
            }
            intent.putExtras(extras);
        }
    }

    private Handler handler_refresh;

    //刷新 list数据
    private void refreshLow() {
        handler_refresh = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String str = msg.obj.toString();
                    LogUtils.e("返回++", str);
                    surnameList1 = JSON.parseObject(str, SurnameList1.class);
                    lists.clear();
                    lists.addAll(surnameList1.getContents());
                    LogUtils.e("listindex", lists.size() + "个");
                    adapter.notifyDataSetChanged();
                    lv.onRefreshComplete();
                    ToastUtil.t(getActivity(), "刷新成功！");
                }
            }
        };
        Map<String, Object> map = new HashMap<>();
        map.put("start", 1);
        map.put("number", 15);
        map.put("sort", "sort");
        map.put("key", "number");
        LogUtils.e("surmsg===========>>", "map========>" + map.toString());
        new HttpImplStringTest(map, "SelectSurname.xml", handler_refresh, "POST").start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showListView(RefreshListView lv) {
        if (adapter == null) {
//            LogUtils.e("sun", "sun" + sum.toString());
            if (sum == null) {
                adapter = new MainFragmentTestAdapter3(getActivity(), lists, getChildFragmentManager(), null);
            } else {
                adapter = new MainFragmentTestAdapter3(getActivity(), lists, getChildFragmentManager(), sum);
            }
        } else {
            adapter.onDateChange(lists);
        }
    }

    private View viewPage;

    @Override
    public void onLoad() {
        setDate();
        showListView(lv);
    }

    private void setDate() {
        addMore(extras);
    }

    private int start1 = 16;

    private Handler handler_addMore;


    /**
     * 加载更多代码
     */
    private void addMore(final Bundle b) {

        handler_addMore = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    String str = msg.obj.toString();
                    if (str.equals("链接服务器超时")) {
                        ToastUtil.t(getActivity(), str);
                    } else {
                        LogUtils.e("strstr", str);
                        String s = JSONObject.parseObject(str).getString("contents");

                        if (!s.equals("[]")) {
                            LogUtils.e("strstr", s);
                            surnameList1 = JSON.parseObject(str, SurnameList1.class);
                            lists.addAll(surnameList1.getContents());
                            LogUtils.e("listindex", lists.size() + "个");
                            lv.loadConplete();
                        } else {
                            LogUtils.e("strstr", s);
                            ToastUtil.t(getActivity(), "已经加载完了，没有更多了！");
                            lv.loadConplete();
                        }
                    }
                }
            }
        };
        if (lists.size() == 15) {
            Map<String, Object> map = new HashMap<>();
            map.put("start", start1);
            map.put("number", 10);
            map.put("sort", "sort");
            map.put("key", "number");
            new HttpImpl(map, FirstDao.SELECTSURNAME, HttpImpl.POSTMethd, handler_addMore).start();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("start", start1 + 10);
            map.put("number", 10);
            map.put("sort", "subtract");
            map.put("key", "number");
            new HttpImpl(map,FirstDao.SELECTADVERTISEMENTLIST, HttpImpl.POSTMethd, handler_addMore).start();
            start1 += 10;
        }
    }

    private String str_name;

    public void getNameDate() {
        //获取网络数据,姓氏数据
        handler_family_and_name = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    str_name = msg.obj.toString();
                    LogUtils.e("首页初次获取姓氏数据===", str_name);
                    surnameList1 = JSON.parseObject(str_name, SurnameList1.class);
                    lists.addAll(surnameList1.getContents());
                    adapter.onDateChange(lists);
                }
            }
        };
        Map<String, Object> map = new HashMap<>();
        map.put("start", 1);
        map.put("number", 15);
        map.put("sort", "sort");
        map.put("key", "number");
        new HttpImpl(map, FirstDao.SELECTSURNAME, HttpImpl.POSTMethd, handler_family_and_name).start();
    }

    private String str_ad;

    public void getAdDate() {
        //获取网络数据,广告数据
        handler_ad = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    str_ad = msg.obj.toString();
                    LogUtils.e("首页获取广告数据==", str_ad);
                    advertisementInfoNew = JSON.parseObject(str_ad, AdvertisementInfoNew.class);
                    adLists.addAll(advertisementInfoNew.getContents());
                    headView = new HeadView(getActivity());
                    lv.addHeaderView(headView);
                }
            }
        };
        Map<String, Object> map2 = new HashMap<>();
        map2.put("start", 1);
        map2.put("number", 3);
        map2.put("time", "");
        new HttpImpl(map2, FirstDao.SELECTADVERTISEMENTLIST, HttpImpl.GETMethd,handler_ad).start();
    }

    /**
     * 自定义顶部Viewpager
     */
    private class HeadView extends RelativeLayout {
        ViewPager viewPager;
        MainFragmentTestAdapterViewpager adapter;
        LinearLayout linDots;
        private Context mContext;

        public HeadView(Context context) {
            super(context);
            this.mContext = context;
            inflate(context, R.layout.lv_viewpager, this);
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            linDots = (LinearLayout) findViewById(R.id.linDot);
            initData();
        }

        //加载ViewPager的数据
        private void initData() {
            viewList = new ArrayList<>();
//            for (int i = 0; i < advertisementInfo.getContents().size(); i++) {
            for (int i = 0; i < advertisementInfoNew.getContents().size(); i++) {
                viewPage = View.inflate(mContext, R.layout.viewpager_item, null);
                ImageView imageView = (ImageView) viewPage.findViewById(R.id.iv);
                Glide.with(mContext)
                        .load(adLists.get(i).getImageurl())//   .load(PathUrl + advertisementInfo.getContents().get(i).getImageurl())
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)//是将图片原尺寸缓存到本地。
//                        .placeholder(R.mipmap.logo_logo)
//                        .crossFade()
                        .into(imageView);
                linDots.addView(dotsItem(i));
                viewList.add(viewPage);
            }
            adapter = new MainFragmentTestAdapterViewpager(getActivity(), viewList);
            viewPager.setAdapter(adapter);
            //设置第一个小点为选中
            linDots.getChildAt(0).setSelected(true);

            viewPager.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    PointF downP = new PointF();
                    PointF curP = new PointF();
                    int act = event.getAction();
                    if (act == MotionEvent.ACTION_DOWN
                            || act == MotionEvent.ACTION_MOVE
                            || act == MotionEvent.ACTION_UP) {
                        ((ViewGroup) v)
                                .requestDisallowInterceptTouchEvent(true);
//                        System.out.println("downP.x==="+downP.x+",downP.y==="+downP.y+",downP.x=="+downP.x+",downP.y==="+downP.y);
//                        LogUtils.e("++++++","downP.x==="+downP.x+",downP.y==="+downP.y+",downP.x=="+downP.x+",downP.y==="+downP.y);
                        if (downP.x == curP.x && downP.y == curP.y) {
                            return false;
                        }
                    }
                    return false;
                }
            });
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int arg0) {
                    for (int i = 0; i < linDots.getChildCount(); i++) {
                        linDots.getChildAt(i).setSelected(false);
                    }
                    linDots.getChildAt(arg0).setSelected(true);
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }

        /**
         * 点
         *
         * @param position
         * @return 修改点的位置
         */
        private ImageView dotsItem(int position) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.view_dots, null);
            ImageView point = (ImageView) layout.findViewById(R.id.dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            params.setMargins(10, 10, 10, 10);
            point.setLayoutParams(params);
            point.setId(position);
            return point;
        }
    }
}
