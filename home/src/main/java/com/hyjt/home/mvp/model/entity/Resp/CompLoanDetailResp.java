package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */

public class CompLoanDetailResp {


    /**
     * Id : 201709260956222827817a50df4f19a
     * CwC_id : 201709251354359705047cd02f2feed
     * CwCnum : DGYFKD20170926-0001
     * SqDate : 2017/9/26 9:56:22
     * CwCpersonal : 李大鹏
     * CwCcompany : 辽宁环宇矿业咨询有限公司
     * CwCdepartment : 信息技术部
     * CwCLeader : 李大鹏
     * CwCreason : 测试
     * CwCmode : 电汇
     * CwCcontracTime : 2017/9/21 0:00:00
     * CwCmoney : ￥3,000.00
     * CwCmodetext : 备注
     * CwCSupplierID : 20150513153836854197033f00d6c7a
     * CwCSupplierI : 南京豪晟工贸有限公司
     * CwCSupbank : 中国建设银行
     * CwCSupnum : XXXXXXXXX
     * CashierQren : /Upload/2017021410336078.jpg
     * CashQrenTime : 2017/9/26 10:04:15
     * CwBname :
     * CwBnum :
     * CwCpersonalQren : /Upload/2017021410336078.jpg
     * PersonalQrenTime : 2017/9/26 10:05:06
     * FlowState : 审批完成
     * Flowid : 201709260956222602814f557b7e2a5
     * CwJKState : 收款人已确认
     * CurrentPerson :
     * NodePerson : 李大鹏 '龙悦'胡成良'张斌'
     * CwContract : DAL.CwContract
     * CwContractReference : System.Data.Objects.DataClasses.EntityReference`1[DAL.CwContract]
     * Supplier : DAL.Supplier
     * SupplierReference : System.Data.Objects.DataClasses.EntityReference`1[DAL.Supplier]
     * EntityState : Modified
     * EntityKey : System.Data.EntityKey
     * CwCOnum : KYTH20170925-0001
     * FlowPack : [{"NodeName":"首签领导","NodePerson":"李大鹏","NodeSign":"/Upload/2017021410336078.jpg","NodeMemo":"同意","NodeMemotext":"","IsBank":"0","CwBname":"","CwBnum":""},{"NodeName":"总经理","NodePerson":"龙悦","NodeSign":"/Upload/20170511093651105.jpg","NodeMemo":"同意","NodeMemotext":"","IsBank":"0","CwBname":"","CwBnum":""},{"NodeName":"董事长","NodePerson":"胡成良","NodeSign":"/Upload/20170509033410580.jpg","NodeMemo":"同意","NodeMemotext":"","IsBank":"1","CwBname":"","CwBnum":""},{"NodeName":"财务部","NodePerson":"张斌","NodeSign":"/Upload/20170509103633667.jpg","NodeMemo":"同意","NodeMemotext":"","IsBank":"1","CwBname":"","CwBnum":""}]
     */

    private String Id;
    private String CwC_id;
    private String CwCnum;
    private String SqDate;
    private String CwCpersonal;
    private String CwCcompany;
    private String CwCdepartment;
    private String CwCLeader;
    private String CwCreason;
    private String CwCmode;
    private String CwCcontracTime;
    private String CwCmoney;
    private String CwCSupplierID;
    private String CwCSupplierI;
    private String CwCSupbank;
    private String CwCSupnum;
    private String CashierQren;
    private String CashQrenTime;
    private String CwBname;
    private String CwBnum;
    private String CwCpersonalQren;
    private String PersonalQrenTime;
    private String FlowState;
    private String Flowid;
    private String CwJKState;
    private String CurrentPerson;
    private String NodePerson;
    private String CwContract;
    private String CwCmodetext;
    private String CwContractReference;
    private String Supplier;
    private String SupplierReference;
    private String EntityState;
    private String EntityKey;
    private String CwCOnum;
    private List<FlowPackBean> FlowPack;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCwC_id() {
        return CwC_id;
    }

    public void setCwC_id(String CwC_id) {
        this.CwC_id = CwC_id;
    }

    public String getCwCnum() {
        return CwCnum;
    }

