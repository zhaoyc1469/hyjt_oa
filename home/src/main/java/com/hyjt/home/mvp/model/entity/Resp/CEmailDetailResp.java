package com.hyjt.home.mvp.model.entity.Resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/30  9:08
 * @desc ${TODO}
 */

public class CEmailDetailResp implements Serializable {


    /**
     * Id : 20170321112436888359204bea7ace9
     * Sender : 李大鹏
     * SenderId : 20120426141601375528685d3e0eb2b
     * Receiver : 齐化龙 蔡福源 孙爱祥 戚文云 李金山
     * SendTime : 2017-03-21 11:24:36
     * Opened : null
     * Theme : 电子档案资料清单
     * Email : 本电子资料清单主要包含航片、图纸等资料，不含报告，请各部门负责人核实清单内容。
     * FilePack : [{"FileId":"201703211124353082688ed0a29873c","FileName":"电子档案资料目录.docx","FilePath":"/up/201703211124353082688ed0a29873c电子档案资料目录.docx"}]
     */

    private String Id;
    private String Sender;
    private String SenderId;
    private String Receiver;
    private String SendTime;
    private Object Opened;
    private String Theme;
    private String Email;
    private List<FilePackBean> FilePack;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String Sender) {
        this.Sender = Sender;
    }

    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String SenderId) {
        this.SenderId = SenderId;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String SendTime) {
        this.SendTime = SendTime;
    }

    public Object getOpened() {
        return Opened;
    }

    public void setOpened(Object Opened) {
        this.Opened = Opened;
    }

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String Theme) {
        this.Theme = Theme;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public List<FilePackBean> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<FilePackBean> FilePack) {
        this.FilePack = FilePack;
    }

    public static class FilePackBean implements Serializable {
        /**
         * FileId : 201703211124353082688ed0a29873c
         * FileName : 电子档案资料目录.docx
         * FilePath : /up/201703211124353082688ed0a29873c电子档案资料目录.docx
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
}
