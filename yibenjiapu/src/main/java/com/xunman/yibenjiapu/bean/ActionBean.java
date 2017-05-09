package com.xunman.yibenjiapu.bean;

import java.util.List;

/**
 * 项目名： UpdateSoftDemo
 * 创建者： xxxxx
 * 创建时间：  2017/5/5 0005 18:08
 * 包名：com.szy.update
 * 文件名： ${name}
 * 描述：  活动页面实体类
 */

public class ActionBean {

    private int result;
    private int id;

    private String title;//活动标题
    //活动图片地址
    private String image;
    //活动按钮图片地址
    private String imagebtn;
    //活动按钮文字
    private String tvbtn;
    //活动按钮文字颜色
    private String tvbtncolor;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagebtn() {
        return imagebtn;
    }

    public void setImagebtn(String imagebtn) {
        this.imagebtn = imagebtn;
    }

    public String getTvbtn() {
        return tvbtn;
    }

    public void setTvbtn(String tvbtn) {
        this.tvbtn = tvbtn;
    }

    public String getTvbtncolor() {
        return tvbtncolor;
    }

    public void setTvbtncolor(String tvbtncolor) {
        this.tvbtncolor = tvbtncolor;
    }
}
