package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/24  10:21
 * @desc ${TODO}
 */

public class CEmailListResp {

    /**
     * total : 64
     * rows : [{"Id":"2017030913535276723664a02c65152","Name":"李大鹏 曾虎 ","Opened":"已读","Theme":"胡总编写的工程简介","Email":"辽宁环宇集团创立于1998年，总部位于辽宁省沈阳市。集团下设辽宁环宇工程咨询管理有限公司，辽宁寰宇会计师事务所有限公司，辽宁环宇矿业咨询有限公司，环宇矿山设计研究院，环宇地质勘查设计研究院，环宇安全评价公司，环宇矿业权评估公司，辽宁环宇房地产评估公司，环宇工程审计公司，环宇工程可研报告公司，环宇政府采购招标代理公司。自成立以来，环宇集团始终秉承解放思想、与时俱进的理念，不断发展壮大。环宇坚信，企业的经营就是思想的经营，企业只有在经营理念上攀登上了思想的高峰，才能够获得永恒的生命力。以此为经营理念，环宇将外部的先进思想和客户、员工的体验、感受作为种子，在环宇独特的企业土壤中耕耘，结合环宇实践，培育出有环宇特色的经营思想，让思想成为企业做强的有力保障。\r\n　在人才结构上，集团高度重视人才结构的合理化配置。经过不断的完善，公司逐步建设了一支\u201c年龄结构合理，成熟与朝气共存，经验与潜力并进\u201d的员工队伍。公司配备实力雄厚的管理团队和专业齐全的技术团队。管理团队囊括经济、法律及技术等专业人士，技术团队中工程咨询、工程造价咨询、招标代理及财务咨询等专业技术人员齐备。\r\n　　人才队伍中具有多类别的国家注册执业资格人员\u2014\u2014注册造价工程师、咨询工程师（投资）、注册一级建造师、注册二级建造师、注册建筑师、注册结构工程师、注册房地产评估师、注册土地评估师、招标师、注册会计师、律师、储量评估师、矿业权评估师、安全评价师等。我们以优秀人才的合理使用为环宇集团发展的核心，以此为环宇集团不断发展的不竭动力。\r\n展望未来，集团将继续坚持创新，坚持转换思维模式，适应开放性市场的需求，采用更为先进的制度去经营环宇，不断开拓创新。集团制定了科学的发展计划，通过与高等院校、科研院所以及国内外企业的精诚合作，进行专业技术的深入研究；同时根据自身特点，逐步建设覆盖工程咨询、工程造价咨询、招标投标代理、工程审计及财务咨询等咨询业务群，打造全过程代理建设模式、可行性研究设计、地质勘查标准化服务、招标投标、预算、结算，财务决算等精品专业咨询业务。在为社会、委托方提供完整方案的同时打造环宇品牌，进一步确立环宇在经济咨询领域的领先地位。\r\n经过十几年的发展，环宇不断地求新求变，但其前进的方向却从未改变，环宇集团将一如既往地沿着规范化、专业化、国际化的道路不断前进！","State":"李大鹏,","CreateTime":"2017-03-09 13:53:52","Sender":"张玮","CreatePersonId":"2014071010080311437881f7f8cc7ed","Version":[0,0,0,0,5,184,84,217],"FileUploaderId":"","SysPersonId":"李大鹏 曾虎 "}]
     */
    private int total;
    private String type;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * Id : 2017030913535276723664a02c65152
         * Name : 李大鹏 曾虎
         * Opened : 已读
         * Theme : 胡总编写的工程简介
         * Email : 辽宁环宇集团创立于1998年，总部位于辽宁省沈阳市。集团下设辽宁环宇工程咨询管理有限公司，辽宁寰宇会计师事务所有限公司，辽宁环宇矿业咨询有限公司，环宇矿山设计研究院，环宇地质勘查设计研究院，环宇安全评价公司，环宇矿业权评估公司，辽宁环宇房地产评估公司，环宇工程审计公司，环宇工程可研报告公司，环宇政府采购招标代理公司。自成立以来，环宇集团始终秉承解放思想、与时俱进的理念，不断发展壮大。环宇坚信，企业的经营就是思想的经营，企业只有在经营理念上攀登上了思想的高峰，才能够获得永恒的生命力。以此为经营理念，环宇将外部的先进思想和客户、员工的体验、感受作为种子，在环宇独特的企业土壤中耕耘，结合环宇实践，培育出有环宇特色的经营思想，让思想成为企业做强的有力保障。
         　在人才结构上，集团高度重视人才结构的合理化配置。经过不断的完善，公司逐步建设了一支“年龄结构合理，成熟与朝气共存，经验与潜力并进”的员工队伍。公司配备实力雄厚的管理团队和专业齐全的技术团队。管理团队囊括经济、法律及技术等专业人士，技术团队中工程咨询、工程造价咨询、招标代理及财务咨询等专业技术人员齐备。
         　　人才队伍中具有多类别的国家注册执业资格人员——注册造价工程师、咨询工程师（投资）、注册一级建造师、注册二级建造师、注册建筑师、注册结构工程师、注册房地产评估师、注册土地评估师、招标师、注册会计师、律师、储量评估师、矿业权评估师、安全评价师等。我们以优秀人才的合理使用为环宇集团发展的核心，以此为环宇集团不断发展的不竭动力。
         展望未来，集团将继续坚持创新，坚持转换思维模式，适应开放性市场的需求，采用更为先进的制度去经营环宇，不断开拓创新。集团制定了科学的发展计划，通过与高等院校、科研院所以及国内外企业的精诚合作，进行专业技术的深入研究；同时根据自身特点，逐步建设覆盖工程咨询、工程造价咨询、招标投标代理、工程审计及财务咨询等咨询业务群，打造全过程代理建设模式、可行性研究设计、地质勘查标准化服务、招标投标、预算、结算，财务决算等精品专业咨询业务。在为社会、委托方提供完整方案的同时打造环宇品牌，进一步确立环宇在经济咨询领域的领先地位。
         经过十几年的发展，环宇不断地求新求变，但其前进的方向却从未改变，环宇集团将一如既往地沿着规范化、专业化、国际化的道路不断前进！
         * State : 李大鹏,
         * CreateTime : 2017-03-09 13:53:52
         * Sender : 张玮
         * CreatePersonId : 2014071010080311437881f7f8cc7ed
         * Version : [0,0,0,0,5,184,84,217]
         * FileUploaderId :
         * SysPersonId : 李大鹏 曾虎
         */

