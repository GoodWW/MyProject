package com.xunman.yibenjiapu.bean;

import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/4/6 0006 16:29
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  直接接收广告信息的新类
 */

public class AdvertisementInfoNew {

    /**
     * result : 11
     * contents : [{"id":1,"imageurl":"http://h.hiphotos.baidu.com/image/h%3D200/sign=7237860f952397ddc9799f046983b216/dc54564e9258d109a4d1165ad558ccbf6c814d23.jpg","time":"2017-03-26"}]
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
         * id : 1
         * imageurl : http://h.hiphotos.baidu.com/image/h%3D200/sign=7237860f952397ddc9799f046983b216/dc54564e9258d109a4d1165ad558ccbf6c814d23.jpg
         * time : 2017-03-26
         */

        private int id;
        private String imageurl;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
