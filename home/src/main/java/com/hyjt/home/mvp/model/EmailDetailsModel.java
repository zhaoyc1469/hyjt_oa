package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.EmailDetailsContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class EmailDetailsModel extends BaseModel implements EmailDetailsContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public EmailDetailsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CEmailDetailResp>> getEmailDetails(String Id) {
        BaseIdReqs baseIdReqs = new BaseIdReqs(Id);
        Log.e("http_BaseIdReqs", baseIdReqs.toString());

        Observable<BaseJson<CEmailDetailResp>> emailDetails = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getEmailDetails(baseIdReqs);
        return emailDetails;
    }

}