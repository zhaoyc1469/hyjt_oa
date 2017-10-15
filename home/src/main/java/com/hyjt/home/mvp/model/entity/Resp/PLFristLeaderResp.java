package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;


public class PLFristLeaderResp {


    private List<FlowDetailsBean> FlowDetails;

    public List<FlowDetailsBean> getFlowDetails() {
        return FlowDetails;
    }

    public void setFlowDetails(List<FlowDetailsBean> FlowDetails) {
        this.FlowDetails = FlowDetails;
    }

    public static class FlowDetailsBean {
        /**
         * Flowid : 2017070715292750845623d173ed17c
         * Leader : 李大鹏
         */

        private String Flowid;
        private String Leader;

        public String getFlowid() {
            return Flowid;
        }

        public void setFlowid(String Flowid) {
            this.Flowid = Flowid;
        }

        public String getLeader() {
            return Leader;
        }

        public void setLeader(String Leader) {
            this.Leader = Leader;
        }
    }
}
