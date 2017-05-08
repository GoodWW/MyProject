package com.xunman.yibenjiapu.bean;

import java.util.List;

/**
 * 项目名： UpdateSoftDemo
 * 创建者： xxxxx
 * 创建时间：  2017/5/5 0005 18:08
 * 包名：com.szy.update
 * 文件名： ${name}
 * 描述：  资讯评论实体类
 */

public class InformationCommentBean {

    /**
     * result : 11
     * info : [{"id":1,"info_browse":0,"info_comment":0,"info_contents":"%E6%B5%8B%E8%AF%95","info_label":"ALL","info_like":0,"info_picture":"[\"-601406328.jpg\"]","info_time":"2017-05-05 15:41:33.0","info_title":"%E6%B5%8B%E8%AF%95","info_userhead":"Genealogy_data%5Cuser_info%5C790935943%5Chead%5C0.png","info_userid":1,"info_username":"18981886651"},{"id":2,"info_browse":0,"info_comment":0,"info_contents":"%E6%91%B8%E6%91%B8%E5%93%A6","info_label":"ALL","info_like":0,"info_picture":"[\"-415444477.jpg\",\"-954982182.jpg\",\"-1476315718.jpg\"]","info_time":"2017-05-05 16:24:07.0","info_title":"%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A","info_userhead":"Genealogy_data%5Cuser_info%5C790935943%5Chead%5C0.png","info_userid":1,"info_username":"18981886651"},{"id":3,"info_browse":0,"info_comment":0,"info_contents":"%E9%A2%9D%E9%A2%9D%E5%91%83%E5%91%83%E5%91%83","info_label":"ALL","info_like":0,"info_picture":"[\"292896394.jpg\",\"1826870690.jpg\",\"-2021245322.jpg\"]","info_time":"2017-05-05 17:26:27.0","info_title":"%E9%BB%98%E9%BB%98","info_userhead":"Genealogy_data%5Cuser_info%5C790935943%5Chead%5C0.png","info_userid":1,"info_username":"18981886651"},{"id":4,"info_browse":0,"info_comment":0,"info_contents":"%E6%88%91%E6%83%B3%E9%97%AE%E4%B8%80%E4%B8%8B","info_label":"ALL","info_like":0,"info_picture":"[\"-286627338.jpg\",\"467431316.jpg\",\"615225502.jpg\",\"-1251709047.jpg\"]","info_time":"2017-05-05 17:30:49.0","info_title":"%E5%95%8A%E5%95%8A%E5%95%8A","info_userhead":"Genealogy_data%5Cuser_info%5C790935943%5Chead%5C0.png","info_userid":1,"info_username":"18981886651"},{"id":5,"info_browse":0,"info_comment":0,"info_contents":"%E9%A2%9D%E9%A2%9D%E9%A2%9D%E9%A2%9D","info_label":"ALL","info_like":0,"info_picture":"[\"1165670295.png\"]","info_time":"2017-05-05 17:32:11.0","info_title":"%E5%91%83%E5%91%83%E5%91%83","info_userhead":"Genealogy_data%5Cuser_info%5C790935943%5Chead%5C0.png","info_userid":1,"info_username":"18981886651"}]
     */

    private int result;
    private List<InfoBean> info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 1
         * info_browse : 0
         * info_comment : 0
         * info_contents : %E6%B5%8B%E8%AF%95
         * info_label : ALL
         * info_like : 0
         * info_picture : ["-601406328.jpg"]
         * info_time : 2017-05-05 15:41:33.0
         * info_title : %E6%B5%8B%E8%AF%95
         * info_userhead : Genealogy_data%5Cuser_info%5C790935943%5Chead%5C0.png
         * info_userid : 1
         * info_username : 18981886651
         */

        private int id;
        private int info_browse;
        private int info_comment;
        private String info_contents;
        private String info_label;
        private int info_like;
        private String info_picture;
        private String info_time;
        private String info_title;
        private String info_userhead;
        private int info_userid;
        private String info_username;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfo_browse() {
            return info_browse;
        }

        public void setInfo_browse(int info_browse) {
            this.info_browse = info_browse;
        }

        public int getInfo_comment() {
            return info_comment;
        }

        public void setInfo_comment(int info_comment) {
            this.info_comment = info_comment;
        }

        public String getInfo_contents() {
            return info_contents;
        }

        public void setInfo_contents(String info_contents) {
            this.info_contents = info_contents;
        }

        public String getInfo_label() {
            return info_label;
        }

        public void setInfo_label(String info_label) {
            this.info_label = info_label;
        }

        public int getInfo_like() {
            return info_like;
        }

        public void setInfo_like(int info_like) {
            this.info_like = info_like;
        }

        public String getInfo_picture() {
            return info_picture;
        }

        public void setInfo_picture(String info_picture) {
            this.info_picture = info_picture;
        }

        public String getInfo_time() {
            return info_time;
        }

        public void setInfo_time(String info_time) {
            this.info_time = info_time;
        }

        public String getInfo_title() {
            return info_title;
        }

        public void setInfo_title(String info_title) {
            this.info_title = info_title;
        }

        public String getInfo_userhead() {
            return info_userhead;
        }

        public void setInfo_userhead(String info_userhead) {
            this.info_userhead = info_userhead;
        }

        public int getInfo_userid() {
            return info_userid;
        }

        public void setInfo_userid(int info_userid) {
            this.info_userid = info_userid;
        }

        public String getInfo_username() {
            return info_username;
        }

        public void setInfo_username(String info_username) {
            this.info_username = info_username;
        }
    }
}
