package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import javax.inject.Inject;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;


@ActivityScope
public class ToCompLoanCreateModel extends BaseModel implements ToCompLoanCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ToCompLoanCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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