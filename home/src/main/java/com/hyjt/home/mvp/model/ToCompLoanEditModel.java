package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Inject;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ToCompLoanEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class ToCompLoanEditModel extends BaseModel implements ToCompLoanEditContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ToCompLoanEditModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CompLoanDetailResp>> getCLDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<CompLoanDetailResp>> clDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanDetail(baseIdReqs);
        return clDetail;
    }

    @Override
    public Observable<BaseJson<Object>> delCLDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> delDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanDel(baseIdReqs);
        return delDetail;
    }
}