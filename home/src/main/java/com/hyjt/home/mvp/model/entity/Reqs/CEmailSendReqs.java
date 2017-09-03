package com.hyjt.home.mvp.model.entity.Reqs;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/31  9:05
 * @desc ${TODO}
 */

public class CEmailSendReqs {

    private String Email;
    private String Theme;
    private List<StaffNameId> SysPersonId;
    private List<FileUploaderId> FileUploaderId;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public List<com.hyjt.home.mvp.model.entity.Reqs.FileUploaderId> getFileUploaderId() {
        return FileUploaderId;
    }

    public void setFileUploaderId(List<com.hyjt.home.mvp.model.entity.Reqs.FileUploaderId> fileUploaderId) {
        FileUploaderId = fileUploaderId;
    }

    public List<StaffNameId> getSysPersonId() {
        return SysPersonId;
    }

    public void setSysPersonId(List<StaffNameId> sysPersonId) {
        SysPersonId = sysPersonId;
    }

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }


    @Override
    public String toString() {
        return "CEmailSendReqs{" +
                "Email='" + Email + '\'' +
                ", Theme='" + Theme + '\'' +
                ", SysPersonId=" + SysPersonId +
                ", FileUploaderId=" + FileUploaderId +
                '}';
    }
}
