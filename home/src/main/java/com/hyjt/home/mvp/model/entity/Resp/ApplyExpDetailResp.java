package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ApplyExpDetailResp {


    /**
     * Id : 201709271058014523334cd29da064c
     * CwEnum : FYBX20170927-0004
     * SqDate : 2017/9/27 10:58:01
     * CwEpersonal : 孙燚龙
     * CwEcompany : 辽宁环宇矿业咨询有限公司
     * CwEdepartment : 信息技术部
     * CwELeader : 李大鹏
     * CwEmoney : ￥8,000.00
     * CwEWriteoff : 是
     * CwEPayMoney : ￥0
     * CwEPayMode : 现金
     * CwBname : 建设银行
     * CashierQren : /Upload/20170509103044154.jpg
     * CashQrenTime : 2017/9/27 11:06:53
     * CwEpersonalQren :
     * PersonalQrenTime : 2017/9/27 11:08:06
     * FlowState : 审批完成
     * Flowid : 201709271058014898338ab536ddc52
     * CwEState : 收款人已确认
     * CurrentPerson :
     * NodePerson : 李大鹏 '龙悦'胡成良'张斌'
     * CwBnum : 121212
     * CwExpenseReceipt : System.Data.Objects.DataClasses.EntityCollection`1[DAL.CwExpenseReceipt]
     * CwFileUploader : System.Data.Objects.DataClasses.EntityCollection`1[DAL.CwFileUploader]
     * CwFileUploader1Reference : System.Data.Objects.DataClasses.EntityReference`1[DAL.CwFileUploader]
     * CwWriteOff : System.Data.Objects.DataClasses.EntityCollection`1[DAL.CwWriteOff]
     * FileUploader : System.Data.Objects.DataClasses.EntityCollection`1[DAL.FileUploader]
     * EntityState : Modified
     * EntityKey : System.Data.EntityKey
     * WriteOffPack : [{"CwWid":"DGYFKD20170927-0002","CwWidMoney":"￥8000","Payed":"￥8000","UnPayed":"￥0","CwWwiteoffMoney":"￥8000"}]
     * ReceivePack : []
     * FilePack : []
     * FlowPack : [{"NodeName":"首签领导","NodePerson":"李大鹏","NodeSign":"/Upload/2017021410336078.jpg","NodeMemo":"同意","NodeMemotext":"ok","IsBank":"0","CwBname":"","CwBnum":""},{"NodeName":"总经理","NodePerson":"龙悦","NodeSign":"/Upload/20170511093651105.jpg","NodeMemo":"同意","NodeMemotext":"ok2","IsBank":"0","CwBname":"","CwBnum":""},{"NodeName":"董事长","NodePerson":"胡成良","NodeSign":"/Upload/20170509033410580.jpg","NodeMemo":"同意","NodeMemotext":"","IsBank":"0","CwBname":"","CwBnum":""},{"NodeName":"财务部","NodePerson":"张斌","NodeSign":"/Upload/20170509103633667.jpg","NodeMemo":"同意","NodeMemotext":"","IsBank":"1","CwBname":"建设银行","CwBnum":"121212"}]
     */

    private String Id;
    private String CwEnum;
    private String SqDate;
    private String CwEpersonal;
    private String CwEcompany;
    private String CwEdepartment;
    private String CwELeader;
    private String CwEmoney;
    private String CwEWriteoff;
    private String CwEPayMoney;
    private String CwEPayMode;
    private String CwBname;
    private String CashierQren;
    private String CashQrenTime;
    private String CwEpersonalQren;
    private String PersonalQrenTime;
    private String FlowState;
    private String Flowid;
    private String CwEState;
    private String CurrentPerson;
    private String NodePerson;
    private String CwBnum;
    private String CwExpenseReceipt;
    private String CwFileUploader;
    private String CwFileUploader1Reference;
    private String CwWriteOff;
    private String FileUploader;
    private String EntityState;
    private String EntityKey;
    private List<WriteOffPackBean> WriteOffPack;
    private List<?> ReceivePack;
    private List<?> FilePack;
    private List<FlowPackBean> FlowPack;

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

    public String getCwEcompany() {
        return CwEcompany;
    }

    public void setCwEcompany(String CwEcompany) {
        this.CwEcompany = CwEcompany;
    }

    public String getCwEdepartment() {
        return CwEdepartment;
    }

    public void setCwEdepartment(String CwEdepartment) {
        this.CwEdepartment = CwEdepartment;
    }

    public String getCwELeader() {
        return CwELeader;
    }

    public void setCwELeader(String CwELeader) {
        this.CwELeader = CwELeader;
    }

    public String getCwEmoney() {
        return CwEmoney;
    }

    public void setCwEmoney(String CwEmoney) {
        this.CwEmoney = CwEmoney;
    }

    public String getCwEWriteoff() {
        return CwEWriteoff;
    }

    public void setCwEWriteoff(String CwEWriteoff) {
        this.CwEWriteoff = CwEWriteoff;
    }

    public String getCwEPayMoney() {
        return CwEPayMoney;
    }

    public void setCwEPayMoney(String CwEPayMoney) {
        this.CwEPayMoney = CwEPayMoney;
    }

    public String getCwEPayMode() {
        return CwEPayMode;
    }

    public void setCwEPayMode(String CwEPayMode) {
        this.CwEPayMode = CwEPayMode;
    }

    public String getCwBname() {
        return CwBname;
    }

    public void setCwBname(String CwBname) {
        this.CwBname = CwBname;
    }

    public String getCashierQren() {
        return CashierQren;
    }

    public void setCashierQren(String CashierQren) {
        this.CashierQren = CashierQren;
    }

    public String getCashQrenTime() {
        return CashQrenTime;
    }

    public void setCashQrenTime(String CashQrenTime) {
        this.CashQrenTime = CashQrenTime;
    }

    public String getCwEpersonalQren() {
        return CwEpersonalQren;
    }

    public void setCwEpersonalQren(String CwEpersonalQren) {
        this.CwEpersonalQren = CwEpersonalQren;
    }

    public String getPersonalQrenTime() {
        return PersonalQrenTime;
    }

    public void setPersonalQrenTime(String PersonalQrenTime) {
        this.PersonalQrenTime = PersonalQrenTime;
    }

    public String getFlowState() {
        return FlowState;
    }

    public void setFlowState(String FlowState) {
        this.FlowState = FlowState;
    }

    public String getFlowid() {
        return Flowid;
    }

    public void setFlowid(String Flowid) {
        this.Flowid = Flowid;
    }

    public String getCwEState() {
        return CwEState;
    }

    public void setCwEState(String CwEState) {
        this.CwEState = CwEState;
    }

    public String getCurrentPerson() {
        return CurrentPerson;
    }

    public void setCurrentPerson(String CurrentPerson) {
        this.CurrentPerson = CurrentPerson;
    }

    public String getNodePerson() {
        return NodePerson;
    }

    public void setNodePerson(String NodePerson) {
        this.NodePerson = NodePerson;
    }

    public String getCwBnum() {
        return CwBnum;
    }

    public void setCwBnum(String CwBnum) {
        this.CwBnum = CwBnum;
    }

    public String getCwExpenseReceipt() {
        return CwExpenseReceipt;
    }

    public void setCwExpenseReceipt(String CwExpenseReceipt) {
        this.CwExpenseReceipt = CwExpenseReceipt;
    }

    public String getCwFileUploader() {
        return CwFileUploader;
    }

    public void setCwFileUploader(String CwFileUploader) {
        this.CwFileUploader = CwFileUploader;
    }

    public String getCwFileUploader1Reference() {
        return CwFileUploader1Reference;
    }

    public void setCwFileUploader1Reference(String CwFileUploader1Reference) {
        this.CwFileUploader1Reference = CwFileUploader1Reference;
    }

    public String getCwWriteOff() {
        return CwWriteOff;
    }

    public void setCwWriteOff(String CwWriteOff) {
        this.CwWriteOff = CwWriteOff;
    }

    public String getFileUploader() {
        return FileUploader;
    }

    public void setFileUploader(String FileUploader) {
        this.FileUploader = FileUploader;
    }

    public String getEntityState() {
        return EntityState;
    }

    public void setEntityState(String EntityState) {
        this.EntityState = EntityState;
    }

    public String getEntityKey() {
        return EntityKey;
    }

    public void setEntityKey(String EntityKey) {
        this.EntityKey = EntityKey;
    }

    public List<WriteOffPackBean> getWriteOffPack() {
        return WriteOffPack;
    }

    public void setWriteOffPack(List<WriteOffPackBean> WriteOffPack) {
        this.WriteOffPack = WriteOffPack;
    }

    public List<?> getReceivePack() {
        return ReceivePack;
    }

    public void setReceivePack(List<?> ReceivePack) {
        this.ReceivePack = ReceivePack;
    }

    public List<?> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<?> FilePack) {
        this.FilePack = FilePack;
    }

    public List<FlowPackBean> getFlowPack() {
        return FlowPack;
    }

    public void setFlowPack(List<FlowPackBean> FlowPack) {
        this.FlowPack = FlowPack;
    }

    public static class WriteOffPackBean {
        /**
         * CwWid : DGYFKD20170927-0002
         * CwWidMoney : ￥8000
         * Payed : ￥8000
         * UnPayed : ￥0
         * CwWwiteoffMoney : ￥8000
         */

        private String CwWid;
        private String CwWidMoney;
        private String Payed;
        private String UnPayed;
        private String CwWwiteoffMoney;

        public String getCwWid() {
            return CwWid;
        }

        public void setCwWid(String CwWid) {
            this.CwWid = CwWid;
        }

        public String getCwWidMoney() {
            return CwWidMoney;
        }

        public void setCwWidMoney(String CwWidMoney) {
            this.CwWidMoney = CwWidMoney;
        }

        public String getPayed() {
            return Payed;
        }

        public void setPayed(String Payed) {
            this.Payed = Payed;
        }

        public String getUnPayed() {
            return UnPayed;
        }

        public void setUnPayed(String UnPayed) {
            this.UnPayed = UnPayed;
        }

        public String getCwWwiteoffMoney() {
            return CwWwiteoffMoney;
        }

        public void setCwWwiteoffMoney(String CwWwiteoffMoney) {
            this.CwWwiteoffMoney = CwWwiteoffMoney;
        }
    }

    public static class FlowPackBean {
        /**
         * NodeName : 首签领导
         * NodePerson : 李大鹏
         * NodeSign : /Upload/2017021410336078.jpg
         * NodeMemo : 同意
         * NodeMemotext : ok
         * IsBank : 0
         * CwBname :
         * CwBnum :
         */

        private String NodeName;
        private String NodePerson;
        private String NodeSign;
        private String NodeMemo;
        private String NodeMemotext;
        private String IsBank;
        private String CwBname;
        private String CwBnum;

        public String getNodeName() {
            return NodeName;
        }

        public void setNodeName(String NodeName) {
            this.NodeName = NodeName;
        }

        public String getNodePerson() {
            return NodePerson;
        }

        public void setNodePerson(String NodePerson) {
            this.NodePerson = NodePerson;
        }

        public String getNodeSign() {
            return NodeSign;
        }

        public void setNodeSign(String NodeSign) {
            this.NodeSign = NodeSign;
        }

        public String getNodeMemo() {
            return NodeMemo;
        }

        public void setNodeMemo(String NodeMemo) {
            this.NodeMemo = NodeMemo;
        }

        public String getNodeMemotext() {
            return NodeMemotext;
        }

        public void setNodeMemotext(String NodeMemotext) {
            this.NodeMemotext = NodeMemotext;
        }

        public String getIsBank() {
            return IsBank;
        }

        public void setIsBank(String IsBank) {
            this.IsBank = IsBank;
        }

        public String getCwBname() {
            return CwBname;
        }

        public void setCwBname(String CwBname) {
            this.CwBname = CwBname;
        }

        public String getCwBnum() {
            return CwBnum;
        }

        public void setCwBnum(String CwBnum) {
            this.CwBnum = CwBnum;
        }
    }
}
