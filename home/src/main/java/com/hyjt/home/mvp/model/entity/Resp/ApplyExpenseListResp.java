package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class ApplyExpenseListResp {

    /**
     * total : 9
     * rows : [{"Id":"2017101008503264079447e32502eaa","CwEnum":"FYBX20171010-0001","SqDate":"2017-10-10 08:50:32","CwEpersonal":"李大鹏","CwEmoney":"￥121.00","FlowState":"审批终止","CwEState":"李大鹏 不同意"},{"Id":"201709270926227218153c0f0f95382","CwEnum":"FYBX20170927-0003","SqDate":"2017-09-27 09:26:22","CwEpersonal":"李大鹏","CwEmoney":"￥111.00","FlowState":"审批中","CwEState":"提交"},{"Id":"201709270921333701609234ad260b4","CwEnum":"FYBX20170927-0001","SqDate":"2017-09-27 09:21:33","CwEpersonal":"李大鹏","CwEmoney":"￥","FlowState":"审批中","CwEState":"提交"},{"Id":"2017092709022324282960b58c57a72","CwEnum":"FYBX20170927-0001","SqDate":"2017-09-27 09:06:52","CwEpersonal":"李大鹏","CwEmoney":"￥40,006.00","FlowState":"审批中","CwEState":"提交"},{"Id":"2017092014291517928965b919033d8","CwEnum":"FYBX20170920-0001","SqDate":"2017-09-20 14:29:15","CwEpersonal":"李大鹏","CwEmoney":"￥5,000.00","FlowState":"审批完成","CwEState":"收款人已确认"},{"Id":"201709200820481666424efb1838977","CwEnum":"FYBX20170920-0001","SqDate":"2017-09-20 08:20:48","CwEpersonal":"李大鹏","CwEmoney":"￥666.00","FlowState":"审批中","CwEState":"胡成良审批通过"},{"Id":"201709191135012933401e78d1a7836","CwEnum":"FYBX20170919-0001","SqDate":"2017-09-19 11:35:01","CwEpersonal":"李大鹏","CwEmoney":"￥5,000.00","FlowState":"审批完成","CwEState":"收款人已确认"},{"Id":"201709190947313467684cb985f9fba","CwEnum":"FYBX20170919-0001","SqDate":"2017-09-19 09:47:31","CwEpersonal":"李大鹏","CwEmoney":"￥5,000.00","FlowState":"审批完成","CwEState":"收款人已确认"},{"Id":"201709131034574845504f670b74808","CwEnum":"FYBX20170913-0001","SqDate":"2017-09-13 10:34:57","CwEpersonal":"李大鹏","CwEmoney":"￥5,000.00","FlowState":"审批完成","CwEState":"出纳已确认"}]
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
         * Id : 2017101008503264079447e32502eaa
         * CwEnum : FYBX20171010-0001
         * SqDate : 2017-10-10 08:50:32
         * CwEpersonal : 李大鹏
         * CwEmoney : ￥121.00
         * FlowState : 审批终止
         * CwEState : 李大鹏 不同意
         */

        private String Id;
        private String CwEnum;
        private String SqDate;
        private String CwEpersonal;
        private String CwEmoney;
        private String FlowState;
        private String CwEState;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getCwEnum() {
            return CwEnum;
        }

        public void setCwEnum(String CwEnum) {
            this.CwEnum = CwEnum;
        }

        public String getSqDate() {
            return SqDate;
        }

        public void setSqDate(String SqDate) {
            this.SqDate = SqDate;
        }

        public String getCwEpersonal() {
            return CwEpersonal;
        }

        public void setCwEpersonal(String CwEpersonal) {
            this.CwEpersonal = CwEpersonal;
        }

        public String getCwEmoney() {
            return CwEmoney;
        }

        public void setCwEmoney(String CwEmoney) {
            this.CwEmoney = CwEmoney;
        }

        public String getFlowState() {
            return FlowState;
        }

        public void setFlowState(String FlowState) {
            this.FlowState = FlowState;
        }

        public String getCwEState() {
            return CwEState;
        }

        public void setCwEState(String CwEState) {
            this.CwEState = CwEState;
        }
    }
}
