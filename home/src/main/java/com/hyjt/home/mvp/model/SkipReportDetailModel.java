package com.hyjt.home.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.SkipReportDetailContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class SkipReportDetailModel extends BaseModel implements SkipReportDetailContract.Model {
    private Gson mGson;

    @Inject
    public SkipReportDetailModel(IRepositoryManager repositoryManager, Gson gson) {
        super(repositoryManager);
        this.mGson = gson;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
    }

    @Override
    public Observable<BaseJson<SReportDetailResp>> sReportDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<SReportDetailResp>> reportDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .skipReportDetail(baseIdReqs);
        return reportDetail;
    }

    @Override
    public Observable<BaseJson<Object>> sReportDel(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> reportDel = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .skipReportDelete(baseIdReqs);
        return reportDel;
    }

    @Override
    public Observable<BaseJson<Object>> sReportEdit(SReportDetailResp detailResp) {
        Log.e("http_rpt_edit", detailResp.toString());

        Observable<BaseJson<Object>> reportEdit = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .skipReportEdit(detailResp);
        return reportEdit;
    }
}