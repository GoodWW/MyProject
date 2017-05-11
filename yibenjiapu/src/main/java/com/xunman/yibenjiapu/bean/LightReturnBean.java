package com.xunman.yibenjiapu.bean;

/**
 * 创建者： xxxxx
 * 创建时间：  2017/5/11 0005 18:08
 * 包名：com.szy.update
 * 文件名： ${name}
 * 描述：  活动点亮返回映射类
 */

public class LightReturnBean {
    private int result;
    //该用户小区总人数和点亮人数
    private int num;
    private int lightNum;
    private int days;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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
}
