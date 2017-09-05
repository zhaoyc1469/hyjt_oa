package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ReportTopListContract;
import com.hyjt.home.mvp.model.entity.Reqs.ReportTopListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class ReportTopListModel extends BaseModel implements ReportTopListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ReportTopListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<ReportTListResp>> getReportTopList(ReportTopListReqs reportTopListReqs) {
        Log.e("http_rtList",reportTopListReqs.toString());

        Observable<BaseJson<ReportTListResp>> reportList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .reportTopList(reportTopListReqs);
        return reportList;
    }
}