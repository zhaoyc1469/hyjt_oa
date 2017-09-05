package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ReportTopDetailContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class ReportTopDetailModel extends BaseModel implements ReportTopDetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ReportTopDetailModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<ReportTDetailResp>> reportTopDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<ReportTDetailResp>> reportTDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .reportTopDetail(baseIdReqs);
        return reportTDetail;
    }

    @Override
    public Observable<BaseJson<Object>> reportTopDel(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> reportTDel = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .reportTopDelete(baseIdReqs);
        return reportTDel;
    }

    @Override
    public Observable<BaseJson<Object>> reportTopEdit(ReportTDetailResp reportDetail) {
        Observable<BaseJson<Object>> reportTEdit = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .reportTopEdit(reportDetail);
        return reportTEdit;
    }
}