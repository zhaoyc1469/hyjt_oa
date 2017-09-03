package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.CreateMtContract;

import javax.inject.Inject;


@ActivityScope
public class CreateMtModel extends BaseModel implements CreateMtContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public CreateMtModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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