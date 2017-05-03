package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/3/30 0030 16:25
 * 包名：com.xunman.yibenjiapu.bean
 * 文件名： ${name}
 * 描述：  分支
 */

public class Family_branch  implements Serializable {

    /**
     * result : 11
     * Branch : [{"createtime":"2017-03-30 16:06:44.0","id":2,"number":1,"recent_operation_time":"2017-03-30 16:06:44.0","sur":"李","sur_id":2}]
     */

    private int result;
    private List<BranchBean> Branch;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<BranchBean> getBranch() {
        return Branch;
    }

    public void setBranch(List<BranchBean> Branch) {
        this.Branch = Branch;
    }

    public static class BranchBean {
        /**
         * createtime : 2017-03-30 16:06:44.0
         * id : 2
         * number : 1
         * recent_operation_time : 2017-03-30 16:06:44.0
         * sur : 李
         * sur_id : 2
         */

        private String createtime;
        private int id;
        private int number;
        private String recent_operation_time;
        private String sur;
        private int sur_id;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
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

        public int getSur_id() {
            return sur_id;
        }

        public void setSur_id(int sur_id) {
            this.sur_id = sur_id;
        }
    }
}
