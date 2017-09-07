package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.SkipReportCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


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

    @Override
    public Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment) {
        Observable<BaseJson<LinkManResp>> getLinkman = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getLinkman(Page, RP, SysDepartment);
        return getLinkman;
    }

    @Override
    public Observable<BaseJson<Object>> skipReportCreate(SReportDetailResp reportDetail) {
        Log.e("http_reportTopCreate", reportDetail.toString());

        Observable<BaseJson<Object>> reportTCreate = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .skipReportCreate(reportDetail);
        return reportTCreate;
    }
}