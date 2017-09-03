package com.hyjt.client.mvp.model;


import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.client.mvp.contract.SplashContract;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

@ActivityScope
public class SplashModel extends BaseModel implements SplashContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SplashModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

}