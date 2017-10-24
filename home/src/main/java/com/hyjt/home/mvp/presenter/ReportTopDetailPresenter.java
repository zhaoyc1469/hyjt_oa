package com.hyjt.home.mvp.presenter;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.home.mvp.contract.ReportTopDetailContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class ReportTopDetailPresenter extends BasePresenter<ReportTopDetailContract.Model, ReportTopDetailContract.View> {
    private RxErrorHandler mErrorHandler;

    @Inject
    public ReportTopDetailPresenter(ReportTopDetailContract.Model model, ReportTopDetailContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void getReportDetail(String Id){

        mModel.reportTopDetail(new BaseIdReqs(Id))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<ReportTDetailResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ReportTDetailResp reportTDetail) {
                        mRootView.setRTDetail(reportTDetail);
                    }
                });
    }

    public void getReportEdit(ReportTDetailResp reportDetail){

        mModel.reportTopEdit(reportDetail)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object reportTDetail) {
                        mRootView.showMessage("编辑成功");
                        mRootView.killMyself();
                    }
                });
    }


    public void getReportDel(String Id){

        mModel.reportTopDel(new BaseIdReqs(Id))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("删除成功");
                        mRootView.killMyself();
                    }
                });
    }

}