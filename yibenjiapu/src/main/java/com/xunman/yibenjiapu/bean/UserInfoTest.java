package com.xunman.yibenjiapu.bean;

import android.graphics.Bitmap;

import java.util.List;

/**
 * 项目名： ServiceDemo
 * Created by lwk on 2017/3/3.
 * 描述：  用户信息实体类
 */

public class UserInfoTest {
        /**
         *
         *
         * Avatar : 头像
         * Surname : 用户的姓
         * Name ：用户的名
         * IdNumber : 身份证号
         * Gender : 性别
         * Age : 年龄
         * NativePlace : 籍贯
         * PlaceOfResidence : 常住地
         * Phone : 电话
         * Email : 邮箱
         */
        private String Nickname;
        private Bitmap Avatar;
        private String Surname;
        private String Name;
        private String IdNumber;
        private String Gender;
        private int age;
        private String NativePlace;
        private String PlaceOfResidence;
        private String Phone;
        private String Email;

        public String getNickname() {
            return Nickname;
        }
        public void setNickname(String nickname) {
            Nickname = nickname;
        }

        public Bitmap getAvatar() {
            return Avatar;
        }
        public void setAvatar(Bitmap avatar) {
            Avatar = avatar;
        }

        public String getSurname() {
            return Surname;
        }
        public void setSurname(String surname) {
            Surname = surname;
        }

        public String getName() {
            return Name;
        }
        public void setName(String name) {
            Name = name;
        }

        public String getIdNumber() {
            return IdNumber;
        }
        public void setIdNumber(String idNumber) {
            IdNumber = idNumber;
        }

        public String getGender() {
            return Gender;
        }
        public void setGender(String gender) {
            Gender = gender;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String getNativePlace() {
            return NativePlace;
        }
        public void setNativePlace(String nativePlace) {
            NativePlace = nativePlace;
        }

        public String getPlaceOfResidence() {
            return PlaceOfResidence;
        }
        public void setPlaceOfResidence(String placeOfResidence) {
            PlaceOfResidence = placeOfResidence;
        }

        public String getPhone() {
            return Phone;
        }
        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getEmail() {
            return Email;
        }
        public void setEmail(String email) {
            Email = email;
        }
}

