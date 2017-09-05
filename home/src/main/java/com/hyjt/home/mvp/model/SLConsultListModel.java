package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.SLConsultListContract;
import com.hyjt.home.mvp.model.entity.Reqs.SLConsultListReqs;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class SLConsultListModel extends BaseModel implements SLConsultListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SLConsultListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<SLConsultListResp>> SLConsultList(SLConsultListReqs sLConsultListReqs) {

        Log.e("http_SLConsultListReqs", sLConsultListReqs.toString());

        Observable<BaseJson<SLConsultListResp>> SLConsultList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .SLConsultList(sLConsultListReqs);
        return SLConsultList;
    }
}