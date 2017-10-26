package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * Created by Administrator on 2017/10/26.
 */

public class CompLoanContractReqs {


    private String Page;
    private String Rows;
    private String Type;

    public CompLoanContractReqs() {
    }

    public CompLoanContractReqs(String page, String rows, String type) {
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
}
