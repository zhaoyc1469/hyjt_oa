package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;


public class CompLoanListResp {

    /**
     * total : 51
     * rows : [{"Id":"201710091111282567606a91edca7fc","CwPnum":"GRJKD20171009-0001","SqDate":"2017-10-09 11:11:28","CwPpersonal":"孙燚龙","CwPmoney":"￥1,000.00","FlowState":"审批完成","CwJKState":"出纳已确认"},{"Id":"2017092710134526986570bd304a958","CwPnum":"GRJKD20170927-0001","SqDate":"2017-09-27 10:14:46","CwPpersonal":"孙燚龙","CwPmoney":"￥5,000.00","FlowState":"审批完成","CwJKState":"收款人已确认"},{"Id":"201709260934597845311d1ca6d8119","CwPnum":"GRJKD20170926-0002","SqDate":"2017-09-26 09:35:16","CwPpersonal":"李大鹏","CwPmoney":"￥3,000.00","FlowState":"审批完成","CwJKState":"收款人已确认"},{"Id":"201709260934592630252583ea40bfb","CwPnum":"GRJKD20170926-0001","SqDate":"2017-09-26 16:45:16","CwPpersonal":"李大鹏","CwPmoney":"￥3,000.00","FlowState":"审批中","CwJKState":"提交"},{"Id":"2017092116075505587309a5765ed47","CwPnum":"GRJKD20170921-0002","SqDate":"2017-09-21 16:07:55","CwPpersonal":"贾印丰","CwPmoney":"￥50,000.00","FlowState":"审批完成","CwJKState":"收款人已确认"},{"Id":"20170921154420523700698912cbf92","CwPnum":"GRJKD20170921-0001","SqDate":"2017-09-21 15:44:20","CwPpersonal":"李大鹏","CwPmoney":"￥20,000.00","FlowState":"审批终止","CwJKState":"李大鹏 不同意"},{"Id":"201709201407014932482b87585e89d","CwPnum":"GRJKD20170920-0002","SqDate":"2017-09-20 14:07:01","CwPpersonal":"李大鹏","CwPmoney":"￥10,000.00","FlowState":"审批完成","CwJKState":"出纳已确认"},{"Id":"201709201405310468391ebc9725c4e","CwPnum":"GRJKD20170920-0001","SqDate":"2017-09-20 14:05:31","CwPpersonal":"李大鹏","CwPmoney":"￥10,000.00","FlowState":"审批终止","CwJKState":"龙悦审批通过"},{"Id":"201709131018176013603eb2a6c2b5a","CwPnum":"GRJKD20170913-0002","SqDate":"2017-09-13 10:18:17","CwPpersonal":"李大鹏","CwPmoney":"￥2,000.00","FlowState":"审批完成","CwJKState":"出纳已确认"},{"Id":"2017091308121568084325bace13ab7","CwPnum":"GRJKD20170913-0001","SqDate":"2017-09-13 08:12:24","CwPpersonal":"李大鹏","CwPmoney":"￥2,000.00","FlowState":"审批完成","CwJKState":"出纳已确认"}]
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
         * Id : 201710091111282567606a91edca7fc
         * CwPnum : GRJKD20171009-0001
         * SqDate : 2017-10-09 11:11:28
         * CwPpersonal : 孙燚龙
         * CwPmoney : ￥1,000.00
         * FlowState : 审批完成
         * CwJKState : 出纳已确认
         */

        private String Id;
        private String CwPnum;
        private String SqDate;
        private String CwPpersonal;
        private String CwPmoney;
        private String FlowState;
        private String CwJKState;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getCwPnum() {
            return CwPnum;
        }

        public void setCwPnum(String CwPnum) {
            this.CwPnum = CwPnum;
        }

        public String getSqDate() {
            return SqDate;
        }

        public void setSqDate(String SqDate) {
            this.SqDate = SqDate;
        }

        public String getCwPpersonal() {
            return CwPpersonal;
        }

        public void setCwPpersonal(String CwPpersonal) {
            this.CwPpersonal = CwPpersonal;
        }

        public String getCwPmoney() {
            return CwPmoney;
        }

        public void setCwPmoney(String CwPmoney) {
            this.CwPmoney = CwPmoney;
        }

        public String getFlowState() {
            return FlowState;
        }

        public void setFlowState(String FlowState) {
            this.FlowState = FlowState;
        }

        public String getCwJKState() {
            return CwJKState;
        }

        public void setCwJKState(String CwJKState) {
            this.CwJKState = CwJKState;
        }
    }
}
