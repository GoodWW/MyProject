package com.xunman.yibenjiapu.bean;

/**
 * 创建者： xxxxx
 * 创建时间：  2017/5/10 0005 18:08
 * 包名：com.szy.update
 * 文件名： ${name}
 * 描述：  活动页面实体类
 */

public class ActionLightBean {
    private int result;
    private String city;
    private String community;
    private int num;
    private int lightNum;
    private int days;
    private Boolean isUserLight;

    public Boolean getUserLight() {
        return isUserLight;
    }

    public void setUserLight(Boolean userLight) {
        isUserLight = userLight;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLightNum() {
        return lightNum;
    }

    public void setLightNum(int lightNum) {
        this.lightNum = lightNum;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
