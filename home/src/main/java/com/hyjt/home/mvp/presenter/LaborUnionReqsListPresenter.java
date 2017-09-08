package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
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
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.LaborUnionReqsListContract;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.LUReqsListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsListResp;
import com.hyjt.home.mvp.ui.adapter.ContributeIAdapter;
import com.hyjt.home.mvp.ui.adapter.LUAppealAdapter;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class LaborUnionReqsListPresenter extends BasePresenter<LaborUnionReqsListContract.Model, LaborUnionReqsListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;


    private LUAppealAdapter mAdapter;
    private int pageId;
    private List<LUReqsListResp.RowsBean> srList = new ArrayList<>();

    @Inject
    public LaborUnionReqsListPresenter(LaborUnionReqsListContract.Model model, LaborUnionReqsListContract.View rootView
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

    public void getLUList(boolean pullToRefresh, String Type) {
        if (mAdapter == null) {
            mAdapter = new LUAppealAdapter(srList, Type);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
//                String id = srList.get(position).getId();
//                ARouter.getInstance().build("/home/ContributeIdeaDetailActivity")
//                        .withString("Id", id)
////                        .withString("Type", Type)
//                        .navigation();
            });
        }

        if (pullToRefresh) {
            pageId = 1;
            mRootView.showLoading();
        } else
            pageId++;


        mModel.getLuList(new LUReqsListReqs("" + pageId, "10", Type))
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
                .doAfterTerminate(() -> {
                    if (pullToRefresh) {
                        if (mRootView != null) {
                            mRootView.hideLoading();//隐藏上拉刷新的进度条
                        }
                    } else {
                        if (mRootView != null) {
                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                        }
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<LUReqsListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LUReqsListResp cideaList) {
//                        if (pullToRefresh) {
//                            srList.clear();
//                            srList.addAll(cideaList.getRows());
//                            mAdapter.notifyDataSetChanged();
//                        } else {
//                            srList.addAll(cideaList.getRows());
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        if (cideaList.getRows().size() == 0) {
//                            if (mRootView != null)
//                                mRootView.endLoad();
//                        }
                    }
                });
    }

}
