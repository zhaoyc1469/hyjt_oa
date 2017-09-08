package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class LUReqsListResp {


    /**
     * total : 2
     * rows : [{"Id":"201609051556227230662b33895f773","Number":"20160905-0002","Title":"诉求xxx","AppealPerson":"李大鹏","AppealPersonId":"20120426141601375528685d3e0eb2b","AppealTime":"2016-09-05 15:55:00","Boss":"梁建军","BossId":"201206131327026CA625C97C804E5EAB","State":"诉求"},{"Id":"201609051555155532243422a65eab0","Number":"20160905-0001","Title":null,"AppealPerson":"李大鹏","AppealPersonId":"20120426141601375528685d3e0eb2b","AppealTime":"2016-09-05 15:55:00","Boss":"","BossId":null,"State":"诉求"}]
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
         * Id : 201609051556227230662b33895f773
         * Number : 20160905-0002
         * Title : 诉求xxx
         * AppealPerson : 李大鹏
         * AppealPersonId : 20120426141601375528685d3e0eb2b
         * AppealTime : 2016-09-05 15:55:00
         * Boss : 梁建军
         * BossId : 201206131327026CA625C97C804E5EAB
         * State : 诉求
         */

        private String Id;
        private String Number;
        private String Title;
        private String AppealPerson;
        private String AppealPersonId;
        private String AppealTime;
        private String Boss;
        private String BossId;
        private String State;
        private String ReplyTime;

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

        public String getAppealPerson() {
            return AppealPerson;
        }

        public void setAppealPerson(String AppealPerson) {
            this.AppealPerson = AppealPerson;
        }

        public String getAppealPersonId() {
            return AppealPersonId;
        }

        public void setAppealPersonId(String AppealPersonId) {
            this.AppealPersonId = AppealPersonId;
        }

        public String getAppealTime() {
            return AppealTime;
        }

        public void setAppealTime(String AppealTime) {
            this.AppealTime = AppealTime;
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

        public String getReplyTime() {
            return ReplyTime;
        }

        public void setReplyTime(String replyTime) {
            ReplyTime = replyTime;
        }
    }
}
