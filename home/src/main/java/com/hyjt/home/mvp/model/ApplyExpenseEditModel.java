package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ApplyExpenseEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpenseListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class ApplyExpenseEditModel extends BaseModel implements ApplyExpenseEditContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ApplyExpenseEditModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<ApplyExpDetailResp>> getApplyExpDetail(BaseIdReqs baseIdReqs) {
        Log.e("http_reqs", baseIdReqs.toString());
        Observable<BaseJson<ApplyExpDetailResp>> getApplyExpList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .ApplyExpeDetailReqs(baseIdReqs);
        return getApplyExpList;
    }

    @Override
    public Observable<BaseJson<PLCompBankAccountResp>> applyExpBankAccount() {

        Observable<BaseJson<PLCompBankAccountResp>> applyExpBankAccount = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .plCompBankAccount();
        return applyExpBankAccount;
    }

    @Override
    public Observable<BaseJson<Object>> flowNodeApprove(ClNodeApproveReqs clNodeApproveReqs) {
        Observable<BaseJson<Object>> flowNodeApr = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .aeFlowNodeApr(clNodeApproveReqs);
        return flowNodeApr;
    }

    @Override
    public Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs) {
        Observable<BaseJson<PLFristLeaderResp>> fristLeader = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanFlowLeader(baseTypeReqs);
        return fristLeader;
    }

    @Override
    public Observable<BaseJson<PLCompanyResp>> getCompany() {
        Observable<BaseJson<PLCompanyResp>> plCompany = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanCompany();
        return plCompany;
    }

    @Override
    public Observable<BaseJson<Object>> aeTellerConfirm(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> tellerConfirm = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .aeTellerConfirm(baseIdReqs);
        return tellerConfirm;
    }

    @Override
    public Observable<BaseJson<Object>> aeReceiverConfirm(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> receiverConfirm = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .aeReceiverConfirm(baseIdReqs);
        return receiverConfirm;
    }

    @Override
    public Observable<BaseJson<ApplyExpTypeResp>> getApplyExpTypeList() {
        Observable<BaseJson<ApplyExpTypeResp>> applyExpType = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getApplyExpTypeList();
        return applyExpType;
    }
}