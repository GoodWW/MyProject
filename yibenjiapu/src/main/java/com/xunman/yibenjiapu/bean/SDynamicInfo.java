package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名： WebSocketDemo
 * 创建者： xxxxx
 * 创建时间：  2017/3/22 0022 11:38
 * 包名：com.xunman.websocketdemo
 * 文件名： ${name}
 * 描述：  姓氏动态info
 */

public class SDynamicInfo  implements Serializable{

    /**
     * result : 11
     * contents : [{"id":3,"publish_time":"2017-03-21 11:50:01.0","sur_id":1,"trends_contents":"中华民族李氏大家族","trends_name":"李氏第一家族"}]
     */

    private int result;
    private List<ContentsBean> contents;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public static class ContentsBean {
        /**
         * id : 3
         * publish_time : 2017-03-21 11:50:01.0
         * sur_id : 1
         * trends_contents : 中华民族李氏大家族
         * trends_name : 李氏第一家族
         */

        private int id;
        private String publish_time;
        private int sur_id;
        private String trends_contents;
        private String trends_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public int getSur_id() {
            return sur_id;
        }

        public void setSur_id(int sur_id) {
            this.sur_id = sur_id;
        }

        public String getTrends_contents() {
            return trends_contents;
        }

        public void setTrends_contents(String trends_contents) {
            this.trends_contents = trends_contents;
        }

        public String getTrends_name() {
            return trends_name;
        }

        public void setTrends_name(String trends_name) {
            this.trends_name = trends_name;
        }
    }
}
