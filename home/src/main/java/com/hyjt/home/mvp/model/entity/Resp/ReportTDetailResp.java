package com.hyjt.home.mvp.model.entity.Resp;

/**
 * @author 赵宇驰
 * @time 2017/9/4  16:30
 * @desc ${TODO}
 */

public class ReportTDetailResp {


    /**
     * CreatePerson : 李大鹏
     * State : 已审核
     * Boss : 胡成良
     * BossId : 2012061310023400000009613dfcf86
     * CreateTime : 2017/5/27 14:31:11
     * PID : 20170527-0001
     * Content : 爱对方水电费水电费
     * Mind : 似的发射得分
     * BossMind : lskdjflskjdflkjs
     * BossTime : 2017/5/27 14:31:49
     * CreatePersonSign : /Upload/2017021410336078.jpg
     * BossSign : /Upload/20170509033410580.jpg
     */

    private String Id;
    private String CreatePerson;
    private String State;
    private String Boss;
    private String BossId;
    private String CreateTime;
    private String PID;
    private String Content;
    private String Mind;
    private String BossMind;
    private String BossTime;
    private String CreatePersonSign;
    private String BossSign;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCreatePerson() {
        return CreatePerson;
    }

    public void setCreatePerson(String CreatePerson) {
        this.CreatePerson = CreatePerson;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
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

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getMind() {
        return Mind;
    }

    public void setMind(String Mind) {
        this.Mind = Mind;
    }

    public String getBossMind() {
        return BossMind;
    }

    public void setBossMind(String BossMind) {
        this.BossMind = BossMind;
    }

    public String getBossTime() {
        return BossTime;
    }

    public void setBossTime(String BossTime) {
        this.BossTime = BossTime;
    }

    public String getCreatePersonSign() {
        return CreatePersonSign;
    }

    public void setCreatePersonSign(String CreatePersonSign) {
        this.CreatePersonSign = CreatePersonSign;
    }

    public String getBossSign() {
        return BossSign;
    }

    public void setBossSign(String BossSign) {
        this.BossSign = BossSign;
    }


    @Override
    public String toString() {
        return "ReportTDetailResp{" +
                "Id='" + Id + '\'' +
                ", CreatePerson='" + CreatePerson + '\'' +
                ", State='" + State + '\'' +
                ", Boss='" + Boss + '\'' +
                ", BossId='" + BossId + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", PID='" + PID + '\'' +
                ", Content='" + Content + '\'' +
                ", Mind='" + Mind + '\'' +
                ", BossMind='" + BossMind + '\'' +
                ", BossTime='" + BossTime + '\'' +
                ", CreatePersonSign='" + CreatePersonSign + '\'' +
                ", BossSign='" + BossSign + '\'' +
                '}';
    }
}
