package com.hyjt.home.mvp.model;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.BlocNoticeListContract;
import com.hyjt.home.mvp.model.entity.Resp.BlocNoticeListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class BlocNoticeListModel extends BaseModel implements BlocNoticeListContract.Model {
    private Gson mGson;

    @Inject
    public BlocNoticeListModel(IRepositoryManager repositoryManager, Gson gson) {
        super(repositoryManager);
        this.mGson = gson;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
    }

    @Override
    public Observable<BaseJson<Object>> getBNoticeLimits() {
        Observable<BaseJson<Object>> getBNoticeLimits = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getBNoticeLimits();
        return getBNoticeLimits;
    }

    @Override
    public Observable<BaseJson<BlocNoticeListResp>> getBNoticeList(int Page, int Rows) {
        Observable<BaseJson<BlocNoticeListResp>> getBNoticeList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getBNoticeList(Page, Rows);
        return getBNoticeList;
    }

}