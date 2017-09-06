package com.hyjt.client.mvp.model;


import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.client.mvp.contract.WorkContract;
import com.hyjt.client.mvp.model.entity.WorkMission;
import com.hyjt.client.mvp.model.service.AppService;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class WorkModel extends BaseModel implements WorkContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public WorkModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<WorkMission>> getMdMsgNum() {
        Observable<BaseJson<WorkMission>> MsgNum = mRepositoryManager.obtainRetrofitService(AppService.class)
                .getMdMsgNum();
        return MsgNum;
    }

    @Override
    public Observable<BaseJson<Object>> outLogin() {
        Observable<BaseJson<Object>> outLogin = mRepositoryManager.obtainRetrofitService(AppService.class)
                .outLogin();
        return outLogin;
    }
}