package com.hyjt.home.mvp.model.entity.Resp;

import com.hyjt.home.mvp.model.entity.Reqs.MtQuestionReqs;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/17  19:49
 * @desc ${TODO}
 */

public class MeetingMsgResp {


    /**
     * Id : 201707201914086661725c93bec9093
     * CM_name : 全部填写
     * CM_Num : JT20170720-0014
     * CM_person : ^201206121650593750000aa99742da7&王长佳
     * CM_promoter : 孙燚龙
     * CM_starttime : 2017-7-20 15:45:00
     * CM_room : 办公室
     * SysPerson : 20120612164750609375054239964cb|蔡福源^2012061216480515625002337f1b3ea|饶强^201206121650155156250d6bd2a0189|崔权超^20120613095142093750075d83c0983|孙爱祥^201206130953155468750585562bc1d|常笛^2012071809424314062503ff7b3c5a9|杨梦尧^20150322133951867708607dc40a0bb|维护^
     * CM_department : 集团
     * CM_content : 巢湖水利项目完结
     * FileUploader : /up/201707201546178421725ba619e4acf165b242199f70e9a.jpg^165b242199f70e9a.jpg,/up/201707201546179357725a787ed7501165b242199f70e9a.jpg^165b242199f70e9a.jpg,/up/20170720154617951372503ea8aad04165b242199f70e9a.jpg^165b242199f70e9a.jpg,/up/201707201546179513725e2e7bb15dc165b242199f70e9a.jpg^165b242199f70e9a.jpg
     * FileUploaderOld : 201707201546178421725ba619e4acf|165b242199f70e9a.jpg^201707201546179357725a787ed7501|165b242199f70e9a.jpg^20170720154617951372503ea8aad04|165b242199f70e9a.jpg^201707201546179513725e2e7bb15dc|165b242199f70e9a.jpg
     * NeedDeal : [{"ExecutiveDepartment":"工程","ExecutorStr":"常馨月","Record":"v哈哈哈那你呢","FinishTime":"2017-7-20 15:46:00","Whether":"否","JiejueTime":"2017-7-20 15:46:00","RecordFangfa":"哥工行哈哈","DuBanPerson":"庞洁峻","DuBanTime":"2017-7-20 15:46:00","jiejueState":"未完成","dubanZhishi":""}]
     */

    private String Id;
    private String CM_name;
    private String CM_Num;
    private String CM_person;
    private String CM_promoter;
    private String CM_starttime;
    private String CM_room;
    private String SysPerson;
    private String CM_department;
    private String CM_content;
    private String FileUploader;
    private String FileUploaderOld;

    private List<MtQuestionReqs> NeedDeal;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCM_name() {
        return CM_name;
    }

    public void setCM_name(String CM_name) {
        this.CM_name = CM_name;
    }

    public String getCM_Num() {
        return CM_Num;
    }

    public void setCM_Num(String CM_Num) {
        this.CM_Num = CM_Num;
    }

    public String getCM_person() {
        return CM_person;
    }

    public void setCM_person(String CM_person) {
        this.CM_person = CM_person;
    }

    public String getCM_promoter() {
        return CM_promoter;
    }

    public void setCM_promoter(String CM_promoter) {
        this.CM_promoter = CM_promoter;
    }

    public String getCM_starttime() {
        return CM_starttime;
    }

    public void setCM_starttime(String CM_starttime) {
        this.CM_starttime = CM_starttime;
    }

    public String getCM_room() {
        return CM_room;
    }

    public void setCM_room(String CM_room) {
        this.CM_room = CM_room;
    }

    public String getSysPerson() {
        return SysPerson;
    }

    public void setSysPerson(String SysPerson) {
        this.SysPerson = SysPerson;
    }

    public String getCM_department() {
        return CM_department;
    }

    public void setCM_department(String CM_department) {
        this.CM_department = CM_department;
    }

    public String getCM_content() {
        return CM_content;
    }

    public void setCM_content(String CM_content) {
        this.CM_content = CM_content;
    }

    public String getFileUploader() {
        return FileUploader;
    }

    public void setFileUploader(String FileUploader) {
        this.FileUploader = FileUploader;
    }

    public String getFileUploaderOld() {
        return FileUploaderOld;
    }

    public void setFileUploaderOld(String FileUploaderOld) {
        this.FileUploaderOld = FileUploaderOld;
    }

    public List<MtQuestionReqs> getNeedDeal() {
        return NeedDeal;
    }

    public void setNeedDeal(List<MtQuestionReqs> needDeal) {
        NeedDeal = needDeal;
    }

}
