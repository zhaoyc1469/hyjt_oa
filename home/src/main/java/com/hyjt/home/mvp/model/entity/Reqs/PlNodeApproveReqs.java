package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * Created by Administrator on 2017/10/19.
 */

public class PlNodeApproveReqs {

    private String Id;
    private String NodeMemo;
    private String NodeMemotext;
    private String CwBname;
    private String CwBnum;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNodeMemo() {
        return NodeMemo;
    }

    public void setNodeMemo(String nodeMemo) {
        NodeMemo = nodeMemo;
    }

    public String getNodeMemotext() {
        return NodeMemotext;
    }

    public void setNodeMemotext(String nodeMemotext) {
        NodeMemotext = nodeMemotext;
    }

    public String getCwBname() {
        return CwBname;
    }

    public void setCwBname(String cwBname) {
        CwBname = cwBname;
    }

    public String getCwBnum() {
        return CwBnum;
    }

    public void setCwBnum(String cwBnum) {
        CwBnum = cwBnum;
    }

    @Override
    public String toString() {
        return "PlNodeApproveReqs{" +
                "Id='" + Id + '\'' +
                ", NodeMemo='" + NodeMemo + '\'' +
                ", NodeMemotext='" + NodeMemotext + '\'' +
                ", CwBname='" + CwBname + '\'' +
                ", CwBnum='" + CwBnum + '\'' +
                '}';
    }
}
