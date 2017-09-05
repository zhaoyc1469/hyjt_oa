package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.SLConsultListContract;
import com.hyjt.home.mvp.model.entity.Reqs.SLConsultListReqs;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultListResp;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class SLConsultListPresenter extends BasePresenter<SLConsultListContract.Model, SLConsultListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public SLConsultListPresenter(SLConsultListContract.Model model, SLConsultListContract.View rootView
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

    public void getSLCList(boolean pullToRefresh, String Type){

        mModel.SLConsultList(new SLConsultListReqs("1", "10", "UnRead"))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示上拉刷新的进度条
//                    else
//                        mRootView.startLoadMore();//显示下拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
//                    if (pullToRefresh) {
//                        if (mRootView != null) {
//                            mRootView.hideLoading();//隐藏上拉刷新的进度条
//                        }
//                    } else{
//                        if (mRootView != null) {
//                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
//                        }
//                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<SLConsultListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull SLConsultListResp sLConsultListResp) {
//                        if (pullToRefresh) {
//                            rtList.clear();
//                            rtList.addAll(reportTListResp.getRows());
//                            mAdapter.notifyDataSetChanged();
//                        } else {
//                            rtList.addAll(reportTListResp.getRows());
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        if (reportTListResp.getRows().size() == 0) {
//                            mRootView.endLoad();
//                        }
                    }
                });
    }

}