package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/22  9:48
 * @desc ${TODO}
 */

public class AddressBookResp {

    /**
     * rows : [{"cell":["201708211717403533571dfa299bcd2","集团","干干干","业务员",""]},{"cell":["201708211707341217571d830e3f0ef","","嘎嘎嘎嘎","总顾问",""]},{"cell":["201708211707335757571500aff7e34","","嘎嘎嘎嘎","总顾问",""]},{"cell":["20170821170013764957153a7e26742","","测试","主任","18669856698"]},{"cell":["201708210909181053357d5373c62d8","","太过分发","总顾问",""]},{"cell":["2017082011012479826895feb17c491","","嘎嘎嘎嘎","总工程师",""]},{"cell":["2017081809253957399897be5bbd4b0","董事长办公室","tgg","总工程师",""]},{"cell":["2017081717295477068000dffe01d22","董事长办公室","ffg","总工程师",""]},{"cell":["20170817172505285080095c0c5331a","人事部","ggg","管理员","1869881866"]},{"cell":["201708171503210618800191072f426","人事部","孙081703","技术员",""]}]
     * page : 1
     * FlexigridPara : {"Page":1,"RP":10,"SortName":"Id","SortOrder":"desc","Query":"Sys_Id&^MyName&^State&^E_maturetimeStart_Time&^E_maturetimeEnd_Time&^","QType":null,"Cols":"Id,Sys_IdOld,MyName,E_postion,MobilePhoneNumber"}
     * total : 700
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
         * Query : Sys_Id&^MyName&^State&^E_maturetimeStart_Time&^E_maturetimeEnd_Time&^
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
