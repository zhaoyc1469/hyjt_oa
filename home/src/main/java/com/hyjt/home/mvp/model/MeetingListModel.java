package com.hyjt.home.mvp.model;


import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.MeetingListContract;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class MeetingListModel extends BaseModel implements MeetingListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MeetingListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<MeetingListResp>> getMeetingList(int Page, int Rows, String Department, String CM_Num, String CM_name, String CM_promoter) {
        Observable<BaseJson<MeetingListResp>> meetingList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getMeetingList(Page, Rows, Department, CM_Num, CM_name, CM_promoter);
        return meetingList;
    }
}