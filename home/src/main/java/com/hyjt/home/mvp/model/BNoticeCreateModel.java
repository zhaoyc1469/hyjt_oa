package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.BNoticeCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class BNoticeCreateModel extends BaseModel implements BNoticeCreateContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public BNoticeCreateModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart) {
        Observable<BaseJson<ImgUploadResp>> fileUpload = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .imgUpload(filePart);
        return fileUpload;
    }

    @Override
    public Observable<BaseJson<Object>> sendBNotice(BNoticeDetailsResp bNoticeDetailsResp) {
        Observable<BaseJson<Object>> createBNotice = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .createBNotice(bNoticeDetailsResp.getTitle(),
                        bNoticeDetailsResp.getMessage(),
                        bNoticeDetailsResp.getFileUploader());
        return createBNotice;
    }
}