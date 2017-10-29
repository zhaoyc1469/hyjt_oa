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
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
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

    @Override
    public Observable<BaseJson<PLCompBankAccountResp>> compBankAccount() {
        Observable<BaseJson<PLCompBankAccountResp>> compBankAccount = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .plCompBankAccount();
        return compBankAccount;
    }

    @Override
    public Observable<BaseJson<Object>> flowNodeApprove(ClNodeApproveReqs clNodeApproveReqs) {
        Observable<BaseJson<Object>> flowNodeApr = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .clFlowNodeApr(clNodeApproveReqs);
        return flowNodeApr;
    }

    @Override
    public Observable<BaseJson<Object>> clTellerConfirm(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> tellerConfirm = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .clTellerConfirm(baseIdReqs);
        return tellerConfirm;
    }

    @Override
    public Observable<BaseJson<Object>> clReceiverConfirm(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> receiverConfirm = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .clReceiverConfirm(baseIdReqs);
        return receiverConfirm;
    }
}