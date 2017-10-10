package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanListContract;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class PsonLoanListModel extends BaseModel implements PsonLoanListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public PsonLoanListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<PsonLoanListResp>> getPsonLoanList(PsonLoanListReqs psonLoanListReqs) {
        Observable<BaseJson<PsonLoanListResp>> psonLoanReqsList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanReqsList(psonLoanListReqs);
        return psonLoanReqsList;
    }
}