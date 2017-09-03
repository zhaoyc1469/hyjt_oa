package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.SkipReportCreateContract;


@ActivityScope
public class SkipReportCreateModel extends BaseModel implements SkipReportCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SkipReportCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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