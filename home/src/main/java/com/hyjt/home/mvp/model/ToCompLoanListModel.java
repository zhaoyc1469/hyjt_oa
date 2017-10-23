package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import javax.inject.Inject;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ToCompLoanListContract;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class ToCompLoanListModel extends BaseModel implements ToCompLoanListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ToCompLoanListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CompLoanListResp>> getCompLoanList(CompLoanListReqs compLoanListReqs) {
        Log.e("http_CompLoan", compLoanListReqs.toString());
        Observable<BaseJson<CompLoanListResp>> compLoanReqsList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanReqsList(compLoanListReqs);
        return compLoanReqsList;
    }
}