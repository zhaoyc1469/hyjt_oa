package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ApplyExpenseListContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpenseListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.BlocNoticeListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class ApplyExpenseListModel extends BaseModel implements ApplyExpenseListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ApplyExpenseListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<ApplyExpenseListResp>> getApplyExpList(ApplyExpenseListReqs applyExpenseListReqs) {
        Log.e("http_reqs", applyExpenseListReqs.toString());
        Observable<BaseJson<ApplyExpenseListResp>> getApplyExpList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .ApplyExpenseReqsList(applyExpenseListReqs);
        return getApplyExpList;
    }
}