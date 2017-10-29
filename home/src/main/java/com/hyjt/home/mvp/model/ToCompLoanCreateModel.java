package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import javax.inject.Inject;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanContractReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.CpSupplierListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.service.HomeService;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class ToCompLoanCreateModel extends BaseModel implements ToCompLoanCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ToCompLoanCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<CpContractListResp>> getCLContractList(CompLoanContractReqs compLoanContractReqs) {
        Observable<BaseJson<CpContractListResp>> getCLContractList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanContract(compLoanContractReqs);
        return getCLContractList;
    }

    @Override
    public Observable<BaseJson<Object>> CompLoanCreate(CompLoanCreateReqs compLoanCreateReqsReqs) {
        Observable<BaseJson<Object>> compLoanCreate = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanCreate(compLoanCreateReqsReqs);
        return compLoanCreate;
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
    public Observable<BaseJson<PLCompanyResp>> getTCLCompany() {
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
    public Observable<BaseJson<CpSupplierListResp>> getCLSupplierList(CompLoanContractReqs compLoanContractReqs) {
        Observable<BaseJson<CpSupplierListResp>> getCLSupplierList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .compLoanSupplier(compLoanContractReqs);
        return getCLSupplierList;
    }
}