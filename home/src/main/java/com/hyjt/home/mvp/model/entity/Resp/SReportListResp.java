package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/9/4  14:54
 * @desc ${TODO}
 */

public class SReportListResp {


    /**
     * total : 2
     * rows : [{"Id":"2016092109423302625718648dcdea1","Number":"20160921-0001","Title":"XXXX","ReportPerson":"李大鹏","ReportTime":"2016-09-21 09:42:00","LeapfrogPerson":"","State":"待批示","ReportPersonId":"20120426141601375528685d3e0eb2b","LeapfrogPersonId":null},{"Id":"201608291522155751417c59ca85176","Number":"20160829-0001","Title":"test20160829","ReportPerson":"李大鹏","ReportTime":"2016-08-29 15:39:00","LeapfrogPerson":"胡成良","State":"汇报","ReportPersonId":"20120426141601375528685d3e0eb2b","LeapfrogPersonId":"2012061310023400000009613dfcf86"}]
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
         * Id : 2016092109423302625718648dcdea1
         * Number : 20160921-0001
         * Title : XXXX
         * ReportPerson : 李大鹏
         * ReportTime : 2016-09-21 09:42:00
         * LeapfrogPerson :
         * State : 待批示
         * ReportPersonId : 20120426141601375528685d3e0eb2b
         * LeapfrogPersonId : null
         */

        private String Id;
        private String Number;
        private String Title;
        private String ReportPerson;
        private String ReportTime;
        private String LeapfrogPerson;
        private String State;
        private String ReportPersonId;
        private Object LeapfrogPersonId;

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

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String ReportTime) {
            this.ReportTime = ReportTime;
        }

        public String getLeapfrogPerson() {
            return LeapfrogPerson;
        }

        public void setLeapfrogPerson(String LeapfrogPerson) {
            this.LeapfrogPerson = LeapfrogPerson;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getReportPersonId() {
            return ReportPersonId;
        }

        public void setReportPersonId(String ReportPersonId) {
            this.ReportPersonId = ReportPersonId;
        }

        public Object getLeapfrogPersonId() {
            return LeapfrogPersonId;
        }

        public void setLeapfrogPersonId(Object LeapfrogPersonId) {
            this.LeapfrogPersonId = LeapfrogPersonId;
        }
    }
}
