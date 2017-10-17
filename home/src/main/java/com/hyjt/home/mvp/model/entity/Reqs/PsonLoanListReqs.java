package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * Created by Administrator on 2017/10/10.
 */

public class PsonLoanListReqs {

    private String Page;
    private String Rows;
    private String CwPnum;
    private String Start;
    private String End;
    private String Type;
    private String Mode;
    private String CwPpersonal;
    private String CwPcompany;
    private String CwPdepartment;

    public PsonLoanListReqs(String page, String rows, String cwPnum, String start, String end, String type, String mode, String cwPpersonal, String cwPcompany, String cwPdepartment) {
        Page = page;
        Rows = rows;
        CwPnum = cwPnum;
        Start = start;
        End = end;
        Type = type;
        Mode = mode;
        CwPpersonal = cwPpersonal;
        CwPcompany = cwPcompany;
        CwPdepartment = cwPdepartment;
    }

    public PsonLoanListReqs() {
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
        return CwPpersonal;
    }

    public void setCwPpersonal(String cwPpersonal) {
        CwPpersonal = cwPpersonal;
    }

    public String getCwPcompany() {
        return CwPcompany;
    }

    public void setCwPcompany(String cwPcompany) {
        CwPcompany = cwPcompany;
    }

    public String getCwPdepartment() {
        return CwPdepartment;
    }

    public void setCwPdepartment(String cwPdepartment) {
        CwPdepartment = cwPdepartment;
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
        return CwPnum;
    }

    public void setCwPnum(String cwPnum) {
        CwPnum = cwPnum;
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
}
