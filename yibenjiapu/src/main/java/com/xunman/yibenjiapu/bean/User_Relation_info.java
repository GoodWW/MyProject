package com.xunman.yibenjiapu.bean;

import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/4/10 0010 13:31
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  家谱分支返回家谱树界面信息
 */

public class User_Relation_info {

    /**
     * result : 11
     * info : {"G_father":{"age":73,"branch_id":2,"card":"510104199308012114","child":"[5,13,14]","email":"12345@qq.com","father":0,"grand":"N","id":7,"mather":0,"name":"爷","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"爷","telephone":"12345678904"},"G_mather":{"age":70,"branch_id":2,"card":"510104199308012115","child":"[5,13,14]","email":"12345@qq.com","father":0,"grand":"N","id":8,"mather":0,"name":"奶","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"奶","telephone":"12345678905"},"bor_sis":[{"age":25,"branch_id":2,"card":"510104199308012111","child":"[9,10]","email":"12345@qq.com","father":5,"grand":"N","id":2,"mather":6,"name":"自己","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"我","telephone":"12345678901"},{"age":24,"branch_id":2,"card":"510104199308012118","email":"12345@qq.com","father":5,"grand":"N","id":11,"mather":6,"name":"弟","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"兄","telephone":"12345678908"},{"age":26,"branch_id":2,"card":"510104199308012119","email":"12345@qq.com","father":5,"grand":"N","id":12,"mather":6,"name":"妹","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"姐","telephone":"12345678909"}],"child":[{"age":4,"branch_id":2,"card":"510104199308012116","email":"12345@qq.com","father":2,"grand":"N","id":9,"mather":15,"name":"子","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"儿","telephone":"12345678906"},{"age":3,"branch_id":2,"card":"510104199308012117","email":"12345@qq.com","father":2,"grand":"N","id":10,"mather":15,"name":"儿","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"女","telephone":"12345678907"}],"father":{"age":48,"branch_id":2,"card":"510104199308012112","child":"[2,11,12]","email":"12345@qq.com","father":7,"grand":"N","id":5,"mather":8,"name":"亲","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"父","telephone":"12345678902"},"mather":{"age":46,"branch_id":2,"card":"510104199308012113","child":"[2,11,12]","email":"12345@qq.com","father":0,"grand":"N","id":6,"mather":0,"name":"亲","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"母","telephone":"12345678903"},"me":{"age":25,"branch_id":2,"card":"510104199308012111","child":"[9,10]","email":"12345@qq.com","father":5,"grand":"N","id":2,"mather":6,"name":"自己","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"我","telephone":"12345678901"}}
     */

