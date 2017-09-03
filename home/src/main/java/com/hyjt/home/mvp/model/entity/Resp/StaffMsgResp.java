package com.hyjt.home.mvp.model.entity.Resp;

import com.hyjt.home.mvp.model.entity.Reqs.StafEduReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StafWorkExpReqs;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/15  21:58
 * @desc ${TODO}
 */

public class StaffMsgResp {

    /**
     * Id : 20170328081716366556360b7a83bf1
     * Department : 信息技术部
     * DepartmentOld : 7bd0306f-0edd-4daa-8693-29a62230c635
     * Name : 孙燚龙
     * E_id : 1703280817
     * E_sex : 男
     * E_birth : 1995-03-02
     * E_nation : 汉族
     * E_relation : 共青团员
     * E_marry : 未婚
     * E_family : 辽宁省抚顺市
     * E_ffamily : 山东日照
     * E_address : 辽宁省抚顺市顺城区延吉路16号楼5单元402号
     * E_card : 210411199503022932
     * E_langu : 无
     * E_compu : 计算机二级
     * State : 本科
     * E_post : 实习程序员
     * E_postion : 技术员
     * E_propost : 无
     * E_workdate : 2017-03-28
     * E_state : 实习
     * E_stay : 否
     * E_doc : 辽宁石油化工大学
     * E_singtime :
     * E_maturetime :
     * E_insure :
     * E_insureL : null
     * MobilePhoneNumber : 13204135846
     * E_innerPhone : null
     * PhoneNumber : null
     * E_QQ : 306415449
     * EmailAddress : 306415449@qq.com
     * Remark : null
     * qzy : null
     * zzrange : 负责程序编写
     * familyRelation : null
     * lefttime :
     * EduPack : [{"School":"辽宁石油化工大学","Date":"2017-07-01","Major":"机械设计制造及自动化","Degree":"2012041114395478107959d49b38c48"}]
     * WorkPack : [{"Company":"辽宁环宇","Postion":"技术员","WorkStart":"2017-08-17","WorkEnd":"2017-08-30"}]
     */

    private String Id;
    private String Department;
    private String DepartmentOld;
    private String Name;
    private String E_id;
    private String E_sex;
    private String E_birth;
    private String E_nation;
    private String E_relation;
    private String E_marry;
    private String E_family;
    private String E_ffamily;
    private String E_address;
    private String E_card;
    private String E_langu;
    private String E_compu;
    private String State;
    private String E_post;
    private String E_postion;
    private String E_propost;
    private String E_workdate;
    private String E_state;
    private String E_stay;
    private String E_doc;
    private String E_singtime;
    private String E_maturetime;
    private String E_insure;
    private String E_insureL;
    private String MobilePhoneNumber;
    private String E_innerPhone;
    private String PhoneNumber;
    private String E_QQ;
    private String EmailAddress;
    private String Remark;
    private String qzy;
    private String zzrange;
    private String familyRelation;
    private String lefttime;
    private List<StafEduReqs> EduPack;
//    private List<StafWorkExpReqs> WorkPack;
    private List<StafWorkExpReqs> WorkPack;
    private List<FilePackBean> FilePack;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getDepartmentOld() {
        return DepartmentOld;
    }

