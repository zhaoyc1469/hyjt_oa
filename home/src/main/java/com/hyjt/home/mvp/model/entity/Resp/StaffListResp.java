package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/10  10:07
 * @desc ${TODO}
 */

public class StaffListResp {
    /**
     * rows : [{"cell":["2017060910053742926684fccfc9702","鞍山分公司","林琳","技术员","18804122739"]},{"cell":["201706090955273443719b2ea5235a3","鞍山分公司","龙宇翔","办事员","18804124001"]},{"cell":["2017052214331101856098892813802","","周桂琴","","13940311786"]},{"cell":["201704141230454844895f12c0c593f","地质勘查设计研究院","朱学忠","总工程师","13804901982"]},{"cell":["20170301095036981026827914c4aff","会计师事务所","韩爽","业务员","18640417336"]},{"cell":["2017020809073956053523c3e96d2d6","后勤及餐饮部","李红梅","","13664108563"]},{"cell":["2016112108102753861069f985309dc","集美公司","程学松","技术员","13998896322"]},{"cell":["2016100813480052875064f1cb15ec3","后勤及餐饮部","刘桂香","","15802427076"]},{"cell":["201610080826352296946231dc3d8f8","会计师事务所","迟懿瑶","业务员","15609824940"]},{"cell":["2016092810290816071093c86c36c76","后勤及餐饮部","陈士良","","13654145302"]}]
     * page : 1
     * FlexigridPara : {"Page":1,"RP":10,"SortName":"Id","SortOrder":"desc","Query":"E_state&201203161417244667328a9188bcf89^","QType":null,"Cols":"Id,Sys_IdOld,MyName,E_postion,MobilePhoneNumber"}
     * total : 144
     * Query : null
     */

    private int page;
    private FlexigridParaBean FlexigridPara;
    private int total;
    private Object Query;
    private List<RowsBean> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public FlexigridParaBean getFlexigridPara() {
        return FlexigridPara;
    }

    public void setFlexigridPara(FlexigridParaBean FlexigridPara) {
        this.FlexigridPara = FlexigridPara;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getQuery() {
        return Query;
    }

    public void setQuery(Object Query) {
        this.Query = Query;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class FlexigridParaBean {
        /**
         * Page : 1
         * RP : 10
         * SortName : Id
         * SortOrder : desc
         * Query : E_state&201203161417244667328a9188bcf89^
         * QType : null
         * Cols : Id,Sys_IdOld,MyName,E_postion,MobilePhoneNumber
         */

        private int Page;
        private int RP;
        private String SortName;
        private String SortOrder;
        private String Query;
        private Object QType;
        private String Cols;

        public int getPage() {
            return Page;
        }

        public void setPage(int Page) {
            this.Page = Page;
        }

        public int getRP() {
            return RP;
        }

        public void setRP(int RP) {
            this.RP = RP;
        }

        public String getSortName() {
            return SortName;
        }

        public void setSortName(String SortName) {
            this.SortName = SortName;
        }

        public String getSortOrder() {
            return SortOrder;
        }

        public void setSortOrder(String SortOrder) {
            this.SortOrder = SortOrder;
        }

        public String getQuery() {
            return Query;
        }

        public void setQuery(String Query) {
            this.Query = Query;
        }

        public Object getQType() {
            return QType;
        }

        public void setQType(Object QType) {
            this.QType = QType;
        }

        public String getCols() {
            return Cols;
        }

        public void setCols(String Cols) {
            this.Cols = Cols;
        }
    }

    public static class RowsBean {
        private List<String> cell;

        public List<String> getCell() {
            return cell;
        }

        public void setCell(List<String> cell) {
            this.cell = cell;
        }
    }
}
