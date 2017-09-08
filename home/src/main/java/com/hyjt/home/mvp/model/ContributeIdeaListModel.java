package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ContributeIdeaListContract;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class ContributeIdeaListModel extends BaseModel implements ContributeIdeaListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ContributeIdeaListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CIdeaListResp>> getIdeaList(CIdeaListReqs cIdeaListReqs) {
        Log.e("http_CIdeaListReqs", cIdeaListReqs.toString());

        Observable<BaseJson<CIdeaListResp>> CIdeaList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .contributeIdeaList(cIdeaListReqs);
        return CIdeaList;
    }
}