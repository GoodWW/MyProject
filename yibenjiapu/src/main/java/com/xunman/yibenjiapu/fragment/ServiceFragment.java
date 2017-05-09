package com.xunman.yibenjiapu.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.xunman.yibenjiapu.adapter.FragmentServiceAdapter;
import com.xunman.yibenjiapu.bean.MsgInfo;
import com.xunman.yibenjiapu.customview.DeletableEditText;
import com.xunman.yibenjiapu.ui.MainActivity;
import com.xunman.yibenjiapu.ui.R;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.StaticClass;
import com.xunman.yibenjiapu.utils.ToastUtil;

import java.util.List;

/**
 * 1、为用户初始创建聊天界面时，通过用户好友信息到固定文件中去寻找聊天消息
 * <p>
 * 2、该界面已创建，着需要广播通知该界面去刷新文件访问
 * <p>
 * Created by Administrator on 2017/1/9 0009.
 */

public class ServiceFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private Button left, right;
    private DeletableEditText et_masg;
    private FragmentServiceAdapter adapter;
    private List<MsgInfo> datas;
    //定义服务返回的数据
    private String serviceBack;
    private Intent intent;

    public static Fragment instance() {
        ServiceFragment serviceFragment = new ServiceFragment();
        return serviceFragment;
    }

    //3月23日添加 注册广播接受者
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
        //接收好友ID
        MM mm = new MM();
        IntentFilter filter = new IntentFilter();
        filter.addAction(StaticClass.LONG_CONNECTION_ACTION);
        getActivity().registerReceiver(mm, filter);
    }
    //到此结束

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        intiView(view);
        adapter = new FragmentServiceAdapter(getActivity());
        datas = adapter.datas;
        listView.setAdapter(adapter);
        clickItem(listView);
        return view;
    }

    private void clickItem(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position % 2 != 0) {
                    Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "02887778611"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
            }
        });
    }

    private void intiView(View view) {
        left = (Button) view.findViewById(R.id.btn_left);
        right = (Button) view.findViewById(R.id.btn_right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        et_masg = (DeletableEditText) view.findViewById(R.id.et_msg);
        et_masg.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.listview);
        et_masg.setFocusable(false);
        et_masg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String msg = et_masg.getText().toString().trim();
        //String msg_left = "请拨打客服电话 0288868868";
        String msg_left = "请拨打客服电话<font color='#3333FF'>028-87778611</font>";
        switch (v.getId()) {
            case R.id.btn_left:
                adapter.addDataToAdapter(new MsgInfo(msg, null));
                adapter.notifyDataSetChanged();
                Log.e("+++", "+++++");
                break;
            case R.id.btn_right:
                String str = et_masg.getText().toString();
                LogUtils.e("sdfsdf", str);
                if (str.equals("")) {
                    ToastUtil.t(getActivity(), "请先输入内容！");
                } else {
                    adapter.addDataToAdapter(new MsgInfo(null, msg));
                    adapter.notifyDataSetChanged();
                    et_masg.setText("");
                    try {
                        Thread.sleep(100);
                        adapter.addDataToAdapter(new MsgInfo(msg_left, null));
                        adapter.notifyDataSetChanged();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.et_msg:
                LogUtils.e("3333333333333333333333", "点击了聊天输入框");
                et_masg.setFocusable(true);
                et_masg.setFocusableInTouchMode(true);
                et_masg.requestFocus();
                MainActivity.llMainBottomNavigation.setVisibility(View.GONE);
                break;
        }
    }

    private String Read_Chat_File(String File_Path) {
        return null;
    }

    //定义广播接受者  用于接收服务发来的消息
    public class MM extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //1、判断该界面是否为首页
            //是：通知界面刷新消息
            //否：广播将处理消息简单处理、通知用户有新消息
            serviceBack = intent.getStringExtra("key");
            Toast.makeText(getActivity(), serviceBack, Toast.LENGTH_SHORT).show();
            adapter.addDataToAdapter(new MsgInfo(serviceBack, null));
            adapter.notifyDataSetChanged();
            Log.e("+++", "+++++");
        }
    }
}
