package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/7/19  13:01
 * @desc ${TODO}
 */

public class MtQuestionReqs {

    private String ExecutiveDepartment;
    private String Executor;
    private String ExecutorStr;
    private String FinishTime;
    private String Record;
    private String Whether;
    private String JiejueTime;
    private String RecordFangfa;
    private String DuBanPerson;
    private String DuBanTime;
    private String jiejueState;
    private String dubanZhishi;

    public MtQuestionReqs() {
    }

    public MtQuestionReqs(String executiveDepartment, String executor, String finishTime, String record, String whether, String jiejueTime, String recordFangfa, String duBanPerson, String duBanTime, String jiejueState) {
        ExecutiveDepartment = executiveDepartment;
        Executor = executor;
        FinishTime = finishTime;
        Record = record;
        Whether = whether;
        JiejueTime = jiejueTime;
        RecordFangfa = recordFangfa;
        DuBanPerson = duBanPerson;
        DuBanTime = duBanTime;
        this.jiejueState = jiejueState;
    }

//    private StaffNameIdKey executor;
//    private StaffNameIdKey supervisor;

//    public StaffNameIdKey getExecutor() {
//        return executor;
//    }
//
//    public void setExecutor(StaffNameIdKey executor) {
//        this.executor = executor;
//    }
//
//    public StaffNameIdKey getSupervisor() {
//        return supervisor;
//    }
//
//    public void setSupervisor(StaffNameIdKey supervisor) {
//        this.supervisor = supervisor;
//    }


    public String getExecutiveDepartment() {
        return ExecutiveDepartment;
    }

    public void setExecutiveDepartment(String executiveDepartment) {
        ExecutiveDepartment = executiveDepartment;
    }

    public String getExecutor() {
        return Executor;
    }

    public void setExecutor(String executor) {
        Executor = executor;
    }

    public String getExecutorStr() {
        return ExecutorStr;
    }

    public void setExecutorStr(String executorStr) {
        ExecutorStr = executorStr;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String record) {
        Record = record;
    }

    public String getWhether() {
        return Whether;
    }

    public void setWhether(String whether) {
        Whether = whether;
    }

    public String getJiejueTime() {
        return JiejueTime;
    }

    public void setJiejueTime(String jiejueTime) {
        JiejueTime = jiejueTime;
    }

    public String getRecordFangfa() {
        return RecordFangfa;
    }

    public void setRecordFangfa(String recordFangfa) {
        RecordFangfa = recordFangfa;
    }

    public String getDuBanPerson() {
        return DuBanPerson;
    }

    public void setDuBanPerson(String duBanPerson) {
        DuBanPerson = duBanPerson;
    }

    public String getDuBanTime() {
        return DuBanTime;
    }

    public void setDuBanTime(String duBanTime) {
        DuBanTime = duBanTime;
    }

    public String getJiejueState() {
        return jiejueState;
    }

    public void setJiejueState(String jiejueState) {
        this.jiejueState = jiejueState;
    }

    public String getDubanZhishi() {
        return dubanZhishi;
    }

    public void setDubanZhishi(String dubanZhishi) {
        this.dubanZhishi = dubanZhishi;
    }
}
