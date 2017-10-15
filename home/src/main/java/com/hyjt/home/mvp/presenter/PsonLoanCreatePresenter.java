package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.widget.EditText;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.ui.view.treelistview.Dept;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class PsonLoanCreatePresenter extends BasePresenter<PsonLoanCreateContract.Model, PsonLoanCreateContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;

    @Inject
    public PsonLoanCreatePresenter(PsonLoanCreateContract.Model model, PsonLoanCreateContract.View rootView
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


    public void getFristLeader(String plType){
        mModel.getFristLeader(new BaseTypeReqs(plType))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PLFristLeaderResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLFristLeaderResp plFristLeaderResp) {
                        mRootView.loadFlowNode(plFristLeaderResp);
                    }
                });
    }

    public void getPLCompany(){
        mModel.getPLCompany()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PLCompanyResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLCompanyResp plCompanyResp) {

                    }
                });
    }

    public void getPLFlowNode(){
        mModel.getFlowNode()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PLFlowNodeResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLFlowNodeResp plFlowNodeResp) {
//                        mRootView.showFlowNode(plFristLeaderResp);
                    }
                });
    }


    public void getOpenBank(){
        mModel.getPLCompany()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PLCompanyResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLCompanyResp plCompanyResp) {

                    }
                });
    }
}
