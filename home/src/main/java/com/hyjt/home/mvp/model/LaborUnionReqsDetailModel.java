package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.LaborUnionReqsDetailContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class LaborUnionReqsDetailModel extends BaseModel implements LaborUnionReqsDetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LaborUnionReqsDetailModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<LUReqsDetailResp>> LUDetail(BaseIdReqs baseIdReqs) {

        Observable<BaseJson<LUReqsDetailResp>> cIDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .laborUnionReqsDetail(baseIdReqs);
        return cIDetail;
    }

    @Override
    public Observable<BaseJson<Object>> LUDel(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> cIDel = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .laborUnionReqsDelete(baseIdReqs);
        return cIDel;
    }

    @Override
    public Observable<BaseJson<Object>> LUEdit(LUReqsDetailResp detailResp) {
        Log.e("http_lu_edit", detailResp.toString());

        Observable<BaseJson<Object>> cIEdit = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .laborUnionReqsEdit(detailResp);
        return cIEdit;
    }
}