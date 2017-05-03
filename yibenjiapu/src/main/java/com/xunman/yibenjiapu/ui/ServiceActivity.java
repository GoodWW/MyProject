package com.xunman.yibenjiapu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xunman.yibenjiapu.adapter.FragmentServiceAdapter;
import com.xunman.yibenjiapu.bean.MsgInfo;

import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/3/17 0017 11:02
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  整理过后的客服界面  未使用
 */

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Button left, right;
    private EditText et_masg;
    private FragmentServiceAdapter adapter;
    private List<MsgInfo> datas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_service);
        intiView();
        adapter = new FragmentServiceAdapter(this);
        datas = adapter.datas;
        listView.setAdapter(adapter);
    }
    private void intiView() {
        left = (Button)findViewById(R.id.btn_left);
        right = (Button) findViewById(R.id.btn_right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        et_masg = (EditText) findViewById(R.id.et_msg);
        listView = (ListView) findViewById(R.id.listview);
    }

    @Override
    public void onClick(View v) {
        String msg = et_masg.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_left:
                adapter.addDataToAdapter(new MsgInfo(msg, null));
                adapter.notifyDataSetChanged();
                Log.e("+++","+++++");
                break;
            case R.id.btn_right:
                adapter.addDataToAdapter(new MsgInfo(null, msg));
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
