package com.hyjt.frame.mvp;

public interface IView {

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 杀死自己
     */
    void killMyself();
}
