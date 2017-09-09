package com.hyjt.home.mvp.model.entity.Resp;

/**
 * Created by Administrator on 2017/9/7.
 */

public class CIdeaDetailResp {


    /**
     * Id : 2016092209585045246270f50f9df66
     * Title : 献计献策test
     * Number : 20160922-0001
     * ReportPerson : 李大鹏
     * ReportPersonId : 20120426141601375528685d3e0eb2b
     * Boss : 胡成良
     * BossId : 2012061310023400000009613dfcf86
     * ReportContent : xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * ReportTime : 2016/9/22 9:57:00
     * ReplyContent : xxxxxxxxxxx
     * ReplyTime : 2016/9/22 10:05:00
     * Agree : 不同意
     * State : 已批示
     */

    private String Id;
    private String Title;
    private String Number;
    private String ReportPerson;
    private String ReportPersonId;
    private String Boss;
    private String BossId;
    private String ReportContent;
    private String ReportTime;
    private String ReplyContent;
    private String ReplyTime;
    private String Agree;
    private String State;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getReportPerson() {
        return ReportPerson;
    }

    public void setReportPerson(String ReportPerson) {
        this.ReportPerson = ReportPerson;
    }

    public String getReportPersonId() {
        return ReportPersonId;
    }

    public void setReportPersonId(String ReportPersonId) {
        this.ReportPersonId = ReportPersonId;
    }

    public String getBoss() {
        return Boss;
    }

    public void setBoss(String Boss) {
        this.Boss = Boss;
    }

    public String getBossId() {
        return BossId;
    }

    public void setBossId(String BossId) {
        this.BossId = BossId;
    }

    public String getReportContent() {
        return ReportContent;
    }

    public void setReportContent(String ReportContent) {
        this.ReportContent = ReportContent;
    }

    public String getReportTime() {
        return ReportTime;
    }

    public void setReportTime(String ReportTime) {
        this.ReportTime = ReportTime;
    }

    public String getReplyContent() {
        return ReplyContent;
    }

    public void setReplyContent(String ReplyContent) {
        this.ReplyContent = ReplyContent;
    }

    public String getReplyTime() {
        return ReplyTime;
    }

    public void setReplyTime(String ReplyTime) {
        this.ReplyTime = ReplyTime;
    }

    public String getAgree() {
        return Agree;
    }

    public void setAgree(String Agree) {
        this.Agree = Agree;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }


    @Override
    public String toString() {
        return "CIdeaDetailResp{" +
                "Id='" + Id + '\'' +
                ", Title='" + Title + '\'' +
                ", Number='" + Number + '\'' +
                ", ReportPerson='" + ReportPerson + '\'' +
                ", ReportPersonId='" + ReportPersonId + '\'' +
                ", Boss='" + Boss + '\'' +
                ", BossId='" + BossId + '\'' +
                ", ReportContent='" + ReportContent + '\'' +
                ", ReportTime='" + ReportTime + '\'' +
                ", ReplyContent='" + ReplyContent + '\'' +
                ", ReplyTime='" + ReplyTime + '\'' +
                ", Agree='" + Agree + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}
