package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class CIdeaListResp {

    /**
     * total : 1
     * rows : [{"Id":"201709081535578418691e69679bf78","Number":"20170908-0001","Title":"亘古不变吧","ReportPerson":"孙燚龙","ReportPersonId":"20170328081716366556360b7a83bf1","Boss":"李大鹏","BossId":"20120426141601375528685d3e0eb2b","State":"待批示"}]
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
         * Id : 201709081535578418691e69679bf78
         * Number : 20170908-0001
         * Title : 亘古不变吧
         * ReportPerson : 孙燚龙
         * ReportPersonId : 20170328081716366556360b7a83bf1
         * Boss : 李大鹏
         * BossId : 20120426141601375528685d3e0eb2b
         * State : 待批示
         */

        private String Id;
        private String Number;
        private String Title;
        private String ReportPerson;
        private String ReportPersonId;
        private String Boss;
        private String BossId;
        private String State;
        private String ReportTime;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getNumber() {
            return Number;
        }

        public void setNumber(String Number) {
            this.Number = Number;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getReportPerson() {
            return ReportPerson;
        }

        public void setReportPerson(String ReportPerson) {
            this.ReportPerson = ReportPerson;
        }

        public String getReportPersonId() {
            return ReportPersonId;
        }

        public void setReportPersonId(String ReportPersonId) {
            this.ReportPersonId = ReportPersonId;
        }

        public String getBoss() {
            return Boss;
        }

        public void setBoss(String Boss) {
            this.Boss = Boss;
        }

        public String getBossId() {
            return BossId;
        }

        public void setBossId(String BossId) {
            this.BossId = BossId;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String reportTime) {
            ReportTime = reportTime;
        }
    }
}
