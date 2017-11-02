package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ApplyExpenseListReqs {

    private String Page;
    private String Rows;
    private String Start;
    private String End;
    private String Type;
    private String Mode;
    private String CwEpersonal;
    private String CwEcompany;
    private String CwEdepartment;
    private String CwEnum;

    public ApplyExpenseListReqs(String page, String rows, String start, String end, String type, String mode, String cwEpersonal, String cwEcompany, String cwEdepartment, String cwEnum) {
        Page = page;
        Rows = rows;
        Start = start;
        End = end;
        Type = type;
        Mode = mode;
        CwEpersonal = cwEpersonal;
        CwEcompany = cwEcompany;
        CwEdepartment = cwEdepartment;
        CwEnum = cwEnum;
    }

    public ApplyExpenseListReqs() {
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getCwEpersonal() {
        return CwEpersonal;
    }

    public void setCwEpersonal(String cwEpersonal) {
        CwEpersonal = cwEpersonal;
    }

    public String getCwEcompany() {
        return CwEcompany;
    }

    public void setCwEcompany(String cwEcompany) {
        CwEcompany = cwEcompany;
    }

    public String getCwEdepartment() {
        return CwEdepartment;
    }

    public void setCwEdepartment(String cwEdepartment) {
        CwEdepartment = cwEdepartment;
    }

    public String getCwEnum() {
        return CwEnum;
    }

    public void setCwEnum(String cwEnum) {
        CwEnum = cwEnum;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        Page = page;
    }

    public String getRows() {
        return Rows;
    }

    public void setRows(String rows) {
        Rows = rows;
    }


    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    @Override
    public String toString() {
        return "ApplyExpenseListReqs{" +
                "Page='" + Page + '\'' +
                ", Rows='" + Rows + '\'' +
                ", Start='" + Start + '\'' +
                ", End='" + End + '\'' +
                ", Type='" + Type + '\'' +
                ", Mode='" + Mode + '\'' +
                ", CwEpersonal='" + CwEpersonal + '\'' +
                ", CwEcompany='" + CwEcompany + '\'' +
                ", CwEdepartment='" + CwEdepartment + '\'' +
                ", CwEnum='" + CwEnum + '\'' +
                '}';
    }
}
