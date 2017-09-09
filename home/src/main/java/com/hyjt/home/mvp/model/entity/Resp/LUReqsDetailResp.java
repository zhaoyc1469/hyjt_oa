package com.hyjt.home.mvp.model.entity.Resp;

/**
 * Created by Administrator on 2017/9/8.
 */

public class LUReqsDetailResp {

    /**
     * Id : 2017090912093916036335f34ecc444
     * Number : 20170909-0001
     * AppealPerson : 孙燚龙
     * AppealPersonId : 20170328081716366556360b7a83bf1
     * Boss : 孙燚龙
     * AppealTime : 2017/9/9 12:09:39
     * ReplyContent :
     * ReplyTime : 0001/1/1 0:00:00
     * State : 待批示
     */

    private String Title;
    private String AppealContent;
    private String BossId;
    private String Id;
    private String Number;
    private String AppealPerson;
    private String AppealPersonId;
    private String Boss;
    private String AppealTime;
    private String ReplyContent;
    private String Agree;
    private String ReplyTime;
    private String State;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAppealContent() {
        return AppealContent;
    }

    public void setAppealContent(String appealContent) {
        AppealContent = appealContent;
    }

    public String getBossId() {
        return BossId;
    }

    public void setBossId(String bossId) {
        BossId = bossId;
    }

    public String getAgree() {
        return Agree;
    }

    public void setAgree(String agree) {
        Agree = agree;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getAppealPerson() {
        return AppealPerson;
    }

    public void setAppealPerson(String AppealPerson) {
        this.AppealPerson = AppealPerson;
    }

    public String getAppealPersonId() {
        return AppealPersonId;
    }

    public void setAppealPersonId(String AppealPersonId) {
        this.AppealPersonId = AppealPersonId;
    }

    public String getBoss() {
        return Boss;
    }

    public void setBoss(String Boss) {
        this.Boss = Boss;
    }

    public String getAppealTime() {
        return AppealTime;
    }

    public void setAppealTime(String AppealTime) {
        this.AppealTime = AppealTime;
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

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    @Override
    public String toString() {
        return "LUReqsDetailResp{" +
                "Title='" + Title + '\'' +
                ", AppealContent='" + AppealContent + '\'' +
                ", BossId='" + BossId + '\'' +
                ", Id='" + Id + '\'' +
                ", Number='" + Number + '\'' +
                ", AppealPerson='" + AppealPerson + '\'' +
                ", AppealPersonId='" + AppealPersonId + '\'' +
                ", Boss='" + Boss + '\'' +
                ", AppealTime='" + AppealTime + '\'' +
                ", ReplyContent='" + ReplyContent + '\'' +
                ", ReplyTime='" + ReplyTime + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}
