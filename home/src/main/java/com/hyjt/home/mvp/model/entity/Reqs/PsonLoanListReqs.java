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

    public PsonLoanListReqs(String page, String rows, String cwPnum, String start, String end) {
        Page = page;
        Rows = rows;
        CwPnum = cwPnum;
        Start = start;
        End = end;
    }

    public PsonLoanListReqs() {
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
