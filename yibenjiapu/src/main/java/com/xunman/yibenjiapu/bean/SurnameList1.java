package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

public class SurnameList1 implements Serializable {

    /**
     * result : 11
     * contents : [{"ID":1,"branch":30,"cradleintr":"李姓的起源还有一种源于老姓说。这种说法主要源于对老子姓氏的考证， 认为老子不姓李而姓老， 李姓源于老姓。","oline_number":500,"percentage":"7.900%","surname":"李"},{"ID":2,"cradleintr":"","percentage":"7.100%","surname":"王"},{"ID":3,"cradleintr":"","percentage":"6.830%","surname":"张"},{"ID":4,"cradleintr":"","percentage":"5.340%","surname":"刘"},{"ID":5,"cradleintr":"","percentage":"","surname":"陈"},{"ID":6,"cradleintr":"","percentage":"","surname":"杨"},{"ID":7,"cradleintr":"","percentage":"2.200%","surname":"黄"},{"ID":8,"cradleintr":"","percentage":"","surname":"赵"},{"ID":9,"cradleintr":"","percentage":"2.020%","surname":"周"},{"ID":12,"cradleintr":"","percentage":"1.380%","surname":"孙"},{"ID":11,"cradleintr":"","percentage":"1.430%","surname":"徐"},{"ID":14,"cradleintr":"","percentage":"1.200%","surname":"朱"},{"ID":18,"cradleintr":"","percentage":"1.150%","surname":"郭"},{"ID":15,"cradleintr":"","percentage":"0.920%","surname":"高"},{"ID":13,"cradleintr":"","percentage":"1.140%","surname":"胡"}]
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

    public static class ContentsBean implements Serializable{
        /**
         * ID : 1
         * branch : 30
         * cradleintr : 李姓的起源还有一种源于老姓说。这种说法主要源于对老子姓氏的考证， 认为老子不姓李而姓老， 李姓源于老姓。
         * oline_number : 500
         * number : 9500
         * surname : 李
         */

        private int ID;
        private int branch;
        private String cradleintr;
        private int oline_number;
        private String number;
        private String surname;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

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

        public int getOline_number() {
            return oline_number;
        }

        public void setOline_number(int oline_number) {
            this.oline_number = oline_number;
        }

        public String getNumber() {
            return number;
        }

        public void setnumber(String number) {
            this.number = number;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }
}
