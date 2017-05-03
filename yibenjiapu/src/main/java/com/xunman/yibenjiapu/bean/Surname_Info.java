package com.xunman.yibenjiapu.bean;

import java.io.Serializable;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/3/30 0030 9:16
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  TODO
 */

public class Surname_Info implements Serializable{

    /**
     * branch : 21
     * cradleintr : 胡姓是当今常见姓氏，读音作hú（ㄏㄨˊ），在《百家姓》中排名第158位。在2007年全国姓氏人口排名第13位，分布广，约占全国汉族人口1.31%，为中国人口超过1%的十九个大姓之一。起源于周王朝初期的封地陈国，国都在今天的河南省淮阳县，得姓始祖为舜帝后裔陈国首任君主胡公满。湖北、江西、安徽、浙江、四川、山东、湖南多此姓，上述七省胡姓约占全国汉族胡姓人口65%。其中湖北省胡姓约占全国汉族胡姓10.4%。
     * id : 13
     * number : 1400
     * online_number : 12
     * praise : 28
     * surname : 胡
     */

    private int branch;
    private String cradleintr;
    private int id;
    private int number;
    private int online_number;
    private int praise;
    private String surname;

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getCradleintr() {
        return cradleintr;
    }

    public void setCradleintr(String cradleintr) {
        this.cradleintr = cradleintr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOnline_number() {
        return online_number;
    }

    public void setOnline_number(int online_number) {
        this.online_number = online_number;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