    private int result;
    private InfoBean info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * G_father : {"age":73,"branch_id":2,"card":"510104199308012114","child":"[5,13,14]","email":"12345@qq.com","father":0,"grand":"N","id":7,"mather":0,"name":"爷","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"爷","telephone":"12345678904"}
         * G_mather : {"age":70,"branch_id":2,"card":"510104199308012115","child":"[5,13,14]","email":"12345@qq.com","father":0,"grand":"N","id":8,"mather":0,"name":"奶","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"奶","telephone":"12345678905"}
         * bor_sis : [{"age":25,"branch_id":2,"card":"510104199308012111","child":"[9,10]","email":"12345@qq.com","father":5,"grand":"N","id":2,"mather":6,"name":"自己","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"我","telephone":"12345678901"},{"age":24,"branch_id":2,"card":"510104199308012118","email":"12345@qq.com","father":5,"grand":"N","id":11,"mather":6,"name":"弟","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"兄","telephone":"12345678908"},{"age":26,"branch_id":2,"card":"510104199308012119","email":"12345@qq.com","father":5,"grand":"N","id":12,"mather":6,"name":"妹","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"姐","telephone":"12345678909"}]
         * child : [{"age":4,"branch_id":2,"card":"510104199308012116","email":"12345@qq.com","father":2,"grand":"N","id":9,"mather":15,"name":"子","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"儿","telephone":"12345678906"},{"age":3,"branch_id":2,"card":"510104199308012117","email":"12345@qq.com","father":2,"grand":"N","id":10,"mather":15,"name":"儿","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"女","telephone":"12345678907"}]
         * father : {"age":48,"branch_id":2,"card":"510104199308012112","child":"[2,11,12]","email":"12345@qq.com","father":7,"grand":"N","id":5,"mather":8,"name":"亲","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"父","telephone":"12345678902"}
         * mather : {"age":46,"branch_id":2,"card":"510104199308012113","child":"[2,11,12]","email":"12345@qq.com","father":0,"grand":"N","id":6,"mather":0,"name":"亲","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"女","sur":"母","telephone":"12345678903"}
         * me : {"age":25,"branch_id":2,"card":"510104199308012111","child":"[9,10]","email":"12345@qq.com","father":5,"grand":"N","id":2,"mather":6,"name":"自己","origin":"四川 成都","place":"得了","recent_operation_time":"2017-04-08 13:58:51.0","setupid":2,"sex":"男","sur":"我","telephone":"12345678901"}
         */

        private GFatherBean G_father;
        private GMatherBean G_mather;
        private FatherBean father;
        private MatherBean mather;
        private MeBean me;
        private List<BorSisBean> bor_sis;
        private List<ChildBean> child;

        public GFatherBean getG_father() {
            return G_father;
        }

        public void setG_father(GFatherBean G_father) {
            this.G_father = G_father;
        }

        public GMatherBean getG_mather() {
            return G_mather;
        }

        public void setG_mather(GMatherBean G_mather) {
            this.G_mather = G_mather;
        }

        public FatherBean getFather() {
            return father;
        }

        public void setFather(FatherBean father) {
            this.father = father;
        }

        public MatherBean getMather() {
            return mather;
        }

        public void setMather(MatherBean mather) {
            this.mather = mather;
        }

        public MeBean getMe() {
            return me;
        }

        public void setMe(MeBean me) {
            this.me = me;
        }

        public List<BorSisBean> getBor_sis() {
            return bor_sis;
        }

        public void setBor_sis(List<BorSisBean> bor_sis) {
            this.bor_sis = bor_sis;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class GFatherBean {
            /**
             * age : 73
             * branch_id : 2
             * card : 510104199308012114
             * child : [5,13,14]
             * email : 12345@qq.com
             * father : 0
             * grand : N
             * id : 7
             * mather : 0
             * name : 爷
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 男
             * sur : 爷
             * telephone : 12345678904
             */

            private int age;
            private int branch_id;
            private String card;
            private String child;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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

        public static class GMatherBean {
            /**
             * age : 70
             * branch_id : 2
             * card : 510104199308012115
             * child : [5,13,14]
             * email : 12345@qq.com
             * father : 0
             * grand : N
             * id : 8
             * mather : 0
             * name : 奶
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 女
             * sur : 奶
             * telephone : 12345678905
             */

            private int age;
            private int branch_id;
            private String card;
            private String child;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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

        public static class FatherBean {
            /**
             * age : 48
             * branch_id : 2
             * card : 510104199308012112
             * child : [2,11,12]
             * email : 12345@qq.com
             * father : 7
             * grand : N
             * id : 5
             * mather : 8
             * name : 亲
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 男
             * sur : 父
             * telephone : 12345678902
             */

            private int age;
            private int branch_id;
            private String card;
            private String child;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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

        public static class MatherBean {
            /**
             * age : 46
             * branch_id : 2
             * card : 510104199308012113
             * child : [2,11,12]
             * email : 12345@qq.com
             * father : 0
             * grand : N
             * id : 6
             * mather : 0
             * name : 亲
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 女
             * sur : 母
             * telephone : 12345678903
             */

            private int age;
            private int branch_id;
            private String card;
            private String child;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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

        public static class MeBean {
            /**
             * age : 25
             * branch_id : 2
             * card : 510104199308012111
             * child : [9,10]
             * email : 12345@qq.com
             * father : 5
             * grand : N
             * id : 2
             * mather : 6
             * name : 自己
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 男
             * sur : 我
             * telephone : 12345678901
             */

            private int age;
            private int branch_id;
            private String card;
            private String child;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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

        public static class BorSisBean {
            /**
             * age : 25
             * branch_id : 2
             * card : 510104199308012111
             * child : [9,10]
             * email : 12345@qq.com
             * father : 5
             * grand : N
             * id : 2
             * mather : 6
             * name : 自己
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 男
             * sur : 我
             * telephone : 12345678901
             */

            private int age;
            private int branch_id;
            private String card;
            private String child;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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

        public static class ChildBean {
            /**
             * age : 4
             * branch_id : 2
             * card : 510104199308012116
             * email : 12345@qq.com
             * father : 2
             * grand : N
             * id : 9
             * mather : 15
             * name : 子
             * origin : 四川 成都
             * place : 得了
             * recent_operation_time : 2017-04-08 13:58:51.0
             * setupid : 2
             * sex : 男
             * sur : 儿
             * telephone : 12345678906
             */

            private int age;
            private int branch_id;
            private String card;
            private String email;
            private int father;
            private String grand;
            private int id;
            private int mather;
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

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
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

            public int getFather() {
                return father;
            }

            public void setFather(int father) {
                this.father = father;
            }

            public String getGrand() {
                return grand;
            }

            public void setGrand(String grand) {
                this.grand = grand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMather() {
                return mather;
            }

            public void setMather(int mather) {
                this.mather = mather;
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
    }
}
