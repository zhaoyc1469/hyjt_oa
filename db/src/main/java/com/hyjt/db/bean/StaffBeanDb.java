package com.hyjt.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class StaffBeanDb {

    @Id
    private String StaffId;

    private String StaffName;
    private String ModuleList;
    public String getModuleList() {
        return this.ModuleList;
    }
    public void setModuleList(String ModuleList) {
        this.ModuleList = ModuleList;
    }
    public String getStaffName() {
        return this.StaffName;
    }
    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }
    public String getStaffId() {
        return this.StaffId;
    }
    public void setStaffId(String StaffId) {
        this.StaffId = StaffId;
    }
    @Generated(hash = 1610662357)
    public StaffBeanDb(String StaffId, String StaffName, String ModuleList) {
        this.StaffId = StaffId;
        this.StaffName = StaffName;
        this.ModuleList = ModuleList;
    }
    @Generated(hash = 1618965316)
    public StaffBeanDb() {
    }
}
