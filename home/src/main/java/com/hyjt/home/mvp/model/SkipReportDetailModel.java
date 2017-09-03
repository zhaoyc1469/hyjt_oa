package com.hyjt.home.mvp.model;

import com.google.gson.Gson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.SkipReportDetailContract;

import javax.inject.Inject;


@ActivityScope
public class SkipReportDetailModel extends BaseModel implements SkipReportDetailContract.Model {
    private Gson mGson;

    @Inject
    public SkipReportDetailModel(IRepositoryManager repositoryManager, Gson gson) {
        super(repositoryManager);
        this.mGson = gson;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
    }

}