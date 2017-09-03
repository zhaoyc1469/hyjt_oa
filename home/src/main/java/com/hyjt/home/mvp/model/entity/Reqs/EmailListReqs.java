package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/29  14:32
 * @desc ${TODO}
 */

public class EmailListReqs {

    private String Page;
    private String Rows;
    private String Type;

    public EmailListReqs(String page, String rows, String type) {
        Type = type;
        Rows = rows;
        Page = page;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "EmailListReqs{" +
                "Page='" + Page + '\'' +
                ", Rows='" + Rows + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
