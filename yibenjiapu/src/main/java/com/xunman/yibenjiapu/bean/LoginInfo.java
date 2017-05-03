package com.xunman.yibenjiapu.bean;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/3/30 0027
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  登陆成功返回信息
 */

public class LoginInfo {


    /**
     * result : 11
     * account_info : {"createtime":"2017-03-29 17:41:43.0","email":"dnjdj@ccnx.com","grade":0,"id":2,"infoid":1,"money":0,"my_points":0,"password":"","recent_operation_time":"2017-03-29 17:44:48.0","sur":"胡","telephone":"18281860528","vip":0}
     * user_info : {"age":25,"branch_id":1,"card":"513021199210263913","email":"dnjdj@ccnx.com","father":0,"id":1,"mather":0,"name":"杨","origin":"四川 达川","place":"hsbbdbbxb","recent_operation_time":"2017-03-29 17:44:48.0","setupid":2,"sex":"男","sur":"hu胡","telephone":"18281860528"}
     * surname_info : {"branch":21,"cradleintr":"胡姓是当今常见姓氏，读音作hú（ㄏㄨˊ），在《百家姓》中排名第158位。在2007年全国姓氏人口排名第13位，分布广，约占全国汉族人口1.31%，为中国人口超过1%的十九个大姓之一。起源于周王朝初期的封地陈国，国都在今天的河南省淮阳县，得姓始祖为舜帝后裔陈国首任君主胡公满。湖北、江西、安徽、浙江、四川、山东、湖南多此姓，上述七省胡姓约占全国汉族胡姓人口65%。其中湖北省胡姓约占全国汉族胡姓10.4%。","id":13,"number":1400,"online_number":12,"praise":28,"surname":"胡"}
     */

    private int result;
    private AccountInfoBean account_info;
    private UserInfoBean user_info;
    private SurnameInfoBean surname_info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public AccountInfoBean getAccount_info() {
        return account_info;
    }

    public void setAccount_info(AccountInfoBean account_info) {
        this.account_info = account_info;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public SurnameInfoBean getSurname_info() {
        return surname_info;
    }

    public void setSurname_info(SurnameInfoBean surname_info) {
        this.surname_info = surname_info;
    }

    public static class AccountInfoBean {
        /**
         * createtime : 2017-03-29 17:41:43.0
         * email : dnjdj@ccnx.com
         * grade : 0
         * id : 2
         * infoid : 1
         * money : 0
         * my_points : 0
         * password :
         * recent_operation_time : 2017-03-29 17:44:48.0
         * sur : 胡
         * telephone : 18281860528
         * vip : 0
         */

        private String createtime;
        private String email;
        private int grade;
        private int id;
        private int infoid;
        private int money;
        private int my_points;
        private String password;
        private String recent_operation_time;
        private String sur;
        private String telephone;
        private int vip;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoid() {
            return infoid;
        }

        public void setInfoid(int infoid) {
            this.infoid = infoid;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getMy_points() {
            return my_points;
        }

        public void setMy_points(int my_points) {
            this.my_points = my_points;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRecent_operation_time() {
            return recent_operation_time;
        }

        public void setRecent_operation_time(String recent_operation_time) {
            this.recent_operation_time = recent_operation_time;
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

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }
    }

    public static class UserInfoBean {
        /**
         * age : 25
         * branch_id : 1
         * card : 513021199210263913
         * email : dnjdj@ccnx.com
         * father : 0
         * id : 1
         * mather : 0
         * name : 杨
         * origin : 四川 达川
         * place : hsbbdbbxb
         * recent_operation_time : 2017-03-29 17:44:48.0
         * setupid : 2
         * sex : 男
         * sur : hu胡
         * telephone : 18281860528
         */

        private int age;
        private int branch_id;
        private String card;
        private String email;
        private int father;
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

    public static class SurnameInfoBean {
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
}
