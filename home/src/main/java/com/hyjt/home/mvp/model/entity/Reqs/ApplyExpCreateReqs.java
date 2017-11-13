package com.hyjt.home.mvp.model.entity.Reqs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ApplyExpCreateReqs {

    private String Id;
    private String CwEcompany;
    private String CwELeader;
    private String CwEreason;
    private String CwEmoney;
    private String CwEmode;
    private String CwEWriteoff;
    private String CwEPayMoney;
    private String CwEPayMode;
    private String CwEtext;
    private String Flowid;
    private List<FilePackBean> FilePack;
    private List<WriteoffPackBean> WriteOffPack;
    private List<ReceivePackBean> ReceivePack;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCwEcompany() {
        return CwEcompany;
    }

    public void setCwEcompany(String cwEcompany) {
        CwEcompany = cwEcompany;
    }

    public String getCwELeader() {
        return CwELeader;
    }

    public void setCwELeader(String cwELeader) {
        CwELeader = cwELeader;
    }

    public String getCwEreason() {
        return CwEreason;
    }

    public void setCwEreason(String cwEreason) {
        CwEreason = cwEreason;
    }

    public String getCwEmoney() {
        return CwEmoney;
    }

    public void setCwEmoney(String cwEmoney) {
        CwEmoney = cwEmoney;
    }

    public String getCwEmode() {
        return CwEmode;
    }

    public void setCwEmode(String cwEmode) {
        CwEmode = cwEmode;
    }

    public String getCwEWriteoff() {
        return CwEWriteoff;
    }

    public void setCwEWriteoff(String cwEWriteoff) {
        CwEWriteoff = cwEWriteoff;
    }

    public String getCwEPayMoney() {
        return CwEPayMoney;
    }

    public void setCwEPayMoney(String cwEPayMoney) {
        CwEPayMoney = cwEPayMoney;
    }

    public String getCwEPayMode() {
        return CwEPayMode;
    }

    public void setCwEPayMode(String cwEPayMode) {
        CwEPayMode = cwEPayMode;
    }

    public String getCwEtext() {
        return CwEtext;
    }

    public void setCwEtext(String cwEtext) {
        CwEtext = cwEtext;
    }

    public String getFlowid() {
        return Flowid;
    }

    public void setFlowid(String flowid) {
        Flowid = flowid;
    }

    public List<FilePackBean> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<FilePackBean> FilePack) {
        this.FilePack = FilePack;
    }

    public List<WriteoffPackBean> getWriteoffPack() {
        return WriteOffPack;
    }

    public void setWriteoffPack(List<WriteoffPackBean> writeoffPack) {
        WriteOffPack = writeoffPack;
    }

    public List<ReceivePackBean> getReceivePack() {
        return ReceivePack;
    }

    public void setReceivePack(List<ReceivePackBean> receivePack) {
        ReceivePack = receivePack;
    }

    public static class FilePackBean implements Serializable {

        private String FileId;
        private String FileName;

        public FilePackBean(String fileId, String fileName) {
            FileId = fileId;
            FileName = fileName;
        }

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

        @Override
        public String toString() {
            return "FilePackBean{" +
                    "FileId='" + FileId + '\'' +
                    ", FileName='" + FileName + '\'' +
                    '}';
        }
    }

    public static class WriteoffPackBean implements Serializable {

        private String CwWid;
        private String CwWidMoney;
        private String CwWwiteoffMoney;

        public WriteoffPackBean() {
        }

        public WriteoffPackBean(String cwWid, String cwWidMoney, String cwWwiteoffMoney) {
            CwWid = cwWid;
            CwWidMoney = cwWidMoney;
            CwWwiteoffMoney = cwWwiteoffMoney;
        }

        public String getCwWid() {
            return CwWid;
        }

        public void setCwWid(String cwWid) {
            CwWid = cwWid;
        }

        public String getCwWidMoney() {
            return CwWidMoney;
        }

        public void setCwWidMoney(String cwWidMoney) {
            CwWidMoney = cwWidMoney;
        }

        public String getCwWwiteoffMoney() {
            return CwWwiteoffMoney;
        }

        public void setCwWwiteoffMoney(String cwWwiteoffMoney) {
            CwWwiteoffMoney = cwWwiteoffMoney;
        }

        @Override
        public String toString() {
            return "WriteoffPackBean{" +
                    "CwWid='" + CwWid + '\'' +
                    ", CwWidMoney='" + CwWidMoney + '\'' +
                    ", CwWwiteoffMoney='" + CwWwiteoffMoney + '\'' +
                    '}';
        }
    }

    public static class ReceivePackBean implements Serializable {

        private String CwRname;
        private String CwRbank;
        private String CwRnum;
        private String CwRmoney;

        public ReceivePackBean(){

        }

        public ReceivePackBean(String cwRname, String cwRbank, String cwRnum, String cwRmoney) {
            CwRname = cwRname;
            CwRbank = cwRbank;
            CwRnum = cwRnum;
            CwRmoney = cwRmoney;
        }

        public String getCwRname() {
            return CwRname;
        }

        public void setCwRname(String cwRname) {
            CwRname = cwRname;
        }

        public String getCwRbank() {
            return CwRbank;
        }

        public void setCwRbank(String cwRbank) {
            CwRbank = cwRbank;
        }

        public String getCwRnum() {
            return CwRnum;
        }

        public void setCwRnum(String cwRnum) {
            CwRnum = cwRnum;
        }

        public String getCwRmoney() {
            return CwRmoney;
        }

        public void setCwRmoney(String cwRmoney) {
            CwRmoney = cwRmoney;
        }

        @Override
        public String toString() {
            return "ReceivePackBean{" +
                    "CwRname='" + CwRname + '\'' +
                    ", CwRbank='" + CwRbank + '\'' +
                    ", CwRnum='" + CwRnum + '\'' +
                    ", CwRmoney='" + CwRmoney + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ApplyExpCreateReqs{" +
                "CwEcompany='" + CwEcompany + '\'' +
                ", CwELeader='" + CwELeader + '\'' +
                ", CwEreason='" + CwEreason + '\'' +
                ", CwEmoney='" + CwEmoney + '\'' +
                ", CwEmode='" + CwEmode + '\'' +
                ", CwEWriteoff='" + CwEWriteoff + '\'' +
                ", CwEPayMoney='" + CwEPayMoney + '\'' +
                ", CwEPayMode='" + CwEPayMode + '\'' +
                ", CwEtext='" + CwEtext + '\'' +
                ", Flowid='" + Flowid + '\'' +
                ", FilePack=" + FilePack.toString() +
                ", WriteOffPack=" + WriteOffPack.toString() +
                ", ReceivePack=" + ReceivePack.toString() +
                '}';
    }
}
