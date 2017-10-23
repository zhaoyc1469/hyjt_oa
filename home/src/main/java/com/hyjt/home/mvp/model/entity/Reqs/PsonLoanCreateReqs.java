package com.hyjt.home.mvp.model.entity.Reqs;

import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.StaffMsgResp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class PsonLoanCreateReqs {

    private String Id;
    private String CwRpname;
    private String CwRpbank;
    private String CwPnum;
    private String CwRpnum;
    private String CwPpnum;
    private String CwPtext;
    private String Flowid;
    private String CwPmode;
    private String CwPmoney;
    private String CwPcompany;
    private String CwPLeader;
    private String CwPreason;
    private List<FilePackBean> FilePack;

    public String getCwPnum() {
        return CwPnum;
    }

    public void setCwPnum(String cwPnum) {
        CwPnum = cwPnum;
    }

    public String getCwPpnum() {
        return CwPpnum;
    }

    public void setCwPpnum(String cwPpnum) {
        CwPpnum = cwPpnum;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCwRpname() {
        return CwRpname;
    }

    public void setCwRpname(String cwRpname) {
        CwRpname = cwRpname;
    }

    public String getCwRpbank() {
        return CwRpbank;
    }

    public void setCwRpbank(String cwRpbank) {
        CwRpbank = cwRpbank;
    }

    public String getCwRpnum() {
        return CwRpnum;
    }

    public void setCwRpnum(String cwRpnum) {
        CwRpnum = cwRpnum;
    }

    public String getCwPtext() {
        return CwPtext;
    }

    public void setCwPtext(String cwPtext) {
        CwPtext = cwPtext;
    }

    public String getFlowid() {
        return Flowid;
    }

    public void setFlowid(String flowid) {
        Flowid = flowid;
    }

    public String getCwPmode() {
        return CwPmode;
    }

    public void setCwPmode(String cwPmode) {
        CwPmode = cwPmode;
    }

    public String getCwPmoney() {
        return CwPmoney;
    }

    public void setCwPmoney(String cwPmoney) {
        CwPmoney = cwPmoney;
    }

    public String getCwPcompany() {
        return CwPcompany;
    }

    public void setCwPcompany(String cwPcompany) {
        CwPcompany = cwPcompany;
    }

    public String getCwPLeader() {
        return CwPLeader;
    }

    public void setCwPLeader(String cwPLeader) {
        CwPLeader = cwPLeader;
    }

    public String getCwPreason() {
        return CwPreason;
    }

    public void setCwPreason(String cwPreason) {
        CwPreason = cwPreason;
    }


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

    @Override
    public String toString() {
        return "PsonLoanCreateReqs{" +
                "Id='" + Id + '\'' +
                ", CwRpname='" + CwRpname + '\'' +
                ", CwRpbank='" + CwRpbank + '\'' +
                ", CwRpnum='" + CwRpnum + '\'' +
                ", CwPtext='" + CwPtext + '\'' +
                ", Flowid='" + Flowid + '\'' +
                ", CwPmode='" + CwPmode + '\'' +
                ", CwPmoney='" + CwPmoney + '\'' +
                ", CwPcompany='" + CwPcompany + '\'' +
                ", CwPLeader='" + CwPLeader + '\'' +
                ", CwPreason='" + CwPreason + '\'' +
                ", FilePack=" + FilePack.toString() +//(FilePack.size() > 0 ? "" : FilePack.get(0).toString()) +
                '}';
    }


}