    public void setDepartmentOld(String departmentOld) {
        DepartmentOld = departmentOld;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getE_id() {
        return E_id;
    }

    public void setE_id(String E_id) {
        this.E_id = E_id;
    }

    public String getE_sex() {
        return E_sex;
    }

    public void setE_sex(String E_sex) {
        this.E_sex = E_sex;
    }

    public String getE_birth() {
        return E_birth;
    }

    public void setE_birth(String E_birth) {
        this.E_birth = E_birth;
    }

    public String getE_nation() {
        return E_nation;
    }

    public void setE_nation(String E_nation) {
        this.E_nation = E_nation;
    }

    public String getE_relation() {
        return E_relation;
    }

    public void setE_relation(String E_relation) {
        this.E_relation = E_relation;
    }

    public String getE_marry() {
        return E_marry;
    }

    public void setE_marry(String E_marry) {
        this.E_marry = E_marry;
    }

    public String getE_family() {
        return E_family;
    }

    public void setE_family(String E_family) {
        this.E_family = E_family;
    }

    public String getE_ffamily() {
        return E_ffamily;
    }

    public void setE_ffamily(String E_ffamily) {
        this.E_ffamily = E_ffamily;
    }

    public String getE_address() {
        return E_address;
    }

    public void setE_address(String E_address) {
        this.E_address = E_address;
    }

    public String getE_card() {
        return E_card;
    }

    public void setE_card(String E_card) {
        this.E_card = E_card;
    }

    public String getE_langu() {
        return E_langu;
    }

    public void setE_langu(String E_langu) {
        this.E_langu = E_langu;
    }

    public String getE_compu() {
        return E_compu;
    }

    public void setE_compu(String E_compu) {
        this.E_compu = E_compu;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getE_post() {
        return E_post;
    }

    public void setE_post(String E_post) {
        this.E_post = E_post;
    }

    public String getE_postion() {
        return E_postion;
    }

    public void setE_postion(String E_postion) {
        this.E_postion = E_postion;
    }

    public String getE_propost() {
        return E_propost;
    }

    public void setE_propost(String E_propost) {
        this.E_propost = E_propost;
    }

    public String getE_workdate() {
        return E_workdate;
    }

    public void setE_workdate(String E_workdate) {
        this.E_workdate = E_workdate;
    }

    public String getE_state() {
        return E_state;
    }

    public void setE_state(String E_state) {
        this.E_state = E_state;
    }

    public String getE_stay() {
        return E_stay;
    }

    public void setE_stay(String E_stay) {
        this.E_stay = E_stay;
    }

    public String getE_doc() {
        return E_doc;
    }

    public void setE_doc(String E_doc) {
        this.E_doc = E_doc;
    }

    public String getE_singtime() {
        return E_singtime;
    }

    public void setE_singtime(String E_singtime) {
        this.E_singtime = E_singtime;
    }

    public String getE_maturetime() {
        return E_maturetime;
    }

    public void setE_maturetime(String E_maturetime) {
        this.E_maturetime = E_maturetime;
    }

    public String getE_insure() {
        return E_insure;
    }

    public void setE_insure(String E_insure) {
        this.E_insure = E_insure;
    }

    public String getE_insureL() {
        return E_insureL;
    }

    public void setE_insureL(String E_insureL) {
        this.E_insureL = E_insureL;
    }

    public String getMobilePhoneNumber() {
        return MobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String MobilePhoneNumber) {
        this.MobilePhoneNumber = MobilePhoneNumber;
    }

    public String getE_innerPhone() {
        return E_innerPhone;
    }

    public void setE_innerPhone(String E_innerPhone) {
        this.E_innerPhone = E_innerPhone;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getE_QQ() {
        return E_QQ;
    }

    public void setE_QQ(String E_QQ) {
        this.E_QQ = E_QQ;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getQzy() {
        return qzy;
    }

    public void setQzy(String qzy) {
        this.qzy = qzy;
    }

    public String getZzrange() {
        return zzrange;
    }

    public void setZzrange(String zzrange) {
        this.zzrange = zzrange;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getLefttime() {
        return lefttime;
    }

    public void setLefttime(String lefttime) {
        this.lefttime = lefttime;
    }

    public List<StafEduReqs> getEduPack() {
        return EduPack;
    }

    public void setEduPack(List<StafEduReqs> EduPack) {
        this.EduPack = EduPack;
    }

    public List<StafWorkExpReqs> getWorkPack() {
        return WorkPack;
    }

    public void setWorkPack(List<StafWorkExpReqs> WorkExpPack) {
        this.WorkPack = WorkExpPack;
    }

    public List<FilePackBean> getFilePack() {
        return FilePack;
    }

    public void setFilePack(List<FilePackBean> FilePack) {
        this.FilePack = FilePack;
    }

    public static class FilePackBean {

        /**
         * FileName : 照片
         * FilePath : /Upload/20170302092952151.png
         */

        private String FileName;
        private String FilePath;
        private String IsPhoto ;

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getIsPhoto() {
            return IsPhoto;
        }

        public void setIsPhoto(String isPhoto) {
            IsPhoto = isPhoto;
        }

        @Override
        public String toString() {
            return "FilePackBean{" +
                    "FileName='" + FileName + '\'' +
                    ", FilePath='" + FilePath + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "StaffMsgResp{" +
                "Id='" + Id + '\'' +
                ", Department='" + Department + '\'' +
                ", Name='" + Name + '\'' +
                ", E_id='" + E_id + '\'' +
                ", E_sex='" + E_sex + '\'' +
                ", E_birth='" + E_birth + '\'' +
                ", E_nation='" + E_nation + '\'' +
                ", E_relation='" + E_relation + '\'' +
                ", E_marry='" + E_marry + '\'' +
                ", E_family='" + E_family + '\'' +
                ", E_ffamily='" + E_ffamily + '\'' +
                ", E_address='" + E_address + '\'' +
                ", E_card='" + E_card + '\'' +
                ", E_langu='" + E_langu + '\'' +
                ", E_compu='" + E_compu + '\'' +
                ", State='" + State + '\'' +
                ", E_post='" + E_post + '\'' +
                ", E_postion='" + E_postion + '\'' +
                ", E_propost='" + E_propost + '\'' +
                ", E_workdate='" + E_workdate + '\'' +
                ", E_state='" + E_state + '\'' +
                ", E_stay='" + E_stay + '\'' +
                ", E_doc='" + E_doc + '\'' +
                ", E_singtime='" + E_singtime + '\'' +
                ", E_maturetime='" + E_maturetime + '\'' +
                ", E_insure='" + E_insure + '\'' +
                ", E_insureL='" + E_insureL + '\'' +
                ", MobilePhoneNumber='" + MobilePhoneNumber + '\'' +
                ", E_innerPhone='" + E_innerPhone + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", E_QQ='" + E_QQ + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", Remark='" + Remark + '\'' +
                ", qzy='" + qzy + '\'' +
                ", zzrange='" + zzrange + '\'' +
                ", familyRelation='" + familyRelation + '\'' +
                ", lefttime='" + lefttime + '\'' +
                ", EduPack=" + EduPack +
                ", WorkPack=" + WorkPack +
                ", FilePack=" + FilePack.toString() +
                '}';
    }
}
