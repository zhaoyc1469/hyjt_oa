package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.SLConsultListContract;
import com.hyjt.home.mvp.model.entity.Reqs.SLConsultListReqs;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultListResp;
import com.hyjt.home.mvp.ui.adapter.SLConsultAdapter;

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
public class SLConsultListPresenter extends BasePresenter<SLConsultListContract.Model, SLConsultListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private SLConsultAdapter mAdapter;
    private List<SLConsultListResp.RowsBean> slcList = new ArrayList<>();
    private int pageId = 1;

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

    public void getSLCList(boolean pullToRefresh, String Type) {

        if (mAdapter == null) {
            mAdapter = new SLConsultAdapter(slcList, Type);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = slcList.get(position).getId();
                ARouter.getInstance().build("/home/SLConsultEditActivity")
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

        mModel.SLConsultList(new SLConsultListReqs("" + pageId, "10", Type))
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
                .subscribe(new ErrorHandleSubscriber<SLConsultListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull SLConsultListResp sLConsultListResp) {
                        if (pullToRefresh) {
                            slcList.clear();
                            slcList.addAll(sLConsultListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            slcList.addAll(sLConsultListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (sLConsultListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }

}