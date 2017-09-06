package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.SkipReportListContract;
import com.hyjt.home.mvp.model.entity.Reqs.SReportListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class SkipReportListModel extends BaseModel implements SkipReportListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SkipReportListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<SReportListResp>> getSReportList(SReportListReqs sReportListReqs) {
        Log.e("http_rtList",sReportListReqs.toString());

        Observable<BaseJson<SReportListResp>> reportList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .skipReportList(sReportListReqs);
        return reportList;
    }
}