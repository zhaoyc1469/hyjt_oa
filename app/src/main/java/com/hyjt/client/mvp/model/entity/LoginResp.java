package com.hyjt.client.mvp.model.entity;

/**
 * @author 赵宇驰
 * @time 2017/7/11  21:15
 * @desc ${TODO}
 */

public class LoginResp {


    /**
     * Name : 孙燚龙
     * PersonName : 孙燚龙
     * Id : 20170328081716366556360b7a83bf1
     * SysDepartmentId : 20131205081700359375001120ed65c
     * Company : null
     * RoleMaterial : 2012041713135331326585dd4c1d320^
     * Part : 信息技术部
     * Job : 技术员
     * Pic : /Upload/20170328082136350.jpg
     * SessionId : uyhybzn15wjmjvofadrwf3kp
     */

    private String Name;
    private String PersonName;
    private String Id;
    private String SysDepartmentId;
    private Object Company;
    private String RoleMaterial;
    private String Part;
    private String Job;
    private String Pic;
    private String SessionId;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String PersonName) {
        this.PersonName = PersonName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSysDepartmentId() {
        return SysDepartmentId;
    }

    public void setSysDepartmentId(String SysDepartmentId) {
        this.SysDepartmentId = SysDepartmentId;
    }

    public Object getCompany() {
        return Company;
    }

    public void setCompany(Object Company) {
        this.Company = Company;
    }

    public String getRoleMaterial() {
        return RoleMaterial;
    }

    public void setRoleMaterial(String RoleMaterial) {
        this.RoleMaterial = RoleMaterial;
    }

    public String getPart() {
        return Part;
    }

    public void setPart(String Part) {
        this.Part = Part;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String Job) {
        this.Job = Job;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String Pic) {
        this.Pic = Pic;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }
}
