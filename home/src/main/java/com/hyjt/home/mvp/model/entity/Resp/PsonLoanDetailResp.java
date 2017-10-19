package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class PsonLoanDetailResp {
    /**
     * Id : 201710171201086648301e2af78c776
     * CwPnum : GRJKD20171017-0003
     * SqDate : 2017/10/17 12:01:08
     * CwPpersonal : 李大鹏
     * CwPcompany : 辽宁环宇矿业咨询有限公司
     * CwPdepartment : 信息技术部
     * CwPLeader : 李大鹏
     * CwPreason : 噜啦啦噜啦啦
     * CwPmoney : ￥188,888.00
     * CwPmode : 转账
     * CwRpname : 李大鹏
     * CwRpbank : 中信银行
     * CwRpnum : 1212122
     * CashierQren : 0
     * CwPpersonalQren : 0
     * FlowState : 审批中
     * Flowid : 2017101712010863483011698272e69
     * CwJKState : 提交
     * CurrentPerson : 李大鹏
     * FileUploader : System.Data.Objects.DataClasses.EntityCollection`1[DAL.FileUploader]
     * EntityState : Unchanged
     * EntityKey : System.Data.EntityKey
     * FilePack : [{"FileId":"20171017120100382830181317c5aa0","FileName":"Screenshot_20171016-192943.png","FilePath":"/up/20171017120100382830181317c5aa0Screenshot_20171016-192943.png"}]
     * FlowPack : [{"NodeName":"首签领导","NodePerson":"李大鹏","NodeSign":"/Upload/2017021410336078.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"0","CwBname":null,"CwBnum":null},{"NodeName":"经理","NodePerson":"龙悦","NodeSign":"/Upload/20170511093651105.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"0","CwBname":null,"CwBnum":null},{"NodeName":"董事长","NodePerson":"胡成良","NodeSign":"/Upload/20170509033410580.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"1","CwBname":null,"CwBnum":null},{"NodeName":"财务部","NodePerson":"张斌","NodeSign":"/Upload/20170509103633667.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"1","CwBname":null,"CwBnum":null}]
     */

    private String Id;
    private String CwPnum;
    private String SqDate;
    private String CwPpersonal;
    private String CwPcompany;
    private String CwPdepartment;
    private String CwPLeader;
    private String CwPreason;
    private String CwPmoney;
    private String CwPmode;
    private String CwRpname;
    private String CwRpbank;
    private String CwRpnum;
    private String CashierQren;
    private String CwPpersonalQren;
    private String FlowState;
    private String Flowid;
    private String CwJKState;
    private String CwBname;
    private String CwBnum;
    private String CurrentPerson;
    private String FileUploader;
    private String EntityState;
    private String EntityKey;
    private List<FilePackBean> FilePack;
    private List<FlowPackBean> FlowPack;

    public String getCwBname() {
        return CwBname;
    }

    public void setCwBname(String cwBname) {
        CwBname = cwBname;
    }

    public String getCwBnum() {
        return CwBnum;
    }

    public void setCwBnum(String cwBnum) {
        CwBnum = cwBnum;
    }

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

    public String getCwPcompany() {
        return CwPcompany;
    }

    public void setCwPcompany(String CwPcompany) {
        this.CwPcompany = CwPcompany;
    }

    public String getCwPdepartment() {
        return CwPdepartment;
    }

    public void setCwPdepartment(String CwPdepartment) {
        this.CwPdepartment = CwPdepartment;
    }

    public String getCwPLeader() {
        return CwPLeader;
    }

    public void setCwPLeader(String CwPLeader) {
        this.CwPLeader = CwPLeader;
    }

    public String getCwPreason() {
        return CwPreason;
    }

    public void setCwPreason(String CwPreason) {
        this.CwPreason = CwPreason;
    }

    public String getCwPmoney() {
        return CwPmoney;
    }

    public void setCwPmoney(String CwPmoney) {
        this.CwPmoney = CwPmoney;
    }

    public String getCwPmode() {
        return CwPmode;
    }

    public void setCwPmode(String CwPmode) {
        this.CwPmode = CwPmode;
    }

    public String getCwRpname() {
        return CwRpname;
    }

    public void setCwRpname(String CwRpname) {
        this.CwRpname = CwRpname;
    }

    public String getCwRpbank() {
        return CwRpbank;
    }

    public void setCwRpbank(String CwRpbank) {
        this.CwRpbank = CwRpbank;
    }

    public String getCwRpnum() {
        return CwRpnum;
    }

    public void setCwRpnum(String CwRpnum) {
        this.CwRpnum = CwRpnum;
    }

    public String getCashierQren() {
        return CashierQren;
    }

    public void setCashierQren(String CashierQren) {
        this.CashierQren = CashierQren;
    }

    public String getCwPpersonalQren() {
        return CwPpersonalQren;
    }

    public void setCwPpersonalQren(String CwPpersonalQren) {
        this.CwPpersonalQren = CwPpersonalQren;
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

    public List<FilePackBean> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<FilePackBean> FilePack) {
        this.FilePack = FilePack;
    }

    public List<FlowPackBean> getFlowPack() {
        return FlowPack;
    }

    public void setFlowPack(List<FlowPackBean> FlowPack) {
        this.FlowPack = FlowPack;
    }

    public static class FilePackBean {
        /**
         * FileId : 20171017120100382830181317c5aa0
         * FileName : Screenshot_20171016-192943.png
         * FilePath : /up/20171017120100382830181317c5aa0Screenshot_20171016-192943.png
         */

        private String FileId;
        private String FileName;
        private String FilePath;

        public String getFileId() {
            return FileId;
        }

        public void setFileId(String FileId) {
            this.FileId = FileId;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }
    }

    public static class FlowPackBean {
        /**
         * NodeName : 首签领导
         * NodePerson : 李大鹏
         * NodeSign : /Upload/2017021410336078.jpg
         * NodeMemo : null
         * NodeMemotext : null
         * IsBank : 0
         * CwBname : null
         * CwBnum : null
         */

        private String NodeName;
        private String NodePerson;
        private String NodeSign;
        private String NodeMemo;
        private String NodeMemotext;
        private String IsBank;
        private Object CwBname;
        private Object CwBnum;

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

        public Object getCwBname() {
            return CwBname;
        }

        public void setCwBname(Object CwBname) {
            this.CwBname = CwBname;
        }

        public Object getCwBnum() {
            return CwBnum;
        }

        public void setCwBnum(Object CwBnum) {
            this.CwBnum = CwBnum;
        }
    }
}