    public void setCwCnum(String CwCnum) {
        this.CwCnum = CwCnum;
    }

    public String getSqDate() {
        return SqDate;
    }

    public void setSqDate(String SqDate) {
        this.SqDate = SqDate;
    }

    public String getCwCpersonal() {
        return CwCpersonal;
    }

    public void setCwCpersonal(String CwCpersonal) {
        this.CwCpersonal = CwCpersonal;
    }

    public String getCwCcompany() {
        return CwCcompany;
    }

    public void setCwCcompany(String CwCcompany) {
        this.CwCcompany = CwCcompany;
    }

    public String getCwCdepartment() {
        return CwCdepartment;
    }

    public void setCwCdepartment(String CwCdepartment) {
        this.CwCdepartment = CwCdepartment;
    }

    public String getCwCLeader() {
        return CwCLeader;
    }

    public void setCwCLeader(String CwCLeader) {
        this.CwCLeader = CwCLeader;
    }

    public String getCwCreason() {
        return CwCreason;
    }

    public void setCwCreason(String CwCreason) {
        this.CwCreason = CwCreason;
    }

    public String getCwCmode() {
        return CwCmode;
    }

    public void setCwCmode(String CwCmode) {
        this.CwCmode = CwCmode;
    }

    public String getCwCcontracTime() {
        return CwCcontracTime;
    }

    public void setCwCcontracTime(String CwCcontracTime) {
        this.CwCcontracTime = CwCcontracTime;
    }

    public String getCwCmoney() {
        return CwCmoney;
    }

    public void setCwCmoney(String CwCmoney) {
        this.CwCmoney = CwCmoney;
    }

    public String getCwCmodetext() {
        return CwCmodetext;
    }

    public void setCwCmodetext(String CwCmodetext) {
        this.CwCmodetext = CwCmodetext;
    }

    public String getCwCSupplierID() {
        return CwCSupplierID;
    }

    public void setCwCSupplierID(String CwCSupplierID) {
        this.CwCSupplierID = CwCSupplierID;
    }

    public String getCwCSupplierI() {
        return CwCSupplierI;
    }

    public void setCwCSupplierI(String CwCSupplierI) {
        this.CwCSupplierI = CwCSupplierI;
    }

    public String getCwCSupbank() {
        return CwCSupbank;
    }

    public void setCwCSupbank(String CwCSupbank) {
        this.CwCSupbank = CwCSupbank;
    }

    public String getCwCSupnum() {
        return CwCSupnum;
    }

    public void setCwCSupnum(String CwCSupnum) {
        this.CwCSupnum = CwCSupnum;
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

    public String getCwCpersonalQren() {
        return CwCpersonalQren;
    }

    public void setCwCpersonalQren(String CwCpersonalQren) {
        this.CwCpersonalQren = CwCpersonalQren;
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

    public String getCwJKState() {
        return CwJKState;
    }

    public void setCwJKState(String CwJKState) {
        this.CwJKState = CwJKState;
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

    public String getCwContract() {
        return CwContract;
    }

    public void setCwContract(String CwContract) {
        this.CwContract = CwContract;
    }

    public String getCwContractReference() {
        return CwContractReference;
    }

    public void setCwContractReference(String CwContractReference) {
        this.CwContractReference = CwContractReference;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String Supplier) {
        this.Supplier = Supplier;
    }

    public String getSupplierReference() {
        return SupplierReference;
    }

    public void setSupplierReference(String SupplierReference) {
        this.SupplierReference = SupplierReference;
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

    public String getCwCOnum() {
        return CwCOnum;
    }

    public void setCwCOnum(String CwCOnum) {
        this.CwCOnum = CwCOnum;
    }

    public List<FlowPackBean> getFlowPack() {
        return FlowPack;
    }

    public void setFlowPack(List<FlowPackBean> FlowPack) {
        this.FlowPack = FlowPack;
    }

    public static class FlowPackBean {
        /**
         * NodeName : 首签领导
         * NodePerson : 李大鹏
         * NodeSign : /Upload/2017021410336078.jpg
         * NodeMemo : 同意
         * NodeMemotext :
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