        private String Id;
        private String Name;
        private String Opened;
        private String Theme;
        private String Email;
        private String State;
        private String CreateTime;
        private String Sender;
        private String CreatePersonId;
        private String FileUploaderId;
        private String SysPersonId;
        private List<Integer> Version;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getOpened() {
            return Opened;
        }

        public void setOpened(String Opened) {
            this.Opened = Opened;
        }

        public String getTheme() {
            return Theme;
        }

        public void setTheme(String Theme) {
            this.Theme = Theme;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getSender() {
            return Sender;
        }

        public void setSender(String Sender) {
            this.Sender = Sender;
        }

        public String getCreatePersonId() {
            return CreatePersonId;
        }

        public void setCreatePersonId(String CreatePersonId) {
            this.CreatePersonId = CreatePersonId;
        }

        public String getFileUploaderId() {
            return FileUploaderId;
        }

        public void setFileUploaderId(String FileUploaderId) {
            this.FileUploaderId = FileUploaderId;
        }

        public String getSysPersonId() {
            return SysPersonId;
        }

        public void setSysPersonId(String SysPersonId) {
            this.SysPersonId = SysPersonId;
        }

        public List<Integer> getVersion() {
            return Version;
        }

        public void setVersion(List<Integer> Version) {
            this.Version = Version;
        }
    }
}
