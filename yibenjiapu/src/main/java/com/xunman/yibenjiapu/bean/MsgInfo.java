package com.xunman.yibenjiapu.bean;

/**
 * 项目名： ServiceDemo
 * 创建者： xxxxx
 * 创建时间：  2017/2/17 0017 10:14
 * 包名：com.xunman.servicedemo
 * 文件名： ${name}
 * 描述：  客服界面adapter的实体类
 */

public class MsgInfo {
    private String left_text;
    private String right_text;

    public MsgInfo(String left_text, String right_text) {
        this.left_text = left_text;
        this.right_text = right_text;
    }

    public String getLeft_text() {
        return left_text;
    }

    public void setLeft_text(String left_text) {
        this.left_text = left_text;
    }

    public String getRight_text() {
        return right_text;
    }

    public void setRight_text(String right_text) {
        this.right_text = right_text;
    }
}
