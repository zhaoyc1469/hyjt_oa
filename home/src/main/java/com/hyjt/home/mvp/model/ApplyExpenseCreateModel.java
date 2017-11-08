package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ApplyExpenseCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseNumReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class ApplyExpenseCreateModel extends BaseModel implements ApplyExpenseCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ApplyExpenseCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs) {
        Observable<BaseJson<PLFristLeaderResp>> fristLeader = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanFlowLeader(baseTypeReqs);
        return fristLeader;
    }

    @Override
    public Observable<BaseJson<ApplyExpTypeResp>> getApplyExpTypeList() {
        Observable<BaseJson<ApplyExpTypeResp>> applyExpType = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getApplyExpTypeList();
        return applyExpType;
    }

    @Override
    public Observable<BaseJson<PLCompanyResp>> getCompany() {
        Observable<BaseJson<PLCompanyResp>> plCompany = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanCompany();
        return plCompany;
    }

    @Override
    public Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart) {
        Observable<BaseJson<ImgUploadResp>> fileUpload = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .imgUpload(filePart);
        return fileUpload;
    }

    @Override
    public Observable<BaseJson<Object>> createApplyExp(ApplyExpCreateReqs applyExpCreateReqs) {
        Observable<BaseJson<Object>> createReqs = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .ApplyExpeCreateReqs(applyExpCreateReqs);
        return createReqs;
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
}