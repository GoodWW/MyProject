package com.xunman.yibenjiapu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunman.yibenjiapu.adapter.InformationCommentAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.bean.InformationBean;
import com.xunman.yibenjiapu.bean.InformationDetailsBean;
import com.xunman.yibenjiapu.dao.Login;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;
import com.xunman.yibenjiapu.view.ShowMoreTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/2 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  论坛详情页面
 */
public class InformationDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private TextView tvInformationDetailsBack;
    //评论列表
    private ListView lvInformationComment;
    private InformationCommentAdapter informationCommentAdapter;
    //发送评论内容
    private EditText etComment;
    //发送评论按钮
    private Button btnCommentSend;
    //网络访问进度条
    private ProgressDialog dialog;
    //ListView头布局控件
    private ImageView iv_details_avatar; //头像
    private ShowMoreTextView smtvContent; //内容
    private RelativeLayout rlCommentHead; //布局
    private TextView tv_head_details_name; //名字
    private TextView tv_head_details_title; //标题
    private TextView tv_head_info_comment_num; //评论数
    private TextView tv_head_info_praise_num; //点赞数
    //信息集合
    private List<InformationDetailsBean> informationDetailsBean;
    private List<InformationDetailsBean.InfoDetailsBean> infoDetailsBeen;
    private InformationDetailsBean infoDetailsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        bundle = intent.getExtras();
        setContentView(R.layout.activity_information_details);
        //获取帖子信息
        getInformation();
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        //返回按钮
        tvInformationDetailsBack = (TextView) findViewById(R.id.tv_information_details_back);
        tvInformationDetailsBack.setOnClickListener(this);
        lvInformationComment = (ListView) findViewById(R.id.lv_information_comment);
        //楼主帖子列表头布局
        initHead();
        infoDetailsBeen = new ArrayList<>();
        informationCommentAdapter = new InformationCommentAdapter(this, intent, infoDetailsBeen);
        lvInformationComment.setAdapter(informationCommentAdapter);
        //评论
        etComment = (EditText) findViewById(R.id.et_comment);
        btnCommentSend = (Button) findViewById(R.id.btn_comment_send);
    }

    /**
     * 初始化listview头布局帖子内容相关控件
     */
    private void initHead(){
        View listViewHeader = getLayoutInflater().inflate(R.layout.item_information_comment_head,lvInformationComment, false);
        lvInformationComment.addHeaderView(listViewHeader);
        smtvContent = (ShowMoreTextView) listViewHeader.findViewById(R.id.smtv_content);
        smtvContent.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
        rlCommentHead = (RelativeLayout) listViewHeader.findViewById(R.id.rl_comment_head);
        rlCommentHead.setOnClickListener(null);
        iv_details_avatar = (ImageView) listViewHeader.findViewById(R.id.iv_details_avatar);
        tv_head_details_name = (TextView) listViewHeader.findViewById(R.id.tv_head_details_name);
        tv_head_details_title = (TextView) listViewHeader.findViewById(R.id.tv_head_details_title);
        tv_head_info_comment_num = (TextView) listViewHeader.findViewById(R.id.tv_head_info_comment_num);
        tv_head_info_praise_num = (TextView) listViewHeader.findViewById(R.id.tv_head_info_praise_num);
        informationDetailsBean = new ArrayList<>();
        //往控件中放入数据

    }

    /**
     * 获取当前帖子和评论列表信息
     */
    private void getInformation() {
        Handler InfoDetailsH = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    dialog = ProgressDialog.show(InformationDetailsActivity.this, null, "正在获取帖子，请稍候...", true, false);
                    String info = msg.obj.toString();
                    LogUtils.e("返回信息___资讯", info);
                    JSONObject JSONobj = JSON.parseObject(info);
                    int result = JSONobj.getInteger("result");
                    if(result == 11){
                        infoDetailsBeen.addAll(infoDetailsBean.getInfo());
                        if (dialog.isShowing());dialog.dismiss();
                    }else if(result == 12){
                        ToastUtil.t(InformationDetailsActivity.this,"获取帖子信息失败");
                        if (dialog.isShowing());dialog.dismiss();
                    }
                }
            }
        };
        Map<String, Object> mapInfoDetails = new HashMap<>();
        mapInfoDetails.put("infoId",bundle.getInt("infoId"));
        new HttpImpl(mapInfoDetails, "", "POST",InfoDetailsH).start();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_information_details_back:
                finish();
                break;
            case R.id.btn_comment_send:
                String strComment = etComment.getText().toString();
                Handler CommentH = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 0x123) {
                            String info = msg.obj.toString();
                            LogUtils.e("返回信息___评论", info);
                            JSONObject JSONobj = JSON.parseObject(info);
                            int result = JSONobj.getInteger("result");
                            if(result == 11){

                            }else if(result == 12){

                            }
                        }
                    }
                };
                Map<String, Object> mapComment = new HashMap<>();
                //给服务器发送用户信息
                mapComment.put("userid", Integer.valueOf(Login.getLoginInfo("id").toString()));
                mapComment.put("username", Login.getLoginInfo("u_nickname").toString());
                mapComment.put("userhead", Login.getLoginInfo("u_datapath").toString() + Login.getLoginInfo("u_head_url").toString());
                //该片帖子的id
                mapComment.put("infoId", bundle.getInt("infoId"));
                //发送输入评论内容
                mapComment.put("strComment",strComment);
                new HttpImpl(mapComment, "", "POST",CommentH).start();
                break;
        }
    }
}
