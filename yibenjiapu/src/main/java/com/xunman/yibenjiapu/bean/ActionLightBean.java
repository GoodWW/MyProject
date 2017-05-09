package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名： UpdateSoftDemo
 * 创建者： xxxxx
 * 创建时间：  2017/5/5 0005 18:08
 * 包名：com.szy.update
 * 文件名： ${name}
 * 描述：  活动列表页面实体类
 */

public class ActionLightBean {

    /**
     * result : 11
     */

    private int result;
    private List<ActionListBean> info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<ActionListBean> getInfo() {
        return info;
    }

    public void setInfo(List<ActionListBean> info) {
        this.info = info;
    }

    public static class ActionListBean{
        private int id;
        private boolean type;//活动标题
        private String title;
        //活动标签
        private List<String> tag;
        //活动简介
        private String intro;
        //活动报名人数
        private int peopleNum;
        //活动时间
        private String time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getPeopleNum() {
            return peopleNum;
        }

        public void setPeopleNum(int peopleNum) {
            this.peopleNum = peopleNum;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }
    }
}
