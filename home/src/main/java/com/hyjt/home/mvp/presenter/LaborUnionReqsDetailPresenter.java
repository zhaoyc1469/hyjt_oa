package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.LaborUnionReqsDetailContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;


@ActivityScope
public class LaborUnionReqsDetailPresenter extends BasePresenter<LaborUnionReqsDetailContract.Model, LaborUnionReqsDetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public LaborUnionReqsDetailPresenter(LaborUnionReqsDetailContract.Model model, LaborUnionReqsDetailContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void LUDetail(String Id) {

        mModel.LUDetail(new BaseIdReqs(Id))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<LUReqsDetailResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LUReqsDetailResp Detail) {
                        mRootView.setLUDetail(Detail);
                    }
                });
    }

    public void LUEdit(LUReqsDetailResp Detail){

        mModel.LUEdit(Detail)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object reportTDetail) {
                        mRootView.showMessage("编辑成功");
                        mRootView.killMyself();
                    }
                });
    }

    public void LUDel(String Id){

        mModel.LUDel(new BaseIdReqs(Id))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("删除成功");
                        mRootView.killMyself();
                    }
                });
    }

}
