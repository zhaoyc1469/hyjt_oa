package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * Created by Administrator on 2017/9/8.
 */

public class LUReqsListReqs {


    private String Page;
    private String Rows;
    private String Type;

    public LUReqsListReqs(){

    }


    public LUReqsListReqs(String page, String rows, String type) {
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
        return "LUReqsListReqs{" +
                "Page='" + Page + '\'' +
                ", Rows='" + Rows + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
