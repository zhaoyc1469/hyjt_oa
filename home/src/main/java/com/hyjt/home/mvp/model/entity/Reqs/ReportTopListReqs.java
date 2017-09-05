package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/9/4  14:34
 * @desc ${TODO}
 */

public class ReportTopListReqs {

    private String Page;
    private String Rows;
    private String Type;

    public ReportTopListReqs(){

    }


    public ReportTopListReqs(String page, String rows, String type) {
        Page = page;
        Rows = rows;
        Type = type;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        Page = page;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRows() {
        return Rows;
    }

    public void setRows(String rows) {
        Rows = rows;
    }

    @Override
    public String toString() {
        return "ReportTopListReqs{" +
                "Page='" + Page + '\'' +
                ", Rows='" + Rows + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
