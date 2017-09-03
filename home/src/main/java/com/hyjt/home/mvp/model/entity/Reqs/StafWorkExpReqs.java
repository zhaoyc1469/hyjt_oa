package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/16  16:50
 * @desc ${TODO}
 */
public class StafWorkExpReqs {

    /**
     * Company : 辽宁环宇
     * Postion : 技术员
     * WorkStart : 2017-08-17
     * WorkEnd : 2017-08-30
     */

    private String Company;
    private String Postion;
    private String WorkStart;
    private String WorkEnd;
    private String Work_degree;

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getPostion() {
        return Postion;
    }

    public void setPostion(String Postion) {
        this.Postion = Postion;
    }

    public String getWorkStart() {
        return WorkStart;
    }

    public void setWorkStart(String WorkStart) {
        this.WorkStart = WorkStart;
    }

    public String getWorkEnd() {
        return WorkEnd;
    }

    public void setWorkEnd(String WorkEnd) {
        this.WorkEnd = WorkEnd;
    }

    public String getWork_degree() {
        return Work_degree;
    }

    public void setWork_degree(String work_degree) {
        Work_degree = work_degree;
    }

    @Override
    public String toString() {
        return "StafWorkExpReqs{" +
                "Company='" + Company + '\'' +
                ", Postion='" + Postion + '\'' +
                ", WorkStart='" + WorkStart + '\'' +
                ", WorkEnd='" + WorkEnd + '\'' +
                '}';
    }
}
