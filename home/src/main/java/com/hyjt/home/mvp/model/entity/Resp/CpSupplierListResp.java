package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;



public class CpSupplierListResp {


    private List<SupPackBean> SupPack;

    public List<SupPackBean> getSupPack() {
        return SupPack;
    }

    public void setSupPack(List<SupPackBean> SupPack) {
        this.SupPack = SupPack;
    }

    public static class SupPackBean {
        /**
         * CwCSupplierID : 2015112510273767035761328fc1fe0
         * CwCSupplierI : 沈阳瀚唐鼎盛科技有限公司
         * CwCSupbank : 中国农业银行
         * CwCSupnum : null
         */

        private String CwCSupplierID;
        private String CwCSupplierI;
        private String CwCSupbank;
        private String CwCSupnum;

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
