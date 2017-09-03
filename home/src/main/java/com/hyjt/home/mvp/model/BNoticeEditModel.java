package com.hyjt.home.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.BNoticeEditContract;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class BNoticeEditModel extends BaseModel implements BNoticeEditContract.Model {
    private Gson mGson;

    @Inject
    public BNoticeEditModel(IRepositoryManager repositoryManager, Gson gson) {
        super(repositoryManager);
        this.mGson = gson;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
    }

    @Override
    public Observable<BaseJson<BNoticeDetailsResp>> getBNoticeDetail(String Id) {
        Observable<BaseJson<BNoticeDetailsResp>> bNoticeDetail = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getBNoticeDetail(Id);
        return bNoticeDetail;
    }

    @Override
    public Observable<BaseJson<Object>> delBNotice(String Id) {
        Observable<BaseJson<Object>> delBNotice = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .delBNotice(Id);
        return delBNotice;
    }


    @Override
    public Observable<BaseJson<Object>> editBNotice(BNoticeDetailsResp bNoticeDetailsResp) {
        Log.e("http_editBNotice", bNoticeDetailsResp.toString());
        Observable<BaseJson<Object>> editBNotice = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .editBNotice(bNoticeDetailsResp.getId(),
                        bNoticeDetailsResp.getTitle(),
                        bNoticeDetailsResp.getMessage(),
                        bNoticeDetailsResp.getFileUploader(),
                        bNoticeDetailsResp.getFileUploaderIdOld());
        return editBNotice;
    }

    @Override
    public Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart) {
        Observable<BaseJson<ImgUploadResp>> fileUpload = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .imgUpload(filePart);
        return fileUpload;
    }
}