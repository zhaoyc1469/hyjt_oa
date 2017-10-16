package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;


public class PLBankAccountResp {

    private List<BankPackBean> BankPack;

    public List<BankPackBean> getBankPack() {
        return BankPack;
    }

    public void setBankPack(List<BankPackBean> BankPack) {
        this.BankPack = BankPack;
    }

    public static class BankPackBean {
        /**
         * BankPerson : 李大鹏
         * BankName : 建设银行
         * BankNum : 1111111
         */

        private String BankPerson;
        private String BankName;
        private String BankNum;

        public String getBankPerson() {
            return BankPerson;
        }

        public void setBankPerson(String BankPerson) {
            this.BankPerson = BankPerson;
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
    }
}
