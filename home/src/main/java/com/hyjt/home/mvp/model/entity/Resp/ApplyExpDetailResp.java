package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ApplyExpDetailResp {

    /**
     * Id : 201711091358539331730cbd1cc8286
     * CwEnum : FYBX20171109-0002
     * SqDate : 2017/11/9 13:58:53
     * CwEpersonal : 李大鹏
     * CwEcompany : 辽宁环宇工程咨询管理有限公司
     * CwEdepartment : 信息技术部
     * CwELeader : 李大鹏
     * CwEreason : 他刚刚给
     * CwEmoney : ￥55,566.00
     * CwEmode : 办公费
     * CwEWriteoff : 是
     * CwEtext : 不会哈哈哈
     * CwEPayMoney : ￥54900
     * CwEPayMode : 转账
     * CashierQren : 0
     * CwEpersonalQren : 0
     * FlowState : 审批中
     * Flowid : 201711091358539411734279f4a3d11
     * CwEState : 提交
     * CurrentPerson : 李大鹏
     * CwExpenseReceipt : System.Data.Objects.DataClasses.EntityCollection`1[DAL.CwExpenseReceipt]
     * CwFileUploader : System.Data.Objects.DataClasses.EntityCollection`1[DAL.CwFileUploader]
     * CwFileUploader1Reference : System.Data.Objects.DataClasses.EntityReference`1[DAL.CwFileUploader]
     * CwWriteOff : System.Data.Objects.DataClasses.EntityCollection`1[DAL.CwWriteOff]
     * FileUploader : System.Data.Objects.DataClasses.EntityCollection`1[DAL.FileUploader]
     * EntityState : Unchanged
     * EntityKey : System.Data.EntityKey
     * WriteOffPack : [{"CwWid":"DGYFKD20171029-0009","CwWidMoney":"￥5555","Payed":"￥0","UnPayed":"￥5555","CwWwiteoffMoney":"￥666"}]
     * ReceivePack : [{"CwRname":"李大鹏","CwRbank":"中信银行","CwRnum":"1212122","CwRmoney":"￥6665"}]
     * FilePack : [{"FileId":"201711091358534641462f216bce8fb","FileName":"Screenshot_20171107-082838.png","FilePath":"/up/201711091358534641462f216bce8fbScreenshot_20171107-082838.png"}]
     * FlowPack : [{"NodeName":"首签领导","NodePerson":"李大鹏","NodeSign":"/Upload/2017021410336078.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"0","CwBname":null,"CwBnum":null},{"NodeName":"董事长","NodePerson":"胡成良","NodeSign":"/Upload/20170509033410580.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"0","CwBname":null,"CwBnum":null},{"NodeName":"财务部","NodePerson":"张斌","NodeSign":"/Upload/20170509103633667.jpg","NodeMemo":null,"NodeMemotext":null,"IsBank":"1","CwBname":null,"CwBnum":null}]
     */

    private String Id;
    private String CwEnum;
    private String SqDate;
    private String CwEpersonal;
    private String CwEcompany;
    private String CwEdepartment;
    private String CwELeader;
    private String CwEreason;
    private String CwEmoney;
    private String CwEmode;
    private String CwEWriteoff;
    private String CwEtext;
    private String CwEPayMoney;
    private String CwEPayMode;
    private String CashierQren;
    private String CwEpersonalQren;
    private String FlowState;
    private String Flowid;
    private String CwEState;
    private String CurrentPerson;
    private String CwExpenseReceipt;
    private String CwFileUploader;
    private String CwFileUploader1Reference;
    private String CwWriteOff;
    private String FileUploader;
    private String EntityState;
    private String EntityKey;
    private List<WriteOffPackBean> WriteOffPack;
    private List<ReceivePackBean> ReceivePack;
    private List<FilePackBean> FilePack;
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

    public String getCwEreason() {
        return CwEreason;
    }

    public void setCwEreason(String CwEreason) {
        this.CwEreason = CwEreason;
    }

    public String getCwEmoney() {
        return CwEmoney;
    }

    public void setCwEmoney(String CwEmoney) {
        this.CwEmoney = CwEmoney;
    }

    public String getCwEmode() {
        return CwEmode;
    }

    public void setCwEmode(String CwEmode) {
        this.CwEmode = CwEmode;
    }

    public String getCwEWriteoff() {
        return CwEWriteoff;
    }

    public void setCwEWriteoff(String CwEWriteoff) {
        this.CwEWriteoff = CwEWriteoff;
    }

    public String getCwEtext() {
        return CwEtext;
    }

    public void setCwEtext(String CwEtext) {
        this.CwEtext = CwEtext;
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

    public String getCashierQren() {
        return CashierQren;
    }

    public void setCashierQren(String CashierQren) {
        this.CashierQren = CashierQren;
    }

    public String getCwEpersonalQren() {
        return CwEpersonalQren;
    }

    public void setCwEpersonalQren(String CwEpersonalQren) {
        this.CwEpersonalQren = CwEpersonalQren;
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

    public List<ReceivePackBean> getReceivePack() {
        return ReceivePack;
    }

    public void setReceivePack(List<ReceivePackBean> ReceivePack) {
        this.ReceivePack = ReceivePack;
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

    public static class WriteOffPackBean {
        /**
         * CwWid : DGYFKD20171029-0009
         * CwWidMoney : ￥5555
         * Payed : ￥0
         * UnPayed : ￥5555
         * CwWwiteoffMoney : ￥666
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

    public static class ReceivePackBean {
        /**
         * CwRname : 李大鹏
         * CwRbank : 中信银行
         * CwRnum : 1212122
         * CwRmoney : ￥6665
         */

        private String CwRname;
        private String CwRbank;
        private String CwRnum;
        private String CwRmoney;

        public String getCwRname() {
            return CwRname;
        }

        public void setCwRname(String CwRname) {
            this.CwRname = CwRname;
        }

        public String getCwRbank() {
            return CwRbank;
        }

        public void setCwRbank(String CwRbank) {
            this.CwRbank = CwRbank;
        }

        public String getCwRnum() {
            return CwRnum;
        }

        public void setCwRnum(String CwRnum) {
            this.CwRnum = CwRnum;
        }

        public String getCwRmoney() {
            return CwRmoney;
        }

        public void setCwRmoney(String CwRmoney) {
            this.CwRmoney = CwRmoney;
        }
    }

    public static class FilePackBean {
        /**
         * FileId : 201711091358534641462f216bce8fb
         * FileName : Screenshot_20171107-082838.png
         * FilePath : /up/201711091358534641462f216bce8fbScreenshot_20171107-082838.png
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
