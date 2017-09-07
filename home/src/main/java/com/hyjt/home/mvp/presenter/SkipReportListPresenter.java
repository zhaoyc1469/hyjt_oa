package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.SkipReportListContract;
import com.hyjt.home.mvp.model.entity.Reqs.ReportTopListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.SReportListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.ui.adapter.ReportTopAdapter;
import com.hyjt.home.mvp.ui.adapter.SkipReportAdapter;

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
public class SkipReportListPresenter extends BasePresenter<SkipReportListContract.Model, SkipReportListContract.View> {
    private RxErrorHandler mErrorHandler;
    private SkipReportAdapter mAdapter;
    private int pageId;
    private List<SReportListResp.RowsBean> srList = new ArrayList<>();

    @Inject
    public SkipReportListPresenter(SkipReportListContract.Model model, SkipReportListContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public void getReportList(boolean pullToRefresh, String Type){

        if (mAdapter == null) {
            mAdapter = new SkipReportAdapter(srList,Type);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = srList.get(position).getId();
                ARouter.getInstance().build("/home/SkipReportDetailActivity")
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


        mModel.getSReportList(new SReportListReqs("" + pageId, "10", Type))
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
                    } else{
                        if (mRootView != null) {
                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                        }
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<SReportListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull SReportListResp reportListResp) {
                        if (pullToRefresh) {
                            srList.clear();
                            srList.addAll(reportListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            srList.addAll(reportListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (reportListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }
    
    
}