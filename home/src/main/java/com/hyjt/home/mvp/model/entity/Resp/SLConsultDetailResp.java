package com.hyjt.home.mvp.model.entity.Resp;

/**
 * @author 赵宇驰
 * @time 2017/9/5  10:48
 * @desc ${TODO}
 */

public class SLConsultDetailResp {


    /**
     * Id : 201709051327347434471017d05bbdd
     * CreatePerson : 孙燚龙
     * State : 待回复
     * Worker : 李大鹏
     * WorkerId : 20120426141601375528685d3e0eb2b
     * CreateTime : 2017/9/5 13:27:34
     * PID : 20170905-0004
     * Content : 内容
     * Mind : 想法
     * WorkerMind : null
     * WorkerTime : 0001/1/1 0:00:00
     * CreatePersonSign : /Upload/20170511094945098.jpg
     * WorkerSign :
     */

    private String Id;
    private String CreatePerson;
    private String State;
    private String Worker;
    private String WorkerId;
    private String CreateTime;
    private String PID;
    private String Content;
    private String Mind;
    private String WorkerMind;
    private String WorkerTime;
    private String CreatePersonSign;
    private String WorkerSign;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public String getWorker() {
        return Worker;
    }

    public void setWorker(String Worker) {
        this.Worker = Worker;
    }

    public String getWorkerId() {
        return WorkerId;
    }

    public void setWorkerId(String WorkerId) {
        this.WorkerId = WorkerId;
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

    public String getWorkerMind() {
        return WorkerMind;
    }

    public void setWorkerMind(String WorkerMind) {
        this.WorkerMind = WorkerMind;
    }

    public String getWorkerTime() {
        return WorkerTime;
    }

    public void setWorkerTime(String WorkerTime) {
        this.WorkerTime = WorkerTime;
    }

    public String getCreatePersonSign() {
        return CreatePersonSign;
    }

    public void setCreatePersonSign(String CreatePersonSign) {
        this.CreatePersonSign = CreatePersonSign;
    }

    public String getWorkerSign() {
        return WorkerSign;
    }

    public void setWorkerSign(String WorkerSign) {
        this.WorkerSign = WorkerSign;
    }

    @Override
    public String toString() {
        return "SLConsultDetailResp{" +
                "Id='" + Id + '\'' +
                ", CreatePerson='" + CreatePerson + '\'' +
                ", State='" + State + '\'' +
                ", Worker='" + Worker + '\'' +
                ", WorkerId='" + WorkerId + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", PID='" + PID + '\'' +
                ", Content='" + Content + '\'' +
                ", Mind='" + Mind + '\'' +
                ", WorkerMind=" + WorkerMind +
                ", WorkerTime='" + WorkerTime + '\'' +
                ", CreatePersonSign='" + CreatePersonSign + '\'' +
                ", WorkerSign='" + WorkerSign + '\'' +
                '}';
    }
}
