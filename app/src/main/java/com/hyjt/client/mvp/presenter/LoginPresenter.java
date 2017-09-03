package com.hyjt.client.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.hyjt.client.mvp.contract.LoginContract;
import com.hyjt.client.mvp.model.entity.LoginResp;
import com.hyjt.client.mvp.ui.activity.HomeActivity;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.hyjt.frame.utils.UiUtils.startActivity;


@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler,  Application application) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }

    public void sendLoginMsg(String name, String pwd, int reb, String registrationID) {
        mModel.sendLogin(name, pwd, reb, registrationID)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() ->mRootView.hideDialog())//隐藏
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<LoginResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LoginResp loginResp) {
                        startActivity(new Intent(mApplication, HomeActivity.class));
                        mRootView.skipHome(loginResp);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

}