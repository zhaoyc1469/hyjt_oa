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
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpenseListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseNumReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
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
    public Observable<BaseJson<PsonLoanListResp>> getPsonLoanList(PsonLoanListReqs psonLoanListReqs) {
        Log.e("http_reqs", psonLoanListReqs.toString());
        Observable<BaseJson<PsonLoanListResp>> psonLoanReqsList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanReqsList(psonLoanListReqs);
        return psonLoanReqsList;
    }

    @Override
    public Observable<BaseJson<CompLoanListResp>> getCompLoanList(CompLoanListReqs compLoanListReqs) {
        Log.e("http_CompLoan", compLoanListReqs.toString());
        Observable<BaseJson<CompLoanListResp>> compLoanReqsList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanReqsList(compLoanListReqs);
        return compLoanReqsList;
}

    @Override
    public Observable<BaseJson<AEExpMoneyResp>> getExpMoney(BaseNumReqs baseNumReqs) {
        Log.e("http_BaseNumReqs", baseNumReqs.toString());
        Observable<BaseJson<AEExpMoneyResp>> aeexpMoneyResp = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getExpMoney(baseNumReqs);
        return aeexpMoneyResp;
    }

    @Override
    public Observable<BaseJson<PLBankAccountResp>> getReceiveBank() {
        Observable<BaseJson<PLBankAccountResp>> bankAccount = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanBankAcount();
        return bankAccount;
    }

    @Override
    public Observable<BaseJson<Object>> delApplyExp(BaseIdReqs baseIdReqs) {
        Observable<BaseJson<Object>> delApplyExp = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .applyExpDel(baseIdReqs);
        return delApplyExp;
    }

    @Override
    public Observable<BaseJson<Object>> editApplyExp(ApplyExpCreateReqs applyExpCreateReqs) {
        Observable<BaseJson<Object>> editApplyExp = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .applyExpEdit(applyExpCreateReqs);
        return editApplyExp;
    }

    @Override
    public Observable<BaseJson<ApplyExpTypeResp>> getApplyExpTypeList() {
        Observable<BaseJson<ApplyExpTypeResp>> applyExpType = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getApplyExpTypeList();
        return applyExpType;
    }
}