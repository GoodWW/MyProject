package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名： UpdateSoftDemo
 * 创建者： xxxxx
 * 创建时间：  2017/5/5 0005 18:08
 * 包名：com.szy.update
 * 文件名： ${name}
 * 描述：  资讯详情页面实体类
 */

public class InformationDetailsBean {

    /**
     * result : 11
     */

    private int result;
    private List<InfoDetailsBean> info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<InfoDetailsBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoDetailsBean> info) {
        this.info = info;
    }

    public static class InfoDetailsBean implements Serializable {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
