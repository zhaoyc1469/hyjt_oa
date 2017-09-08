package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.LaborUnionReqsListContract;
import com.hyjt.home.mvp.model.entity.Reqs.LUReqsListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class LaborUnionReqsListModel extends BaseModel implements LaborUnionReqsListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LaborUnionReqsListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<LUReqsListResp>> getLuList(LUReqsListReqs LUReqsListReqs) {


        Observable<BaseJson<LUReqsListResp>> LUReqsList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .laborUnionReqsList(LUReqsListReqs);
        return LUReqsList;
    }
}