package com.hyjt.client.mvp.model;


import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.client.mvp.contract.LinkmanContract;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class LinkmanModel extends BaseModel implements LinkmanContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LinkmanModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<List<ChildrenBean>>> reqsDeptList() {
        Observable<BaseJson<List<ChildrenBean>>> deptList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getDeptList();
        return deptList;
    }

    @Override
    public Observable<BaseJson<StaffListResp>> getAddressBook(int Page, int RP, String State, String deptId, String Name, String edu, String startTime, String endTime) {
        Observable<BaseJson<StaffListResp>> getAddressBook = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getAddressBook(Page, RP, State, deptId, Name, edu, startTime, endTime);
        return getAddressBook;
    }


}