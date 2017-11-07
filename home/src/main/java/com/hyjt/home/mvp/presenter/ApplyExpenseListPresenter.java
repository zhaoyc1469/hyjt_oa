package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ApplyExpenseListContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpenseListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.ui.adapter.ApplyExpenseAdapter;
import com.hyjt.home.mvp.ui.adapter.CompLoanAdapter;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class ApplyExpenseListPresenter extends BasePresenter<ApplyExpenseListContract.Model, ApplyExpenseListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private ApplyExpenseAdapter mAdapter;
    private int pageId;
    private List<ApplyExpenseListResp.RowsBean> aeList = new ArrayList<>();

    @Inject
    public ApplyExpenseListPresenter(ApplyExpenseListContract.Model model, ApplyExpenseListContract.View rootView
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

    public void getApplyExpList(boolean pullToRefresh, String Type, String Mode, String CwEpersonal,
                                String CwEcompany, String CwEdepartment, String CwEnum, String Start, String End) {
        if (mAdapter == null) {
            mAdapter = new ApplyExpenseAdapter(aeList);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) ->
                    ARouter.getInstance().build("/home/ApplyExpenseEditActivity")
                    .withString("ApplyExpId", aeList.get(position).getId())
                    .withString("ApplyExpType", Type).navigation());
        }

        if (pullToRefresh) {
            pageId = 1;
            mRootView.showLoading();
        } else
            pageId++;

        mModel.getApplyExpList(new ApplyExpenseListReqs("" + pageId, "10", Start, End,
                Type, Mode, CwEpersonal, CwEcompany, CwEdepartment, CwEnum))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显示上拉刷新的进度条
                    else
                        mRootView.startLoadMore();//显示下拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        mRootView.hideLoading();//隐藏上拉刷新的进度条
                    } else {
                        mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<ApplyExpenseListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ApplyExpenseListResp applyExpenseListResp) {
                        if (pullToRefresh) {
                            aeList.clear();
                            aeList.addAll(applyExpenseListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            aeList.addAll(applyExpenseListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (applyExpenseListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if ("202".equals(e.getMessage())) {
                            mRootView.showNoLimits();
//                            mRootView.killMyself();
                        } else {
                            mRootView.showMessage(e.getMessage());
                        }
                    }
                });

    }

}
