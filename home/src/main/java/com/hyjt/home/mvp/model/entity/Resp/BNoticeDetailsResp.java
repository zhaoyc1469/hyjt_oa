package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/23  19:53
 * @desc ${TODO}
 */

public class BNoticeDetailsResp {
    
    /**
     * Id : 201406271656349873721da19b93ced
     * Title : 关于马艳芳、吴强两位同事任职的决定
     * Message : 详见附件
     * Creater : 李大鹏
     * Createtime : 2014-06-27
     * FileUploaderIdOld : ^20140627165633270273925eded341d|关于马艳芳、吴强两位同事任职的决定.docx
     * FilePack : [{"FileName":"关于马艳芳、吴强两位同事任职的决定.docx","FilePath":"/up/20140627165633270273925eded341d关于马艳芳、吴强两位同事任职的决定.docx","IsPhoto":"0"}]
     */

    private String Id;
    private String Title;
    private String Message;
    private String Creater;
    private String Createtime;
    private String FileUploader;
    private String FileUploaderIdOld;
    private List<FilePackBean> FilePack;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getCreater() {
        return Creater;
    }

    public void setCreater(String Creater) {
        this.Creater = Creater;
    }

    public String getCreatetime() {
        return Createtime;
    }

    public void setCreatetime(String Createtime) {
        this.Createtime = Createtime;
    }

    public String getFileUploader() {
        return FileUploader;
    }

    public void setFileUploader(String fileUploader) {
        FileUploader = fileUploader;
    }

    public String getFileUploaderIdOld() {
        return FileUploaderIdOld;
    }

    public void setFileUploaderIdOld(String FileUploaderIdOld) {
        this.FileUploaderIdOld = FileUploaderIdOld;
    }

    public List<FilePackBean> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<FilePackBean> FilePack) {
        this.FilePack = FilePack;
    }

    public static class FilePackBean {
        /**
         * FileName : 关于马艳芳、吴强两位同事任职的决定.docx
         * FilePath : /up/20140627165633270273925eded341d关于马艳芳、吴强两位同事任职的决定.docx
         * IsPhoto : 0
         * FileId
         */

        private String FileName;
        private String FilePath;
        private String IsPhoto;
        private String FileId;

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

        public String getIsPhoto() {
            return IsPhoto;
        }

        public void setIsPhoto(String IsPhoto) {
            this.IsPhoto = IsPhoto;
        }

        public String getFileId() {
            return FileId;
        }

        public void setFileId(String fileId) {
            FileId = fileId;
        }
    }

    @Override
    public String toString() {
        return "BNoticeDetailsResp{" +
                "Id='" + Id + '\'' +
                ", Title='" + Title + '\'' +
                ", Message='" + Message + '\'' +
                ", Creater='" + Creater + '\'' +
                ", Createtime='" + Createtime + '\'' +
                ", FileUploader='" + FileUploader + '\'' +
                ", FileUploaderIdOld='" + FileUploaderIdOld + '\'' +
                ", FilePack=" + FilePack +
                '}';
    }
}
