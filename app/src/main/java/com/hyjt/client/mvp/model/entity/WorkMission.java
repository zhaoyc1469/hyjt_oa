package com.hyjt.client.mvp.model.entity;

/**
 * @author 赵宇驰
 * @time 2017/8/2  21:39
 * @desc ${TODO}
 */

public class WorkMission {


    /**
     * Record : 6
     * RecordL : 1
     * RecordDb : 0
     */

    private String Record;
    private String RecordL;
    private String RecordDb;
    private String SysLetter;

    public String getRecord() {
        return Record;
    }

    public void setRecord(String Record) {
        this.Record = Record;
    }

    public String getRecordL() {
        return RecordL;
    }

    public void setRecordL(String RecordL) {
        this.RecordL = RecordL;
    }

    public String getRecordDb() {
        return RecordDb;
    }

    public void setRecordDb(String RecordDb) {
        this.RecordDb = RecordDb;
    }

    public String getSysLetter() {
        return SysLetter;
    }

    public void setSysLetter(String sysLetter) {
        SysLetter = sysLetter;
    }
}
