package com.hyjt.home.mvp.presenter;

import android.app.Application;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.ToCompLoanListContract;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.ui.adapter.CompLoanAdapter;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAdapter;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class ToCompLoanListPresenter extends BasePresenter<ToCompLoanListContract.Model, ToCompLoanListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private CompLoanAdapter mAdapter;
    private int pageId;
    private List<CompLoanListResp.RowsBean> clList = new ArrayList<>();

    @Inject
    public ToCompLoanListPresenter(ToCompLoanListContract.Model model, ToCompLoanListContract.View rootView
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

    public void getCompLoanList(boolean pullToRefresh, String Type, String Mode, String CwCpersonal,
                                String CwCcompany, String CwCdepartment, String CwCnum, String Start, String End) {
        if (mAdapter == null) {
            mAdapter = new CompLoanAdapter(clList);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                ARouter.getInstance().build("/home/PsonLoanEditActivity")
                        .withString("CompLoanId", clList.get(position).getId())
                        .withString("CompLoanType", Type).navigation();
            });
        }

        if (pullToRefresh) {
            pageId = 1;
            mRootView.showLoading();
        } else
            pageId++;

        mModel.getCompLoanList(new CompLoanListReqs("" + pageId, "10", CwCnum, Start, End,
                Type, Mode, CwCpersonal, CwCcompany, CwCdepartment))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
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
                    } else{
                        if (mRootView != null) {
                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                        }
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<CompLoanListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull CompLoanListResp compLoanListResp) {
                        if (pullToRefresh) {
                            clList.clear();
                            clList.addAll(compLoanListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            clList.addAll(compLoanListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (compLoanListResp.getRows().size() == 0) {
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
