package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/31  9:14
 * @desc ${TODO}
 */

public class FileUploaderId {

    private String Id;
    private String FileName;

    public FileUploaderId() {
    }

    public FileUploaderId(String id, String FileName) {
        this.Id = id;
        this.FileName = FileName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    @Override
    public String toString() {
        return "FileUploaderId{" +
                "Id='" + Id + '\'' +
                ", FileName='" + FileName + '\'' +
                '}';
    }
}
