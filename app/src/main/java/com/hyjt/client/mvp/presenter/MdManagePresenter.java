package com.hyjt.client.mvp.presenter;

import android.app.Application;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.widget.imageloader.ImageLoader;


import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.hyjt.client.mvp.contract.MdManageContract;


@ActivityScope
public class MdManagePresenter extends BasePresenter<MdManageContract.Model, MdManageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MdManagePresenter(MdManageContract.Model model, MdManageContract.View rootView
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

}
