package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.EmailDetailsContract;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class EmailDetailsPresenter extends BasePresenter<EmailDetailsContract.Model, EmailDetailsContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public EmailDetailsPresenter(EmailDetailsContract.Model model, EmailDetailsContract.View rootView
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

    public void reqsEmDetail(String Id){

        mModel.getEmailDetails(Id)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<CEmailDetailResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull CEmailDetailResp emailDetail) {
                        mRootView.setEmailDetail(emailDetail);
                    }
                });
    }

}