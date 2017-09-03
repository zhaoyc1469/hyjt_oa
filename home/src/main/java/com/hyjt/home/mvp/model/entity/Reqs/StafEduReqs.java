package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/16  16:43
 * @desc ${TODO}
 */
public class StafEduReqs {
    /**
     * School : 辽宁石油化工大学
     * Date : 2017-07-01
     * Major : 机械设计制造及自动化
     * Degree : 2012041114395478107959d49b38c48
     */

    private String School;
    private String Date;
    private String Major;
    private String Degree;

    public String getSchool() {
        return School;
    }

    public void setSchool(String School) {
        this.School = School;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }

    @Override
    public String toString() {
        return "StafEduReqs{" +
                "School='" + School + '\'' +
                ", Date='" + Date + '\'' +
                ", Major='" + Major + '\'' +
                ", Degree='" + Degree + '\'' +
                '}';
    }
}
