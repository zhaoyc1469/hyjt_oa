package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/24  9:41
 * @desc ${TODO}
 */

public class BaseListReqs {

    private String Page;
    private String Rows;

    public BaseListReqs(String page, String rows) {
        Page = page;
        Rows = rows;
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


    @Override
    public String toString() {
        return "BaseListReqs{" +
                "Page='" + Page + '\'' +
                ", Rows='" + Rows + '\'' +
                '}';
    }
}
