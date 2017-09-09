package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ContributeIdeaDetailContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class ContributeIdeaDetailModel extends BaseModel implements ContributeIdeaDetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ContributeIdeaDetailModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CIdeaDetailResp>> cIDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<CIdeaDetailResp>> cIDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .contributeIdeaDetail(baseIdReqs);
        return cIDetail;
    }

    @Override
    public Observable<BaseJson<Object>> cIDel(BaseIdReqs baseIdReqs) {

        Observable<BaseJson<Object>> cIDel = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .contributeIdeaDelete(baseIdReqs);
        return cIDel;
    }

    @Override
    public Observable<BaseJson<Object>> cIEdit(CIdeaDetailResp detailResp) {
        Log.e("http_ci_edit", detailResp.toString());

        Observable<BaseJson<Object>> cIEdit = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .contributeIdeaEdit(detailResp);
        return cIEdit;
    }
}