package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.widget.imageloader.ImageLoader;


import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanEditContract;


@ActivityScope
public class PsonLoanEditPresenter extends BasePresenter<PsonLoanEditContract.Model, PsonLoanEditContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;

    @Inject
    public PsonLoanEditPresenter(PsonLoanEditContract.Model model, PsonLoanEditContract.View rootView
            , RxErrorHandler handler, Application application) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

//    public void getrPsonLoanDetail()

}
