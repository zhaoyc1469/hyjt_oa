package com.hyjt.client.mvp.contract;


import com.hyjt.client.mvp.model.entity.LoginResp;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;

import io.reactivex.Observable;

public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void skipHome(LoginResp loginResp);

        void hideDialog();

        void showLoading(String msg);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<LoginResp>> sendLogin(String username, String password, int rebPwd, String registrationID);

    }
}