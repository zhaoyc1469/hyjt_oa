package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;


public class CpContractListResp {

    /**Supplier
     * total : 7
     * rows : [{"CwC_id":"201709251354359705047cd02f2feed","CwCOnum":"KYTH20170925-0001","CwCOname":"创建测试","CwCSupplierID":"20150513153836854197033f00d6c7a","CwCSupplierI":"南京豪晟工贸有限公司","CwCSupbank":"中国建设银行","CwCSupnum":"XXXXXXXXX"},{"CwC_id":"201707270956516099038668210e34a","CwCOnum":"KYTH20170727-0001","CwCOname":"电脑采购合同2","CwCSupplierID":"20150513153836854197033f00d6c7a","CwCSupplierI":"南京豪晟工贸有限公司","CwCSupbank":"中国建设银行","CwCSupnum":"XXXXXXXXX"},{"CwC_id":"2017072413463491386341c434d7df0","CwCOnum":"dn001","CwCOname":"电脑采购","CwCSupplierID":"20150806093627849295224acb98e62","CwCSupplierI":"沈阳市皇姑区鑫汇艺园家具","CwCSupbank":"中国银行","CwCSupnum":null},{"CwC_id":"2017072413440198511630da04673d5","CwCOnum":"dyj0021","CwCOname":"打印机采购合同1","CwCSupplierID":"2015102010531241361545866d577b4","CwCSupplierI":"沈阳市高新区乾���电子经销部","CwCSupbank":"交通银行","CwCSupnum":null},{"CwC_id":"201707241340181093114560ce22f7c","CwCOnum":"DN001","CwCOname":"电脑采购合同","CwCSupplierID":"2015102010531241361545866d577b4","CwCSupplierI":"沈阳市高新区乾祥电子经销部","CwCSupbank":"交通银行","CwCSupnum":null},{"CwC_id":"201707241329247299402e1b79890a7","CwCOnum":"合同编号","CwCOname":"合同名称","CwCSupplierID":"2015112510273767035761328fc1fe0","CwCSupplierI":"沈阳瀚唐鼎盛科技有限公司","CwCSupbank":"中国农业银行","CwCSupnum":null},{"CwC_id":"201707241322475552231f8e0a6103e","CwCOnum":null,"CwCOname":null,"CwCSupplierID":null,"CwCSupplierI":null,"CwCSupbank":null,"CwCSupnum":null}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * CwC_id : 201709251354359705047cd02f2feed
         * CwCOnum : KYTH20170925-0001
         * CwCOname : 创建测试
         * CwCSupplierID : 20150513153836854197033f00d6c7a
         * CwCSupplierI : 南京豪晟工贸有限公司
         * CwCSupbank : 中国建设银行
         * CwCSupnum : XXXXXXXXX
         */

        private String CwC_id;
        private String CwCOnum;
        private String CwCOname;
        private String CwCSupplierID;
        private String CwCSupplierI;
        private String CwCSupbank;
        private String CwCSupnum;

        public String getCwC_id() {
            return CwC_id;
        }

        public void setCwC_id(String CwC_id) {
            this.CwC_id = CwC_id;
        }

        public String getCwCOnum() {
            return CwCOnum;
        }

        public void setCwCOnum(String CwCOnum) {
            this.CwCOnum = CwCOnum;
        }

        public String getCwCOname() {
            return CwCOname;
        }

        public void setCwCOname(String CwCOname) {
            this.CwCOname = CwCOname;
        }

        public String getCwCSupplierID() {
            return CwCSupplierID;
        }

        public void setCwCSupplierID(String CwCSupplierID) {
            this.CwCSupplierID = CwCSupplierID;
        }

        public String getCwCSupplierI() {
            return CwCSupplierI;
        }

        public void setCwCSupplierI(String CwCSupplierI) {
            this.CwCSupplierI = CwCSupplierI;
        }

        public String getCwCSupbank() {
            return CwCSupbank;
        }

        public void setCwCSupbank(String CwCSupbank) {
            this.CwCSupbank = CwCSupbank;
        }

        public String getCwCSupnum() {
            return CwCSupnum;
        }

        public void setCwCSupnum(String CwCSupnum) {
            this.CwCSupnum = CwCSupnum;
        }
    }
}
