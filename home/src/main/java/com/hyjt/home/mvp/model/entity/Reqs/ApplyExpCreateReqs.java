package com.hyjt.home.mvp.model.entity.Reqs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ApplyExpCreateReqs {

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

    private List<FilePackBean> FilePack;

    public List<FilePackBean> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<FilePackBean> FilePack) {
        this.FilePack = FilePack;
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

}
