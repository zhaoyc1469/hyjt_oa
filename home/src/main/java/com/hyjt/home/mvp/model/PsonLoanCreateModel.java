package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class PsonLoanCreateModel extends BaseModel implements PsonLoanCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public PsonLoanCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<PLBankAccountResp>> getBankAccount() {
        Observable<BaseJson<PLBankAccountResp>> bankAccount = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanBankAcount();
        return bankAccount;
    }

    @Override
    public Observable<BaseJson<PLFlowNodeResp>> getFlowNode() {
        return null;
    }

    @Override
    public Observable<BaseJson<PLCompanyResp>> getPLCompany() {
        Observable<BaseJson<PLCompanyResp>> plCompany = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanCompany();
        return plCompany;
    }

    @Override
    public Observable<BaseJson<Object>> createPsonLoan(PsonLoanCreateReqs psonLoanCreateReqs) {
        Log.e("http_psonLoanCreateReqs", psonLoanCreateReqs.toString());
        Observable<BaseJson<Object>> createPsonLoan = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .psonLoanCreate(psonLoanCreateReqs);
        return createPsonLoan;
    }

    @Override
    public Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart) {
        Observable<BaseJson<ImgUploadResp>> fileUpload = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .imgUpload(filePart);
        return fileUpload;
    }
}