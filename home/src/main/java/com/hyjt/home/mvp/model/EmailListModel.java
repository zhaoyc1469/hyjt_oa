package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.EmailListContract;
import com.hyjt.home.mvp.model.entity.Reqs.EmailListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CEmailListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class EmailListModel extends BaseModel implements EmailListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public EmailListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CEmailListResp>> getEmailList(EmailListReqs emailListReqs) {

        Log.e("http_email",emailListReqs.toString());

        Observable<BaseJson<CEmailListResp>> emailList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getEmailList(emailListReqs);
        return emailList;
    }

}