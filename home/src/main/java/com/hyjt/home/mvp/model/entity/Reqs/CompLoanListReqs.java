package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * Created by Administrator on 2017/10/10.
 */

public class CompLoanListReqs {

    private String Page;
    private String Rows;
    private String Start;
    private String End;
    private String Type;
    private String Mode;
    private String CwCnum;
    private String CwCpersonal;
    private String CwCcompany;
    private String CwCdepartment;

    public CompLoanListReqs(String page, String rows, String CwCnum, String start, String end, String type, String mode, String CwCpersonal, String CwCcompany, String CwCdepartment) {
        this.Page = page;
        this.Rows = rows;
        this.CwCnum = CwCnum;
        this.Start = start;
        this.End = end;
        this.Type = type;
        this.Mode = mode;
        this.CwCpersonal = CwCpersonal;
        this.CwCcompany = CwCcompany;
        this.CwCdepartment = CwCdepartment;
    }

    public CompLoanListReqs() {
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

    public String getCwPpersonal() {
        return CwCpersonal;
    }

    public void setCwPpersonal(String cwPpersonal) {
        CwCpersonal = cwPpersonal;
    }

    public String getCwPcompany() {
        return CwCcompany;
    }

    public void setCwPcompany(String cwPcompany) {
        CwCcompany = cwPcompany;
    }

    public String getCwPdepartment() {
        return CwCdepartment;
    }

    public void setCwPdepartment(String cwPdepartment) {
        CwCdepartment = cwPdepartment;
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

    public String getCwPnum() {
        return CwCnum;
    }

    public void setCwPnum(String cwPnum) {
        CwCnum = cwPnum;
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
        return "CompLoanListReqs{" +
                "Page='" + Page + '\'' +
                ", Rows='" + Rows + '\'' +
                ", Start='" + Start + '\'' +
                ", End='" + End + '\'' +
                ", Type='" + Type + '\'' +
                ", Mode='" + Mode + '\'' +
                ", CwCnum='" + CwCnum + '\'' +
                ", CwCpersonal='" + CwCpersonal + '\'' +
                ", CwCcompany='" + CwCcompany + '\'' +
                ", CwCdepartment='" + CwCdepartment + '\'' +
                '}';
    }
}
