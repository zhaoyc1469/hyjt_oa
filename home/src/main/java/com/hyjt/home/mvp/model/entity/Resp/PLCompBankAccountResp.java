package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;


public class PLCompBankAccountResp {
    private List<BankPackBean> BankPack;

    public List<BankPackBean> getBankPack() {
        return BankPack;
    }

    public void setBankPack(List<BankPackBean> BankPack) {
        this.BankPack = BankPack;
    }

    public static class BankPackBean {
        /**
         * Company : 辽宁环宇工程咨询管理有限公司
         * BankName : 中国银行沈阳崇山东路支行1
         * BankNum : 11111111112
         * BankMoney : ￥100001
         */

        private String Company;
        private String BankName;
        private String BankNum;
        private String BankMoney;

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getBankNum() {
            return BankNum;
        }

        public void setBankNum(String BankNum) {
            this.BankNum = BankNum;
        }

        public String getBankMoney() {
            return BankMoney;
        }

        public void setBankMoney(String BankMoney) {
            this.BankMoney = BankMoney;
        }
    }
}
