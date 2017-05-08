package com.xunman.yibenjiapu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunman.yibenjiapu.adapter.CommentReplyAdapter;
import com.xunman.yibenjiapu.adapter.InformationCommentAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.view.ShowMoreTextView;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/5/3 =
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  评论回复页面
 */
public class CommentReplyActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private TextView tvConmentReplyBack;
    //评论回复列表
    private ListView lvCommentReply;
    private CommentReplyAdapter commentReplyAdapter;
    //评论内容
    private ShowMoreTextView smtvCommentContent;
    private RelativeLayout rlReplyHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_comment_reply);
        intiView();
        BaseApplication.list.add(this);
    }

    private void intiView() {
        tvConmentReplyBack = (TextView) findViewById(R.id.tv_conment_reply_back);
        lvCommentReply = (ListView) findViewById(R.id.lv_comment_reply);
        //评论为列表头布局
        View listViewHeader = getLayoutInflater().inflate(R.layout.item_comment_reply_head,lvCommentReply, false);
        smtvCommentContent = (ShowMoreTextView) listViewHeader.findViewById(R.id.smtv_comment_content);
        smtvCommentContent.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
        rlReplyHead = (RelativeLayout) listViewHeader.findViewById(R.id.rl_reply_head);
        rlReplyHead.setOnClickListener(null);

        lvCommentReply.addHeaderView(listViewHeader);

        commentReplyAdapter = new CommentReplyAdapter(this);
        lvCommentReply.setAdapter(commentReplyAdapter);

        //评论回复按钮点击事件，跳转到评论详情回复界面
        lvCommentReply.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        tvConmentReplyBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_conment_reply_back:
                finish();
                break;
        }
    }
}
