package com.xunman.yibenjiapu.data;

import java.io.Serializable;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/4/7 0007 15:11
 * 包名：com.xunman.yibenjiapu.data
 * 文件名： ${name}
 * 描述：  TODO
 */

public class AddInfo implements Serializable {
    private int setupid;
    private String sur;
    private String name;
    private String card;
    private String sex;
    private int age;
    private String birthday;
    private String origin;
    private String place;
    private String telephone;
    private String email;
    private String password;
    private String nickname;

    public int getSetupid() {
        return setupid;
    }

    public void setSetupid(int setupid) {
        this.setupid = setupid;
    }

    public String getSur() {
        return sur;
    }

    public void setSur(String sur) {
        this.sur = sur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
