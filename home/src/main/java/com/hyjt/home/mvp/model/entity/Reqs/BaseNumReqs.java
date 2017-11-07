package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/29  16:25
 * @desc ${TODO}
 */

public class BaseNumReqs {

    private String Num;

    public BaseNumReqs(String num) {
        Num = num;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    @Override
    public String toString() {
        return "BaseNumReqs{" +
                "Num='" + Num + '\'' +
                '}';
    }
}
