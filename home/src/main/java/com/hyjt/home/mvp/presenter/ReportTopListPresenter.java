package com.hyjt.home.mvp.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.home.mvp.contract.ReportTopListContract;
import com.hyjt.home.mvp.model.entity.Reqs.ReportTopListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.ui.adapter.ReportTopAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class ReportTopListPresenter extends BasePresenter<ReportTopListContract.Model, ReportTopListContract.View> {
    private RxErrorHandler mErrorHandler;
    private ReportTopAdapter mAdapter;
    private List<ReportTListResp.RowsBean> rtList = new ArrayList<>();
    private int pageId = 1;

    @Inject
    public ReportTopListPresenter(ReportTopListContract.Model model, ReportTopListContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void getReportList(boolean pullToRefresh, String Type) {

        if (mAdapter == null) {
            mAdapter = new ReportTopAdapter(rtList, Type);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = rtList.get(position).getId();
                ARouter.getInstance().build("/home/ReportTopDetailActivity")
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


        mModel.getReportTopList(new ReportTopListReqs("" + pageId, "10", Type))
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
                        if (mRootView != null) {
                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                        }
                    }

                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<ReportTListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ReportTListResp reportTListResp) {
                        if (pullToRefresh) {
                            rtList.clear();
                            rtList.addAll(reportTListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            rtList.addAll(reportTListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (reportTListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }

}