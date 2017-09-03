package com.hyjt.client.mvp.model;


import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.client.mvp.contract.LoginContract;
import com.hyjt.client.mvp.model.entity.LoginResp;
import com.hyjt.client.mvp.model.service.AppService;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseJson<LoginResp>> sendLogin(String username, String password, int rebPwd, String registrationID) {
        Observable<BaseJson<LoginResp>> users = mRepositoryManager.obtainRetrofitService(AppService.class)
                .getLoginResp(username, password, rebPwd, registrationID);
        return users;
    }

}