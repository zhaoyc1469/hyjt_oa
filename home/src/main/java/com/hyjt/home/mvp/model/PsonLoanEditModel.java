package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PlNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;


@ActivityScope
public class PsonLoanEditModel extends BaseModel implements PsonLoanEditContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public PsonLoanEditModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<PsonLoanDetailResp>> getPLDetail(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<PsonLoanDetailResp>> plDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanDetail(baseIdReqs);
        return plDetail;
    }

    @Override
    public Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs) {
        Observable<BaseJson<PLFristLeaderResp>> fristLeader = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanFlowLeader(baseTypeReqs);
        return fristLeader;
    }

    @Override
    public Observable<BaseJson<PLBankAccountResp>> getBankAccount() {
        Observable<BaseJson<PLBankAccountResp>> bankAccount = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanBankAcount();
        return bankAccount;
    }

    @Override
    public Observable<BaseJson<PLCompanyResp>> getPLCompany() {
        Observable<BaseJson<PLCompanyResp>> plCompany = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanCompany();
        return plCompany;
    }

    @Override
    public Observable<BaseJson<Object>> delPsonLoan(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> delPsonLoan = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanDel(baseIdReqs);
        return delPsonLoan;
    }

    @Override
    public Observable<BaseJson<Object>> editPsonLoan(PsonLoanCreateReqs psonLoanCreateReqs) {
        Observable<BaseJson<Object>> editPsonLoan = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanEdit(psonLoanCreateReqs);
        return editPsonLoan;
    }

    @Override
    public Observable<BaseJson<Object>> flowNodeApprove(PlNodeApproveReqs PlNodeApproveReqs) {
        Observable<BaseJson<Object>> flowNodeApr = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .plFlowNodeApr(PlNodeApproveReqs);
        return flowNodeApr;
    }

    @Override
    public Observable<BaseJson<PLCompBankAccountResp>> compBankAccount() {
        Observable<BaseJson<PLCompBankAccountResp>> compBankAccount = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .plCompBankAccount();
        return compBankAccount;
    }

    @Override
    public Observable<BaseJson<Object>> plTellerConfirm(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> tellerConfirm = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .plTellerConfirm(baseIdReqs);
        return tellerConfirm;
    }

    @Override
    public Observable<BaseJson<Object>> plReceiverConfirm(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> receiverConfirm = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .plReceiverConfirm(baseIdReqs);
        return receiverConfirm;
    }

}