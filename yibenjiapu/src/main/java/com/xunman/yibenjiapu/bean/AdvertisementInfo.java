package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/2/28 0028 16:47
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  TODO
 */

public class AdvertisementInfo implements Serializable {

    /**
     * result : 11
     * contents : [{"iD":7,"imageurl":"Advertisement/logo.png","time":"2017-05-15","url":"http://jp.xun-m.com/"},{"iD":8,"imageurl":"Advertisement/JP.png","time":"2017-05-15","url":"http://jp.xun-m.com/"},{"iD":9,"imageurl":"Advertisement/yibenjiapu.png","time":"2017-05-15","url":"http://jp.xun-m.com/"}]
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
         * iD : 7
         * imageurl : Advertisement/logo.png
         * time : 2017-05-15
         * url : http://jp.xun-m.com/
         */

        private int iD;
        private String imageurl;
        private String time;
        private String url;

        public int getID() {
            return iD;
        }

        public void setID(int iD) {
            this.iD = iD;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
