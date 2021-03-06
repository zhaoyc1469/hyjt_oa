package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ReportTopCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class ReportTopCreateModel extends BaseModel implements ReportTopCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ReportTopCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<Object>> reportTopCreate(ReportTDetailResp reportDetail) {
        Log.e("http_reportTopCreate", reportDetail.toString());

        Observable<BaseJson<Object>> reportTCreate = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .reportTopCreate(reportDetail);
        return reportTCreate;
    }

    @Override
    public Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment) {
        Observable<BaseJson<LinkManResp>> getLinkman = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getLinkman(Page, RP, SysDepartment);
        return getLinkman;
    }
}