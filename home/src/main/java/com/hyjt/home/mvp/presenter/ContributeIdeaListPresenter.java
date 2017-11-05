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
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ContributeIdeaListContract;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.SReportListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.ui.adapter.ContributeIAdapter;
import com.hyjt.home.mvp.ui.adapter.SkipReportAdapter;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class ContributeIdeaListPresenter extends BasePresenter<ContributeIdeaListContract.Model, ContributeIdeaListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private ContributeIAdapter mAdapter;
    private int pageId;
    private List<CIdeaListResp.RowsBean> srList = new ArrayList<>();

    @Inject
    public ContributeIdeaListPresenter(ContributeIdeaListContract.Model model, ContributeIdeaListContract.View rootView
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

    public void getControbuteList(boolean pullToRefresh, String Type) {
        if (mAdapter == null) {
            mAdapter = new ContributeIAdapter(srList, Type);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = srList.get(position).getId();
                ARouter.getInstance().build("/home/ContributeIdeaDetailActivity")
                        .withString("Id", id)
//                        .withString("Type", Type)
                        .navigation();
            });
        }

        if (pullToRefresh) {
            pageId = 1;
            mRootView.showLoading();
        } else
            pageId++;


        mModel.getIdeaList(new CIdeaListReqs("" + pageId, "10", Type))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显示上拉刷新的进度条
                    else
                        mRootView.startLoadMore();//显示下拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        mRootView.hideLoading();
                    } else {
                        mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                    }

                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<CIdeaListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull CIdeaListResp cideaList) {
                        if (pullToRefresh) {
                            srList.clear();
                            srList.addAll(cideaList.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            srList.addAll(cideaList.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (cideaList.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }
}
