package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.SLConsultEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class SLConsultEditModel extends BaseModel implements SLConsultEditContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SLConsultEditModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<SLConsultDetailResp>> slconsultDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<SLConsultDetailResp>> consultDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .SLConsultDetail(baseIdReqs);
        return consultDetail;
    }

    @Override
    public Observable<BaseJson<Object>> slconsultDel(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> consultDel = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .SLConsultDel(baseIdReqs);
        return consultDel;
    }

    @Override
    public Observable<BaseJson<Object>> slconsultEdit(SLConsultDetailResp consultResp) {
        Observable<BaseJson<Object>> consultEdit = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .SLConsultEdit(consultResp);
        return consultEdit;
    }
}