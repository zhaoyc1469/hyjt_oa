package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/7/20  10:39
 * @desc ${TODO}
 */

public class MeetingMsgReqs {

    private String CM_name;
    private String CM_person;
    private String CM_promoter;
    private String CM_starttime;
    private String CM_room;
    private String SysPerson;
    private String CM_department;
    private String CM_content;
    private String FileUploader;
    private String CM_State;
    private String rowsCount;
    private String Rows;

    public String getCM_name() {
        return CM_name;
    }

    public void setCM_name(String CM_name) {
        this.CM_name = CM_name;
    }

    public String getCM_person() {
        return CM_person;
    }

    public void setCM_person(String CM_person) {
        this.CM_person = CM_person;
    }

    public String getCM_promoter() {
        return CM_promoter;
    }

    public void setCM_promoter(String CM_promoter) {
        this.CM_promoter = CM_promoter;
    }

    public String getCM_starttime() {
        return CM_starttime;
    }

    public void setCM_starttime(String CM_starttime) {
        this.CM_starttime = CM_starttime;
    }

    public String getCM_room() {
        return CM_room;
    }

    public void setCM_room(String CM_room) {
        this.CM_room = CM_room;
    }

    public String getSysPerson() {
        return SysPerson;
    }

    public void setSysPerson(String sysPerson) {
        SysPerson = sysPerson;
    }

    public String getCM_department() {
        return CM_department;
    }

    public void setCM_department(String CM_department) {
        this.CM_department = CM_department;
    }

    public String getCM_content() {
        return CM_content;
    }

    public void setCM_content(String CM_content) {
        this.CM_content = CM_content;
    }

    public String getFileUploader() {
        return FileUploader;
    }

    public void setFileUploader(String fileUploader) {
        FileUploader = fileUploader;
    }

    public String getCM_State() {
        return CM_State;
    }

    public void setCM_State(String CM_State) {
        this.CM_State = CM_State;
    }

    public String getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(String rowsCount) {
        this.rowsCount = rowsCount;
    }

    public String getRows() {
        return Rows;
    }

    public void setRows(String rows) {
        Rows = rows;
    }

    @Override
    public String toString() {
        return "MeetingMsgReqs{" +
                "CM_name='" + CM_name + '\'' +
                ", CM_person='" + CM_person + '\'' +
                ", CM_promoter='" + CM_promoter + '\'' +
                ", CM_starttime='" + CM_starttime + '\'' +
                ", CM_room='" + CM_room + '\'' +
                ", SysPerson='" + SysPerson + '\'' +
                ", CM_department='" + CM_department + '\'' +
                ", CM_content='" + CM_content + '\'' +
                ", FileUploader='" + FileUploader + '\'' +
                ", CM_State='" + CM_State + '\'' +
                ", rowsCount='" + rowsCount + '\'' +
                ", Rows='" + Rows + '\'' +
                '}';
    }
}
