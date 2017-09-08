package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.LaborUnionReqsCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class LaborUnionReqsCreateModel extends BaseModel implements LaborUnionReqsCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LaborUnionReqsCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<Object>> createLUReqs(LUReqsDetailResp detail) {
        Log.e("http_LUReqsDetailResp", detail.toString());

        Observable<BaseJson<Object>> reqsDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .laborUnionReqsCreate(detail);
        return reqsDetail;
    }
}