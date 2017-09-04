package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/9/4  14:54
 * @desc ${TODO}
 */

public class ReportTListResp {


    /**
     * total : 2
     * rows : [{"Id":"2017060509001316362234e22c314cd","PID":"20170605-0001","Boss":"胡成良","CreatePerson":"李大鹏","CreateTime":"2017-06-05 09:00:13","State":"待审核"},{"Id":"20170527143111546280960fbb87c89","PID":"20170527-0001","Boss":"胡成良","CreatePerson":"李大鹏","CreateTime":"2017-05-27 14:31:11","State":"已审核"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * Id : 2017060509001316362234e22c314cd
         * PID : 20170605-0001
         * Boss : 胡成良
         * CreatePerson : 李大鹏
         * CreateTime : 2017-06-05 09:00:13
         * State : 待审核
         */

        private String Id;
        private String PID;
        private String Boss;
        private String CreatePerson;
        private String CreateTime;
        private String State;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getPID() {
            return PID;
        }

        public void setPID(String PID) {
            this.PID = PID;
        }

        public String getBoss() {
            return Boss;
        }

        public void setBoss(String Boss) {
            this.Boss = Boss;
        }

        public String getCreatePerson() {
            return CreatePerson;
        }

        public void setCreatePerson(String CreatePerson) {
            this.CreatePerson = CreatePerson;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
