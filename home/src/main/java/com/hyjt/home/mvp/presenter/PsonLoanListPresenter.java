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

import com.hyjt.home.mvp.contract.PsonLoanListContract;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ReportTopListReqs;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAdapter;
import com.hyjt.home.mvp.ui.adapter.ReportTopAdapter;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class PsonLoanListPresenter extends BasePresenter<PsonLoanListContract.Model, PsonLoanListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private PsonLoanAdapter mAdapter;
    private List<PsonLoanListResp.RowsBean> plList = new ArrayList<>();
    private int pageId;

    @Inject
    public PsonLoanListPresenter(PsonLoanListContract.Model model, PsonLoanListContract.View rootView
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

    public void getPsonLoanList(boolean pullToRefresh, String Type, String Mode,
                                String CwPpersonal, String CwPcompany, String CwPdepartment,
                                String CwPnum, String Start, String End){

        if (mAdapter == null) {
            mAdapter = new PsonLoanAdapter(plList);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                ARouter.getInstance().build("/home/PsonLoanEditActivity")
                        .withString("PsonLoanId", plList.get(position).getId())
                        .withString("PsonLoanType", Type).navigation();
            });
        }

        if (pullToRefresh) {
            pageId = 1;
            mRootView.showLoading();
        } else
            pageId++;

        mModel.getPsonLoanList(new PsonLoanListReqs("" + pageId, "10", CwPnum, Start, End,
                Type, Mode, CwPpersonal, CwPcompany, CwPdepartment))
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
                .subscribe(new ErrorHandleSubscriber<PsonLoanListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PsonLoanListResp psonLoanListResp) {
                        if (pullToRefresh) {
                            plList.clear();
                            plList.addAll(psonLoanListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            plList.addAll(psonLoanListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (psonLoanListResp.getRows().size() == 0) {
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
