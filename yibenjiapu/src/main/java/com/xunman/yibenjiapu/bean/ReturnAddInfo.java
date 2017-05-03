package com.xunman.yibenjiapu.bean;

import java.io.Serializable;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/3/7 0007 11:32
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  返回添加重复的信息
 */

public class ReturnAddInfo implements Serializable {

    /**
     * age : 26
     * card : 510821199005106311
     * email : 123456@qq.com
     * iD : 3
     * name : 人文
     * origin : 交警
     * place : 阿坝
     * recent_operation_time : 2017-03-07 00:00:00.0
     * setupid : 3
     * sex : 男
     * sur : 张
     * telephone : 95175385245
     */
//{"age":26,"card":"510821199005106311","email":"123456@qq.com","iD":3,"name":"人文","origin":"交警","place":"阿坝","recent_operation_time":"2017-03-07 00:00:00.0","setupid":3,"sex":"男","sur":"张","telephone":"95175385245"}
    private int age;
    private String card;
    private String email;
    private int iD;
    private String name;
    private String origin;
    private String place;
    private String recent_operation_time;
    private int setupid;
    private String sex;
    private String sur;
    private String telephone;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRecent_operation_time() {
        return recent_operation_time;
    }

    public void setRecent_operation_time(String recent_operation_time) {
        this.recent_operation_time = recent_operation_time;
    }

    public int getSetupid() {
        return setupid;
    }

    public void setSetupid(int setupid) {
        this.setupid = setupid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSur() {
        return sur;
    }

    public void setSur(String sur) {
        this.sur = sur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
